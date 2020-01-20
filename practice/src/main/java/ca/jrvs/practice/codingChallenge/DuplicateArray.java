package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Contains-Duplicate-10c0f81d8cd24ee984a814b0d13b241f
 */
public class DuplicateArray {

    /**
     * Time Complexity: O(nlogn)
     * Justification: sorting complexity is O(nlogn) and traversing is O(n)
     *
     * Space Complexity: O(1)
     * Justification:  Space depends on the sorting implementation which,
     * usually, costs O(1) auxiliary space if heapsort is used.
     */
    public boolean sortingApproach(int[] nums){
        Arrays.sort(nums);
        for (int i=0; i< nums.length; i++){
            if(nums[i]==nums[i+1]){
                return true;
            }
        }return false;
    }

    /**
     * Time Complexity: O(n)
     * Justification: search() and insert() of n takes O(n)
     *
     * Space Complexity:  O(n)
     * Justification: The space used by a hash table is linear with the number of elements in it.
     */
    public boolean checkUsingSet(int[] nums){
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num: nums){
            if(set.contains(num))
                return true;
            set.add(num);
        }
        return false;
    }
}
