package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import javax.sql.DataSource;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.Optional;

@Repository
public class PositionDao extends JdbcCrudDao<Position>{

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);

    private final String TABLE_NAME = "position";
    private final String ID_COLUMN = "account_id";
    private final String TICKER_COLUMN = "ticker";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public PositionDao(DataSource dataSource){
        setJdbcTemplate(new JdbcTemplate(dataSource));
        setSimpleJdbcInsert(new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN));
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
    Class<Position> getEntityClass() {
        return Position.class;
    }


    public Optional<Position> getByAccountIdAndTicker(Integer accountId, String ticker) {
        Optional<Position> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "= " + accountId +
                " AND " + TICKER_COLUMN + "='" + ticker + "'";
        try {
            entity = Optional.ofNullable((Position) getJdbcTemplate().
                    queryForObject(selectSql,
                            BeanPropertyRowMapper.newInstance(getEntityClass())));
        } catch (IncorrectResultSizeDataAccessException e ){
            logger.debug("Can't find Position id: " + accountId + ", ticker:" + ticker, e);
        }
        return entity;
    }

    /**
     * finds position by account ID by trader id
     * @param accountID
     */
    public List<Position> findPositionByAccountID(Integer accountID){
        List<Position> entities = null;
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "= " + accountID ;

        try {
            entities = getJdbcTemplate().
                    query(selectSql,
                            BeanPropertyRowMapper.newInstance(getEntityClass()));
        } catch (IncorrectResultSizeDataAccessException e ){
            logger.debug("Can't find Position by account ID", e);
        }
        return entities;
    }

    @Override
    public int updateOne(Position position) {
       throw new UnsupportedOperationException("Update not supported");
    }

    public static Logger getLogger() {
        return logger;
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