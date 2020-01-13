package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountPrimesTest {

    @Test
    public void countPrimes() {
        CountPrimes primeObj = new CountPrimes();
        int actual = primeObj.countPrimes(10);
        int expected = 4;
        assertEquals(expected,actual);
    }
}