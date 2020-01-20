package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
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
public class TraderDaoTest {

    @Autowired
    private TraderDao traderDao;

    private Trader trader;


    @Before
    public void insertOne() {
        traderDao.deleteAll();
        trader = new Trader();
        trader.setFirst_name("John");
        trader.setLast_name("Matic");
        trader.setCountry("Serbia");
        trader.setDob(new Date());
        trader.setEmail("john.matic@mail.com");
        traderDao.save(trader);

    }

    @Test
    public void updateOne() {
        String firstName = "Jamie";
        String lastName = "Matic";
        String country = "Canada";
        trader.setFirst_name(firstName);
        trader.setLast_name(lastName);
        trader.setCountry(country);

        int changeNum = traderDao.updateOne(trader);

        assertEquals(1, changeNum);
    }

    @Test
    public void findAllById() {
        List<Integer> ids= Arrays.asList(trader.getID());
        Iterable<Trader> savedTraders =traderDao.findAllById(ids);
        List<Trader> traders = Lists.newArrayList(savedTraders);
        assertEquals(1, traders.size());
        assertEquals(trader.getFirst_name(), traders.get(0).getFirst_name());
        assertEquals(trader.getLast_name(), traders.get(0).getLast_name());
        assertEquals(trader.getCountry(), traders.get(0).getCountry());
        assertEquals(trader.getEmail(), traders.get(0).getEmail());
    }

    @Test
    public void saveAll() {
        List<Trader> traders = new ArrayList<Trader>();
        Trader trader1 = new Trader();
        trader1.setFirst_name("T1");
        trader1.setLast_name("D1");
        trader1.setCountry("Canada");
        trader1.setDob(new Date());
        trader1.setEmail("T1@mail.com");

        Trader trader2 = new Trader();
        trader2.setFirst_name("T2");
        trader2.setLast_name("D2");
        trader2.setCountry("Canada");
        trader2.setDob(new Date());
        trader2.setEmail("T2@mail.com");

        traders.add(trader1);
        traders.add(trader2);
        Iterable<Trader> savedTraders = traderDao.saveAll(traders);

        assertNotNull(savedTraders);
        assertEquals(3, traderDao.count());
    }

    @Test
    public void findById() {
        Trader savedTrader = traderDao.findById(trader.getID()).get();
        assertEquals(trader.getFirst_name(), savedTrader.getFirst_name());
        assertEquals(trader.getLast_name(), savedTrader.getLast_name());
        assertEquals(trader.getCountry(), savedTrader.getCountry());
        assertEquals(trader.getEmail(), savedTrader.getEmail());
    }

    @Test
    public void existsById() {
        List<Trader> traders = Lists.newArrayList(traderDao.findAll());
        for(Trader trader: traders) {
            System.out.println("Id:" + trader.getID());
        }
        assertTrue(traderDao.existsById(trader.getID()));
    }

    @Test
    public void findAll() {
        Iterable<Trader> traders = traderDao.findAll();
        int count = 0;
        for (Trader trade: traders) {
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void count() {
        assertEquals(1, traderDao.count());
    }


    @Test
    public void deleteAll() {
        traderDao.deleteAll();
        assertEquals(0, traderDao.count());
    }

    @After
    public void deleteOne() {
        traderDao.deleteById(trader.getID());
    }

}