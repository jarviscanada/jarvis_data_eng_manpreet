package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwapTwoNumbersTest {

    @Test
    public void swapNmbersBitManipulation() {
        SwapTwoNumbers swapObj = new SwapTwoNumbers();
        int[] inputNum = {2,5};
        int[] expected = {5,2};
        int[] actual = swapObj.swapNmbersBitManipulation(inputNum);
        assertArrayEquals(expected,actual);

    }

    @Test
    public void swapNmbersArithemeticOperation() {
        SwapTwoNumbers swapObj = new SwapTwoNumbers();
        int[] inputNum = {2,5};
        int[] expected = {5,2};
        int[] actual = swapObj.swapNmbersArithemeticOperation(inputNum);
        assertArrayEquals(expected,actual);
    }
}