package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.*;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Sql({"classpath:schema.sql"})
@SpringBootTest(classes = {TestConfig.class})
public class PositionDaoTest {

    @Autowired
    private SecurityOrderDao securityOrderDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private QuoteDao quoteDao;

    private SecurityOrder securityOrder;

    private Position position;

    private Account account;

    private Trader trader;

    private Quote quote;

    @Before
    public void insertOne() {
        quote = new Quote();
        quote.setTicker("AAPL");
        quote.setAskPrice(26.3);
        quote.setAskSize(52);
        quote.setBidPrice(55.6);
        quote.setBidSize(12);
        quote.setLastPrice(26.6);
        quoteDao.save(quote);

        traderDao.deleteAll();
        trader = new Trader();
        trader.setFirst_name("John");
        trader.setLast_name("Matic");
        trader.setCountry("Serbia");
        trader.setDob(new Date());
        trader.setEmail("john.matic@mail.com");
        traderDao.save(trader);

        accountDao.deleteAll();
        account = new Account();
        account.setAmount(200.99);
        account.setTrader_id(trader.getID());
        accountDao.save(account);

        securityOrderDao.deleteAll();
        securityOrder = new SecurityOrder();
        securityOrder.setAccountId(account.getID());
        securityOrder.setNotes("abchdjs");
        securityOrder.setPrice(22.33);
        securityOrder.setSize(2);
        securityOrder.setPrice(33.22);
        securityOrder.setStatus("FILLED");
        securityOrder.setTicker(quote.getTicker());
        securityOrderDao.save(securityOrder);
    }


//    @Test
//    public void findAllById() {
//        List<Integer> ids= Arrays.asList(position.getID());
//        Iterable<Position> savedPositions =positionDao.findAllById(ids);
//        List<Position> positions = Lists.newArrayList(savedPositions);
//        assertEquals(1, positions.size());
//        assertEquals(position.getTicker(), positions.get(0).getTicker());
//        assertEquals(position.getPosition(), positions.get(0).getPosition());
//    }

//    @Test
//    public void saveAll() {
//        List<Position> positions = new ArrayList<Position>();
//        Position position1 = new Position();
//        position1.setTicker("FB");
//        position1.setPosition(13);
//
//
//        Position position2 = new Position();
//        position2.setTicker("AAPL");
//        position2.setPosition(14);
//
//
//        positions.add(position1);
//        positions.add(position2);
//        Iterable<Position> savedPositions = positionDao.saveAll(positions);
//
//        assertNotNull(savedPositions);
//        assertEquals(3, positionDao.count());
//    }

//    @Test
//    public void findById() {
//        Position savedPostion = positionDao.findById(position.getID()).get();
//        assertEquals(position.getTicker(), savedPostion.getTicker());
//        assertEquals(position.getPosition(), savedPostion.getPosition());
//
//    }

//    @Test
//    public void existsById() {
//        List<Position> positions = Lists.newArrayList(positionDao.findAll());
//        for(Position position: positions) {
//            System.out.println("Id:" + position.getID());
//        }
//        assertTrue(positionDao.existsById(position.getID()));
//    }

    @Test
    public void findAll() {
        Iterable<Position> positions = positionDao.findAll();
        int count = 0;
        for (Position trade: positions) {
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void count() {
        assertEquals(1, positionDao.count());
    }

    @After
    public void deleteOne() {
        securityOrderDao.deleteById(securityOrder.getID());
        accountDao.deleteById(account.getID());
        traderDao.deleteById(trader.getID());
        quoteDao.deleteById(quote.getTicker());
    }

}