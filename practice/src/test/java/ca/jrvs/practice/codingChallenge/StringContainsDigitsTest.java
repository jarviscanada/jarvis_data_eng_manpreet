package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringContainsDigitsTest {

    @Test
    public void checkDigitsUsingAscii() {
        StringContainsDigits scd = new StringContainsDigits();
        String str = "123,000";
        boolean actual = scd.checkDigitsUsingAscii(str);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void checkDigitsUsingApi() {
        StringContainsDigits scd = new StringContainsDigits();
        String str = "123,000";
        boolean actual = scd.checkDigitsUsingApi(str);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void checkDigitsUsingRegex() {
        StringContainsDigits scd = new StringContainsDigits();
        String str = "123,000";
        boolean actual = scd.checkDigitsUsingRegex(str);
        boolean expected = false;
        assertEquals(expected,actual);
    }
}