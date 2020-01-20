package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/Merge-Sorted-Array-af5cfc0abe7d448a935f92fb37178d1a
 */
public class MergeArraySolution {

    /**
     * Time Complexity: O(nlogn)
     * Justification: Arrays.sort has nlogn and it iterates through the nums1 length atmost once.
     *
     * Space Complexity: O(1)
     * Justification: No extra space is used
     */
    public int[] mergeSortedArray(int[] nums1, int m, int[] nums2, int n){
            for(int i=0;i<nums2.length;i++){
                nums1[i+m]=nums2[i];
            }
            Arrays.sort(nums1);
            return nums1;
    }

}
