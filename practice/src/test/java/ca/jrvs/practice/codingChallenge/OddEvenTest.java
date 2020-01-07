package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class OddEvenTest {

    @Test
    public void testOddEvenMod() {
        OddEven oddEven = new OddEven();
        String expected = "even";
        String actual = oddEven.oddEvenMod(2);
        assertEquals(expected, actual);

    }

    @Test
    public void testOddEvenBit() {
        OddEven oddEven = new OddEven();
        String expected = "odd";
        String actual = oddEven.oddEvenBit(311);
        assertEquals(expected, actual);

    }
}