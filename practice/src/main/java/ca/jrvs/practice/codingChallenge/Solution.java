package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Find-the-Duplicate-Number-e00e7fd3d3954826b8a9e8986f3613ce
 */

public class Solution {

    /**
     * Time Complexity: O()
     * Justification:
     *
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
     * Time Complexity: O()
     * Justification:
     *
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
