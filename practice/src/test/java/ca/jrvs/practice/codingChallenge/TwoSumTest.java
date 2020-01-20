package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest {

    @Test
    public void twoSumBruteForce() {
        TwoSum twoSum = new TwoSum();
        int[] nums = {3,1,4,9,8,5};
        int target = 6;
        int[] actual = twoSum.twoSumBruteForce(nums,target);
        int[] expected = {1,5};
        assertArrayEquals(actual,expected);
    }

    @Test
    public void twoSumBySorting() {
        TwoSum twoSum = new TwoSum();
        int[] nums = {3,1,4,9,8,5};
        int target = 6;
        int[] actual = twoSum.twoSumBySorting(nums,target);
        int[] expected = {1,5};
        assertArrayEquals(actual,expected);
    }

    @Test
    public void twoSumByMap() {
        TwoSum twoSum = new TwoSum();
        int[] nums = {3,1,4,9,8,5};
        int target = 6;
        int[] actual = twoSum.twoSumByMap(nums,target);
        int[] expected = {1,5};
        assertArrayEquals(actual,expected);
    }



}