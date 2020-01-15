package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
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
    public void insertOne(){
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("SDG");
        savedQuote.setLastPrice(10.1d);
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
}