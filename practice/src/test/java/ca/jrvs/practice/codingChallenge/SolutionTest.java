package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void mergeSortedArray() {
        Solution obj = new Solution();
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        int[] actual = obj.mergeSortedArray(nums1,m,nums2,n);
        int[] expected = {1,2,2,3,5,6};
        assertArrayEquals(expected,actual);
    }
}