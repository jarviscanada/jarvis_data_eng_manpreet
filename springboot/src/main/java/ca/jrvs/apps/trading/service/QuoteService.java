package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import javax.transaction.Transactional;

import ca.jrvs.apps.trading.model.domain.Quote;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao){
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Find an IexQuote
     */
    public IexQuote findIexQuoteByTicker(String ticker){
        return marketDataDao.findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid."));
    }

    /**
     * Update quote table against IEX source
     *  - get all quotes from the db
     *  - foreach ticker get IexQuote
     *  - convert iexQuote to quote entity
     *  - persist quote to db
     *
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if ticker is not found from IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void updateMarketData(){
        List<Quote> quotes = findAllQuotes();
        IexQuote iexQuote = new IexQuote();
        Quote tempQuote = new Quote();
        for (Quote quote : quotes){
            String ticker = quote.getTicker();
            iexQuote = marketDataDao.findById(ticker).get();
            tempQuote = buildQuoteFromIexQuote(iexQuote);
            quoteDao.save(tempQuote);
        }
    }

    /**
     * Helper method: Map a IexQuote to a Quote entity
     *
     * -Note: `iexQuote.getLastestPrice() == null` if the stock market is closed.
     * -Make sure set a default value for number field(s).
     */
    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote){
        Quote quote = new Quote();
        quote.setTicker(iexQuote.getSymbol());
        quote.setLastPrice(Double.parseDouble(iexQuote.getLatestPrice()));
        quote.setAskPrice(Double.parseDouble(iexQuote.getIexAskPrice()));
        quote.setAskSize(Integer.parseInt(iexQuote.getIexAskSize()));
        quote.setBidPrice(Double.parseDouble(iexQuote.getIexBidPrice()));
        quote.setBidSize(Integer.parseInt(iexQuote.getIexBidSize()));
        return quote;
    }

    /**
     * find all quotes from the quote table
     * @return list of quotes
     */
    public List<Quote> findAllQuotes(){
        return quoteDao.findAll();
    }

    /**
     * Validate (aganist IEX) and save given tickers to quote table
     *
     * - Get iexQuotes
     * - convert each iexQuote to Quote entity
     * - persist the quote to db
     *
     * @param tickers
     * @throws IllegalArgumentException if ticker is not found
     */
    public List<Quote> saveQuotes(List<String> tickers){
        Quote quote;
        List<Quote> savedQuote = new ArrayList<>();
        List<IexQuote> iexQuotes= marketDataDao.findAllById(tickers);
        for(IexQuote iexQuote: iexQuotes){
            quote = buildQuoteFromIexQuote(iexQuote);
            savedQuote.add(saveQuote(quote));
        }
        return savedQuote;
    }

    public Quote saveQuote(Quote quote){
        return quoteDao.save(quote);
    }

    public Quote saveQuote(String ticker){
        IexQuote iexQuote = new IexQuote();
        iexQuote = marketDataDao.findById(ticker).get();
        Quote quote = buildQuoteFromIexQuote(iexQuote);
        return saveQuote(quote);
    }


}