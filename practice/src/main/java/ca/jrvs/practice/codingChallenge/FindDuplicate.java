package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Find-the-Duplicate-Number-e00e7fd3d3954826b8a9e8986f3613ce
 */

public class FindDuplicate {

    /**
     * Time Complexity: O(nlogn)
     * Justification: sorting costs nlogn and iteration through loop costs O(n), Hence,
     * total is O(nlogn)
     *
     * Space Complexity:  O(1)
     * Justification: No extra space used
     */
    public int findDuplicateUsingSort(int[] nums){
        Arrays.sort(nums);
        for(int i=1; i<nums.length; i++){
            if(nums[i-1]==nums[1]){
                return nums[i];
            }
        }
        throw new IllegalArgumentException("Duplicate not found");
    }

    /**
     * Time Complexity: O(n)
     * Justification: loop interation to nums length until n costs O(n)
     *
     * Space Complexity:  O(n)
     * Justification: set correspondes to an extra space that takes upto the length 
     * of nums if there is no duplicate i.e. n
     */
    public int findDuplicatesUsingSet(int[] nums){
        Set<Integer> set = new HashSet<Integer>();
        for(int num: nums){
            if(set.contains(num))
                return num;
            set.add(num);
        }
        throw new IllegalArgumentException("Duplicate not found");
    }
}
