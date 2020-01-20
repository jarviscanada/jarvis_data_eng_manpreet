package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuplicateCharactersTest {

    @Test
    public void findDuplicateCharacters() {
        DuplicateCharacters obj = new DuplicateCharacters();
        String input ="A black cat";
        String[] expected = {"a", "c"};
        String[] actual = obj.findDuplicateCharacters(input);
        assertArrayEquals(expected,actual);

    }
}