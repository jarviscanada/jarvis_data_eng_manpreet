package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

    public static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

    abstract public JdbcTemplate getJdbcTemplate();

    abstract public SimpleJdbcInsert getSimpleJdbcInsert();

    abstract public String getTableName();

    abstract public String getIdColumnName();

    abstract Class<T> getEntityClass();

    @Override
    public <S extends T> S save(S entity) {
        if (existsById(entity.getID())){
            int updateRowNum = updateOne(entity);
            if (updateRowNum != 1){
                throw new DataRetrievalFailureException("Unable to update quote.");
            }
        } else {
            addOne(entity);
        }
        return entity;
    }

    private <S extends T> void addOne(S entity){
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
        Object newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
        entity.setID((Integer) newId);
    }

    abstract public int updateOne(T entity);

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        for (S entity : entities) {
            save(entity);
        }
        return entities;
    }

    @Override
    public Optional<T> findById(Integer id) {
        Optional<T> entity = Optional.empty();
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        try {
            entity = Optional.ofNullable((T) getJdbcTemplate().
                    queryForObject(selectSql,
                            BeanPropertyRowMapper.newInstance(getEntityClass()), id));
        } catch (IncorrectResultSizeDataAccessException e ){
            logger.debug("Can't find trader id: " + id, e);
        }
        return entity;
    }

    @Override
    public boolean existsById(Integer id) {
        String selectSql = "SELECT * FROM " + getTableName() + " WHERE "+ getIdColumnName() +" = " + id ;
        List<T> entities =  getJdbcTemplate()
                .query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()));
        if(entities.size()==1) {
            T outQuote = entities.get(0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterable<T> findAll() {
        String selectSql = "SELECT * FROM " + getTableName();
        List<T> entities =  getJdbcTemplate()
                .query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()));
        return entities;
    }

    @Override
    public Iterable<T> findAllById(Iterable<Integer> ids) {
        List<T> entities = new ArrayList<>();
        for (int id : ids) {
            entities.add(findById(id).orElseThrow(IllegalArgumentException::new));
        }
        return entities;
    }

    @Override
    public long count() {
        long count = 0;
        String sql = "SELECT COUNT(*) FROM " + getTableName();
        return getJdbcTemplate()
                .queryForObject(sql, Long.class);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";
        getJdbcTemplate().update(deleteSql, id);
    }

    @Override
    public void delete(T entity) {
        throw new UnsupportedOperationException("Not implemented...");
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        throw new UnsupportedOperationException("Not implemented...");
    }

    @Override
    public void deleteAll() {
        String deleteSql = "DELETE FROM " + getTableName() ;
        getJdbcTemplate().update(deleteSql);
    }
}
