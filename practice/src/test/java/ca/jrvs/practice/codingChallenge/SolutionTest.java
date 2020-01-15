package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void findDuplicateUsingSort() {
        Solution obj = new Solution();
        int[] nums = {1,2,2,3,4};
        int actual = obj.findDuplicatesUsingSet(nums);
        int expected = 2;
        assertEquals(expected,actual);
    }

    @Test
    public void findDuplicatesUsingSet() {
        Solution obj = new Solution();
        int[] nums = {1,2,2,3,4};
        int actual = obj.findDuplicatesUsingSet(nums);
        int expected = 2;
        assertEquals(expected,actual);
    }
}