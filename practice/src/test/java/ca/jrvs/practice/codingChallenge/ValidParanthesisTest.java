package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParanthesisTest {

    @Test
    public void validParanthesis() {
        String input = "[]()";
        ValidParanthesis vp = new ValidParanthesis();
        boolean actual = vp.validParanthesis(input);
        assertTrue(actual);

        input = "[{]}";
        actual = vp.validParanthesis(input);
        assertFalse(actual);
    }
}