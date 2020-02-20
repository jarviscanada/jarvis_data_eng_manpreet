package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Sql({"classpath:schema.sql"})
@SpringBootTest(classes = {TestConfig.class})
public class AccountDaoTest {

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private AccountDao accountDao;

    private Account account;

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

        accountDao.deleteAll();
        account = new Account();
        account.setAmount(200.99);
        account.setTrader_id(trader.getID());
        accountDao.save(account);

    }

    @Test
    public void updateOne() {
        Double amount = 300.90;
        Integer traderId = 2;
        account.setAmount(amount);
        account.setTrader_id(traderId);

        int changeNum = accountDao.updateOne(account);

        assertEquals(1, changeNum);
    }

    @Test
    public void findAllById() {
        List<Integer> ids= Arrays.asList(account.getID());
        Iterable<Account> savedAccounts =accountDao.findAllById(ids);
        List<Account> traders = Lists.newArrayList(savedAccounts);
        assertEquals(1, traders.size());
        assertEquals(account.getAmount(), traders.get(0).getAmount());
        assertEquals(account.getTrader_id(), traders.get(0).getTrader_id());

    }

    @Test
    public void saveAll() {
        List<Account> accounts = new ArrayList<Account>();
        Account account1 = new Account();
        account1.setAmount(400.88);
        account1.setTrader_id(trader.getID());

        Account account2 = new Account();
        account2.setAmount(330.88);
        account2.setTrader_id(trader.getID());

        accounts.add(account1);
        accounts.add(account2);
        Iterable<Account> savedAccounts = accountDao.saveAll(accounts);

        assertNotNull(savedAccounts);
        assertEquals(3, accountDao.count());

        accountDao.deleteById(account1.getID());
        accountDao.deleteById(account2.getID());
    }

    @Test
    public void findById() {
        Account savedAccount = accountDao.findById(account.getID()).get();
        assertEquals(account.getTrader_id(), savedAccount.getTrader_id());
        assertEquals(account.getAmount(), savedAccount.getAmount());

    }

    @Test
    public void existsById() {
        List<Account> accounts = Lists.newArrayList(accountDao.findAll());
        assertTrue(accountDao.existsById(account.getID()));
    }

    @Test
    public void findAll() {
        Iterable<Account> accounts = accountDao.findAll();
        int count = 0;
        for (Account account: accounts) {
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void count() {
        assertEquals(1, accountDao.count());
    }


    @Test
    public void deleteAll() {
        accountDao.deleteAll();
        assertEquals(0, accountDao.count());
    }

    @After
    public void deleteOne() {
        accountDao.deleteById(account.getID());
        traderDao.deleteById(trader.getID());
    }

}