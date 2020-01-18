package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceTest {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private QuoteDao quoteDao;

    private Quote quote;

    @Before
    public void setUp() throws Exception {
        quoteDao.deleteAll();
        quote = new Quote();
        quote.setAskPrice(1d);
        quote.setAskSize(2);
        quote.setBidPrice(6.9d);
        quote.setBidSize(7);
        quote.setTicker("MSFT");
        quote.setLastPrice(55.2d);
        quoteDao.save(quote);
    }

    @Test
    public void saveQuotes() {
        Quote quote1 = new Quote();
        quote1.setAskPrice(5d);
        quote1.setAskSize(5);
        quote1.setBidPrice(5.9d);
        quote1.setBidSize(5);
        quote1.setTicker("SDG");
        quote1.setLastPrice(5.2d);

        Quote quote2 = new Quote();
        quote2.setAskPrice(4d);
        quote2.setAskSize(4);
        quote2.setBidPrice(1.9d);
        quote2.setBidSize(7);
        quote2.setTicker("AAPL");
        quote2.setLastPrice(15.2d);
        List<Quote> quotes = new ArrayList<Quote>() {
            {
                add(quote1);
                add(quote2);
            }
        };
        List<Quote> savedQuotes = quoteService.saveQuotes(quotes);
        assertNotNull(savedQuotes);
        assertEquals(2, savedQuotes.size());
    }

    @Test
    public void saveQuote() {
        Quote quote = new Quote();
        quote.setAskPrice(1d);
        quote.setAskSize(2);
        quote.setBidPrice(6.9d);
        quote.setBidSize(7);
        quote.setTicker("JNJ");
        quote.setLastPrice(55.2d);
        Quote savedQuote  = quoteService.saveQuote(quote);

        assertNotNull(savedQuote);
        assertEquals(quote.getAskPrice(), savedQuote.getAskPrice());
        assertEquals(quote.getAskSize(), savedQuote.getAskSize());
        assertEquals(quote.getBidPrice(), savedQuote.getBidPrice());
        assertEquals(quote.getBidSize(), savedQuote.getBidSize());
        assertEquals(quote.getTicker(), savedQuote.getTicker());
        assertEquals(quote.getLastPrice(), savedQuote.getLastPrice());
    }

    @Test
    public void findIexQuoteByTicker() {
        String ticker = "AAPL";
        IexQuote iex = quoteService.findIexQuoteByTicker(ticker);
        assertNotNull(iex);
        assertEquals(iex.getSymbol(), ticker);

        String invalidTicker = "INVALID";
        try {
            quoteService.findIexQuoteByTicker(invalidTicker);
            fail();
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void updateMarketData() {
        quoteService.updateMarketData();
        assertEquals(1, quoteDao.count());
        Assert.assertTrue(quoteDao.existsById(quote.getTicker()));

    }


    @Test
    public void findAllQuotes() {
        List<Quote> quotes = quoteService.findAllQuotes();
        assertNotNull(quotes);
        assertEquals(1, quotes.size());
    }

}