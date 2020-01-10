package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringToIntegerTest {

    @Test
    public void atoiBuiltIn() {
        StringToInteger atoi = new StringToInteger();
        int actual = atoi.atoiBuiltIn("-34 nklo");
        int expected = -34;
        assertEquals(expected,actual);
    }

    @Test
    public void atoiWithoutBuildIn() {
        StringToInteger atoi = new StringToInteger();
        int actual = atoi.atoiWithoutBuildIn("-34 nklo");
        int expected = -34;
        assertEquals(expected,actual);
    }
}