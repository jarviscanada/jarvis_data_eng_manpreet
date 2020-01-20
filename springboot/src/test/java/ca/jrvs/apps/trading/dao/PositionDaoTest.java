package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Position;
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
    private PositionDao positionDao;

    private Position position;


    @Before
    public void insertOne() {
//        positionDao.deleteAll();
//        position = new Position();
//        position.setPosition(11);
//        position.setTicker("AAPL");
//        positionDao.save(position);
//

    }


    @Test
    public void findAllById() {
        List<Integer> ids= Arrays.asList(position.getID());
        Iterable<Position> savedPositions =positionDao.findAllById(ids);
        List<Position> positions = Lists.newArrayList(savedPositions);
        assertEquals(1, positions.size());
        assertEquals(position.getTicker(), positions.get(0).getTicker());
        assertEquals(position.getPosition(), positions.get(0).getPosition());
    }

    @Test
    public void saveAll() {
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
    }

    @Test
    public void findById() {
        Position savedPostion = positionDao.findById(position.getID()).get();
        assertEquals(position.getTicker(), savedPostion.getTicker());
        assertEquals(position.getPosition(), savedPostion.getPosition());

    }

    @Test
    public void existsById() {
        List<Position> positions = Lists.newArrayList(positionDao.findAll());
        for(Position position: positions) {
            System.out.println("Id:" + position.getID());
        }
        assertTrue(positionDao.existsById(position.getID()));
    }

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


    @Test
    public void deleteAll() {
//        positionDao.deleteAll();
//        assertEquals(0, positionDao.count());
    }

    @After
    public void deleteOne() {
        positionDao.deleteById(position.getID());
    }

}