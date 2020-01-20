package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/How-to-compare-two-maps-506fe7e4931743d1a827d0560e053c5c
 */
public class CompareMap {

    /**
     * Time Complexity: O(n)
     * Justification: Implementation of equals have O(n) as iterator iterates through the map atmost once.
     *
     */
    public <K,V> boolean compareMapUsingAPI(Map<K,V> map1, Map<K,V> map2){
        return map1.equals(map2);
    }

    /**
     * Time Complexity: O(n)
     * Justification: Iterator iterates through the map atmost once.
     *
     */
    public <K,V> boolean compareMap(Map<K,V> map1, Map<K,V> map2){

            if(map1 == map2){
                return true;
            }

            if (map1.size() != map2.size()) {
                return false;
            }

            for (Map.Entry<K,V> pair : map1.entrySet()) {
                if (!map2.containsKey(pair.getKey())) {
                    return false;
                }
                if (!map2.get(pair.getKey()).equals(pair.getValue())) {
                    return false;
                }
            }

            return true;
    }
}
