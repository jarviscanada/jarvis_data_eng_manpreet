package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void checkAnagramUsingSort() {
        Solution obj = new Solution();
        String str1 = "anagram";
        String str2 = "nagaram";
        boolean actual = obj.checkAnagramUsingSort(str1,str2);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void checkAnagramUsingHashtable() {
        Solution obj = new Solution();
        String str1 = "anagram";
        String str2 = "nagaram";
        boolean actual = obj.checkAnagramUsingHashtable(str1,str2);
        boolean expected = true;
        assertEquals(expected,actual);
    }
}