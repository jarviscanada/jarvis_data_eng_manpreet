package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoTest {

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote;

    @Before
    public void insertOne(){
        savedQuote.setAskPrice(5d);
        savedQuote.setAskSize(5);
        savedQuote.setBidPrice(5.9d);
        savedQuote.setBidSize(5);
        savedQuote.setTicker("SDG");
        savedQuote.setLastPrice(5.2d);
        quoteDao.save(savedQuote);
    }

    @Test
    public void save() {
    }

    @Test
    public void saveAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void existsById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void count() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void deleteAll() {
    }

    @After
    public void deleteOne(){
        quoteDao.deleteById(savedQuote.getTicker());
    }
}