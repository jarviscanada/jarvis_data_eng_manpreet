package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotateStringTest {

    @Test
    public void rotateStringSimpleCHeck() {
        RotateString rotateString = new RotateString();
        String str1 = "cdeab";
        String str2 = "abcde";
        boolean actual = rotateString.rotateStringSimpleCheck(str1,str2);
        boolean expected = true;
        assertEquals(expected,actual);
    }
}