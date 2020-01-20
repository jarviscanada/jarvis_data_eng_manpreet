package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateArrayTest {

    @Test
    public void sortingApproach() {
        DuplicateArray obj = new DuplicateArray();
        int[] nums = {1,2,3,1};
        boolean actual = obj.sortingApproach(nums);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void checkUsingSet() {
        DuplicateArray obj = new DuplicateArray();
        int[] nums = {1,2,3,1};
        boolean actual = obj.checkUsingSet(nums);
        boolean expected = true;
        assertEquals(expected,actual);
    }
}