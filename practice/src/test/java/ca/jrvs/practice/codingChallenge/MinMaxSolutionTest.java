package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void findByOneLoop() {
        Solution obj = new Solution();
        int[] nums = { 2,4,6,4,1,8};
        int[] actual = obj.findByOneLoop(nums);
        int[] expected = {8,1};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void findByStream() {
        Solution obj = new Solution();
        int[] nums = { 2,4,6,4,1,8};
        int[] actual = obj.findByStream(nums);
        int[] expected = {8,1};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void findByCollections() {
        Solution obj = new Solution();
        int[] nums = { 2,4,6,4,1,8};
        int[] actual = obj.findByCollections(nums);
        int[] expected = {8,1};
        assertArrayEquals(expected,actual);
    }
}