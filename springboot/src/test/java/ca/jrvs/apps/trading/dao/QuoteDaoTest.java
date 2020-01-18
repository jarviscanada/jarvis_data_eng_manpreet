package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoTest {

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote;
    private Quote savedQuote1;

    @Before
    public void insertTwo(){
        savedQuote = new Quote();
        savedQuote.setAskPrice(5d);
        savedQuote.setAskSize(5);
        savedQuote.setBidPrice(5.9d);
        savedQuote.setBidSize(5);
        savedQuote.setTicker("SDG");
        savedQuote.setLastPrice(5.2d);
        quoteDao.save(savedQuote);

        savedQuote1 = new Quote();
        savedQuote1.setAskPrice(4d);
        savedQuote1.setAskSize(4);
        savedQuote1.setBidPrice(1.9d);
        savedQuote1.setBidSize(7);
        savedQuote1.setTicker("AAPL");
        savedQuote1.setLastPrice(15.2d);
        quoteDao.save(savedQuote1);
    }


    @Test
    public void findById() {
        String ticker = savedQuote.getTicker();
        Optional<Quote> quote = quoteDao.findById(ticker);
        assertEquals(quote.get().getAskPrice(), savedQuote.getAskPrice());
        assertEquals(quote.get().getAskSize(), savedQuote.getAskSize());
        assertEquals(quote.get().getBidPrice(), savedQuote.getBidPrice());
        assertEquals(quote.get().getBidSize(), savedQuote.getBidSize());
        assertEquals(quote.get().getTicker(), savedQuote.getTicker());
        assertEquals(quote.get().getLastPrice(), savedQuote.getLastPrice());
    }

    @Test
    public void existsById() {
        assertTrue(quoteDao.existsById(savedQuote.getTicker()));
        assertFalse(quoteDao.existsById("MNOP"));
    }

    @Test
    public void findAll() {
        String ticker = savedQuote.getTicker();
        List<Quote> quotes = quoteDao.findAll();
        assertEquals(quotes.get(0).getAskPrice(), savedQuote.getAskPrice());
        assertEquals(quotes.get(0).getAskSize(), savedQuote.getAskSize());
        assertEquals(quotes.get(0).getBidPrice(), savedQuote.getBidPrice());
        assertEquals(quotes.get(0).getBidSize(), savedQuote.getBidSize());
        assertEquals(quotes.get(0).getTicker(), savedQuote.getTicker());
        assertEquals(quotes.get(0).getLastPrice(), savedQuote.getLastPrice());
    }

    @Test
    public void count() {
        assertEquals(2, quoteDao.count());
    }

    @After
    public void delete() {
        quoteDao.deleteById(savedQuote.getTicker());
        assertEquals(1, quoteDao.count());
        quoteDao.deleteAll();
        assertEquals(0, quoteDao.count());
    }

}