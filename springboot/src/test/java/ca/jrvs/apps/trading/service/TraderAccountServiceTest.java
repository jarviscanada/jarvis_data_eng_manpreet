package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceTest {

    @Autowired
    private TraderAccountService traderAccountService;

    @Autowired
    private TraderDao traderDoa;

    @Autowired
    private AccountDao accountDao;

    private TraderAccountView savedTraderAccountView;

    @Before
    public  void setUp() {
        Trader trader = new Trader();
        trader.setEmail("test@mail.com");
        trader.setFirst_name("Test_Fname");
        trader.setLast_name("Test_Lname");
        trader.setDob(new Date());
        trader.setCountry("Canada");
        savedTraderAccountView = traderAccountService.createTraderAndAccount(trader);
    }

    @Test
    public void createTraderAndAccount() {
        Trader trader = new Trader();
        try {
            traderAccountService.createTraderAndAccount(trader);
            fail();
        }
        catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        trader.setEmail("test1@mail.com");
        trader.setFirst_name("Test1_Fname");
        trader.setLast_name("Test1_Lname");
        trader.setDob(new Date());
        trader.setCountry("Canada");

        TraderAccountView view = traderAccountService.createTraderAndAccount(trader);

        assertEquals(trader.getEmail(), view.getTrader().getEmail());
        assertEquals(trader.getDob(), view.getTrader().getDob());
        assertEquals(trader.getCountry(), view.getTrader().getCountry());
        assertEquals(trader.getFirst_name(), view.getTrader().getFirst_name());
        assertEquals(trader.getLast_name(), view.getTrader().getLast_name());
    }

    private void checkDepositAccount() {
        Trader trader = new Trader();
        trader.setEmail("test2@mail.com");
        trader.setFirst_name("Test2_Fname");
        trader.setLast_name("Test2_Lname");
        trader.setDob(new Date());
        trader.setCountry("Canada");
        trader = traderDoa.save(trader);

        try {
            traderAccountService.deposit(trader.getID(), 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void deposit() {
        try {
            traderAccountService.deposit(null, 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            traderAccountService.deposit(savedTraderAccountView.getTrader().getID(), -10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        checkDepositAccount();
        Account account = traderAccountService.deposit(savedTraderAccountView.getTrader().getID(), 100d);
        savedTraderAccountView.setAccount(account);

        assertEquals(new Double(100), account.getAmount());
    }

    private void checkInsufficient() {
        try {
            traderAccountService.withdraw(savedTraderAccountView.getTrader().getID(), 110.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    private void checkWithdrawAccount() {
        Trader trader = new Trader();
        trader.setEmail("test2@mail.com");
        trader.setFirst_name("Test2_Fname");
        trader.setLast_name("Test2_Lname");
        trader.setDob(new Date());
        trader.setCountry("Canada");
        trader = traderDoa.save(trader);

        try {
            traderAccountService.withdraw(trader.getID(), 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void withdraw() {
        try {
            traderAccountService.withdraw(null, 10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            traderAccountService.withdraw(savedTraderAccountView.getTrader().getID(), -10.0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        checkWithdrawAccount();
        Account account = traderAccountService.deposit(savedTraderAccountView.getTrader().getID(), 100d);
        savedTraderAccountView.setAccount(account);
        checkInsufficient();
        account = traderAccountService.withdraw(savedTraderAccountView.getTrader().getID(), 80d);
        savedTraderAccountView.setAccount(account);

        assertEquals(new Double(20), account.getAmount());
    }

    @Test
    public void deleteTraderByID() {
        try {
            traderAccountService.deleteTraderByID(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        traderAccountService.deposit(savedTraderAccountView.getTrader().getID(), 100d);

        try {
            traderAccountService.deleteTraderByID(savedTraderAccountView.getTrader().getID());
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        traderAccountService.withdraw(savedTraderAccountView.getTrader().getID(), 100d);

    }

    @After
    public void tearDown() {
        if (savedTraderAccountView.getAccount().getAmount() != 0) {
            Account account = traderAccountService.withdraw(savedTraderAccountView.getTrader().getID(), savedTraderAccountView.getAccount().getAmount());
            savedTraderAccountView.setAccount(account);
        }

        traderAccountService.deleteTraderByID(savedTraderAccountView.getTrader().getID());
    }
}