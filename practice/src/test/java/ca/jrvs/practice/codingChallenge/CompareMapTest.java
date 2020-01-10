package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import sun.tools.jar.CommandLine;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CompareMapTest {

    @Test
    public void compareMapUsingAPI() {
        CompareMap maps = new CompareMap();
        HashMap<Integer,String> map1 = new HashMap<>();
        map1.put(12,"name");
        HashMap<Integer,String> map2 = new HashMap<>();
        map2.put(12,"yo");
        boolean actual = maps.compareMapUsingAPI(map1,map2);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void compareMap() {
    }
}