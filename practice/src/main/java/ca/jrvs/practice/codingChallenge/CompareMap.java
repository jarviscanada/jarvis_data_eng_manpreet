package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/How-to-compare-two-maps-506fe7e4931743d1a827d0560e053c5c
 */
public class CompareMap {

    public boolean compareMapUsingAPI(HashMap<Integer,String> map1, HashMap<Integer,String> map2){
        return map1.equals(map2);
    }

    public boolean compareMap(HashMap<Integer,String> map1, HashMap<Integer,String> map2){
        return false;
    }
}
