package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramTest {

    @Test
    public void checkAnagramUsingSort() {
        Anagram obj = new Anagram();
        String str1 = "anagram";
        String str2 = "nagaram";
        boolean actual = obj.checkAnagramUsingSort(str1,str2);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void checkAnagramUsingHashtable() {
        Anagram obj = new Anagram();
        String str1 = "anagram";
        String str2 = "nagaram";
        boolean actual = obj.checkAnagramUsingHashtable(str1,str2);
        boolean expected = true;
        assertEquals(expected,actual);
    }
}
