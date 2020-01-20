package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class LetterWithNumberTest {

    @Test
    public void printLetterWithNumber() {
        LetterWithNumber ob = new LetterWithNumber();
        String str = "abcee";
        String expected = "a1b2c3e5e5";
        String actual = ob.printLetterWithNumber(str);
        assertEquals(expected,actual);
    }
}