package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class MissingNumberTest {

    @Test
    public void sumAllNumbes() {
        MissingNumber numObj = new MissingNumber();
        int[] nums = {0,3,1,4,2,6};
        int actual= numObj.sumAllNumbes(nums);
        int expected = 5;
        assertEquals(expected,actual);
    }

    @Test
    public void findUsingSet() {
        MissingNumber numObj = new MissingNumber();
        int[] nums = {0,3,1,4,2,6};
        int actual= numObj.findUsingSet(nums);
        int expected = 5;
        assertEquals(expected,actual);
    }

    @Test
    public void findUsingGaussFormula() {
        MissingNumber numObj = new MissingNumber();
        int[] nums = {0,3,1,4,2,6};
        int actual= numObj.findUsingGaussFormula(nums);
        int expected = 5;
        assertEquals(expected,actual);
    }
}