package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinMaxSolutionTest {

    @Test
    public void findByOneLoop() {
        MinMaxSolution obj = new MinMaxSolution();
        int[] nums = { 2,4,6,4,1,8};
        int[] actual = obj.findByOneLoop(nums);
        int[] expected = {8,1};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void findByStream() {
        MinMaxSolution obj = new MinMaxSolution();
        int[] nums = { 2,4,6,4,1,8};
        int[] actual = obj.findByStream(nums);
        int[] expected = {8,1};
        assertArrayEquals(expected,actual);
    }

    @Test
    public void findByCollections() {
        MinMaxSolution obj = new MinMaxSolution();
        int[] nums = { 2,4,6,4,1,8};
        int[] actual = obj.findByCollections(nums);
        int[] expected = {8,1};
        assertArrayEquals(expected,actual);
    }
}