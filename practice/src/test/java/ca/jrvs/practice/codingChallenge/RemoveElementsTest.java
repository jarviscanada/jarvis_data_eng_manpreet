package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveElementsTest {

    @Test
    public void removeUsingTwoPointer() {
        RemoveElements obj = new RemoveElements();
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int actual = obj.removeUsingTwoPointer(nums, val);
        int expected = 5;
        assertEquals(expected,actual);
    }
}