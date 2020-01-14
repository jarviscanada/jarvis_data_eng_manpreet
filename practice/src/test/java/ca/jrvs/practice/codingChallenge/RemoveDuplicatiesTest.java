package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveDuplicatiesTest {

    @Test
    public void removeUsingTwoPointer() {
        RemoveDuplicaties obj = new RemoveDuplicaties();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int actual = obj.removeUsingTwoPointer(nums);
        int expected = 5;
        assertEquals(expected,actual);
    }
}