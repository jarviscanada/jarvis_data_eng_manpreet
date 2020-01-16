package ca.jrvs.practice.codingChallenge;

import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ticket: https://www.notion.so/Find-Largest-Smallest-d9c2bfdf936b4d718046bb212600b8b0
 */
public class Solution {

    /**
     * Time Complexity:O(n)
     * Justification: for loop costs O(n) that traverses the num of length atmost once
     *
     * Space Complexity: O(1)
     * Justification: No extra space apart from max and min is used which is constant
     */
    public int[] findByOneLoop(int[] nums){
        int max = nums[0];
        int min = nums[0];

        for(int i=1;i<nums.length;i++){
            if(nums[i]>max)
                max=nums[i];
            if(nums[i]<min)
                min=nums[i];
        }
        return new int[]{max,min};
    }

    /**
     * Time Complexity:O(n)
     * Justification: The stream filtering uses iteration internally to find min and max
     *
     * Space Complexity: O(1)
     * Justification: No extra space apart from mix and max is used which is constant
     */
    public int[] findByStream(int[] nums){
        int min = Arrays.stream(nums)
                .min()
                .getAsInt();

        int max = Arrays.stream(nums)
                .max()
                .getAsInt();

        return new int[]{max,min};
    }

    /**
     * Time Complexity:O(n)
     * Justification: Iteration is used to convert the int[] to Integer and Collections also uses iteration
     * internally to find min and max
     *
     * Space Complexity: O(n)
     * Justification: Extra space is required to store the generate Integer list
     */
    public int[] findByCollections(int[] nums) {
        List<Integer> list  = Arrays.stream( nums ).boxed().collect( Collectors.toList() );
        int max = Collections.max(list);
        int min = Collections.min(list);
        return new int[] {max, min};
    }
}
