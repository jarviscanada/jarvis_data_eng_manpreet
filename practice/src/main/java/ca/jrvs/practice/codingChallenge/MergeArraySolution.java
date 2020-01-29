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

    /**
     * Time Complexity: O(n)
     * Justification: It iterates through the total length atmost once.
     *
     * Space Complexity: O(n)
     * Justification: extra space for result is n+m where n is length of nums1 and m is length is nums2
     */
    public int[] mergeSortedArrayApproach2(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int[] result = new int[totalLength];
        int p1 = 0, p2 = 0;
        for (int i = 0; i < totalLength; i++) {
            if (p1 >= nums1.length)
                result[i] = nums2[p2++];
            else if (p2 >= nums2.length)
                result[i] = nums1[p1++];
            else if (nums1[p1] > nums2[p2])
                result[i] = nums2[p2++];
            else {
                result[i] = nums1[p1++];
            }

        }
        return result;
    }
}
