package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPalindromeTest {

    @Test
    public void twoPointer() {
        ValidPalindrome valObj = new ValidPalindrome();
        String inputStr = "A man, a plan, a canal: Panama";
        boolean expected = true;
        boolean actual = valObj.twoPointer(inputStr);
        assertEquals(expected,actual);
    }

    @Test
    public void checkUsingRecursion() {
        ValidPalindrome valObj = new ValidPalindrome();
        String inputStr = "A man, a plan, a canal: Panama";
        boolean expected = true;
        boolean actual = valObj.checkUsingRecursion(inputStr);
        assertEquals(expected,actual);
    }
}