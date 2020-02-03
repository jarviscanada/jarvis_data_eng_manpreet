package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao extends JdbcCrudDao<Account>{

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);

    private final String TABLE_NAME = "account";
    private final String ID_COLUMN = "id";
    private final String TRADER_ID_COLUMN = "trader_id";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public AccountDao(DataSource dataSource){
        setJdbcTemplate(new JdbcTemplate(dataSource));
        setSimpleJdbcInsert(new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN));
    }

    /**
     * Update the amount of an account
     * - withdraw if amount is negative
     * - deposit if amount is positive
     * @param account
     * @param amount
     */
    public Account updateAmountById(Account account, Double amount){
        Double balance = account.getAmount();
        Double newBalance = balance + amount;
        if (newBalance < 0){
            throw new IllegalArgumentException("Withdrawal failure: No sufficient fund in the account: " +
                    account.getID());
        }
        account.setAmount(newBalance);
        return save(account);
    }

    public Optional<Account> findAccountByTraderID(Integer traderID){
        Optional<Account> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + TRADER_ID_COLUMN +  "= " + traderID + "";
        try {
            entity = Optional.ofNullable((Account) getJdbcTemplate().query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass())));
        } catch (IncorrectResultSizeDataAccessException e ){
            logger.debug("Can't find trader id: " + e);
        }

        return entity;
    }

    @Override
    public int updateOne(Account entity) {
        String updateSQL = "UPDATE " + getTABLE_NAME() + " SET amount=? WHERE id=?";
        return getJdbcTemplate().update(updateSQL, makeUpdateValues(entity));
    }

    private Object[] makeUpdateValues(Account account){
        return new Object[]{account.getAmount(), account.getID()};
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ID_COLUMN;
    }

    @Override
    Class<Account> getEntityClass() {
        return Account.class;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getID_COLUMN() {
        return ID_COLUMN;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return simpleJdbcInsert;
    }

    public void setSimpleJdbcInsert(SimpleJdbcInsert simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }
}