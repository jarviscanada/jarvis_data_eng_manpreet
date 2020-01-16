package ca.jrvs.practice.codingChallenge;

import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class FindDuplicateTest {

    @Test
    public void findDuplicateUsingSort() {
        FindDuplicate obj = new FindDuplicate();
        int[] nums = {1,2,2,3,4};
        int actual = obj.findDuplicatesUsingSet(nums);
        int expected = 2;
        assertEquals(expected,actual);
    }

    @Test
    public void findDuplicatesUsingSet() {
        FindDuplicate obj = new FindDuplicate();
        int[] nums = {1,2,2,3,4};
        int actual = obj.findDuplicatesUsingSet(nums);
        int expected = 2;
        assertEquals(expected,actual);
    }
}
