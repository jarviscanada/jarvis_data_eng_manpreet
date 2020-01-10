package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/Two-Sum-916555352ece41f7b0777426b9a7f3fa
 */
public class TwoSum {

/**
 * Big-O: Time complexity : O(n^2)
 * Justification: we run loops to find complement
 *
 * Big-O: Space complexity : O(1)
 **/
public int[] twoSumBruteForce(int[] nums, int target){
    for(int i= 0; i< nums.length; i++){
        for (int j=i+1; j < nums.length; j++){
            if(nums[j]== target-nums[i])
                return new int[]{i,j};
        }
    }
    throw new RuntimeException("No two sum target found");
}

    /**
     * Big-O: Time complexity : O(n*log(n))
     * Justification: traversing in the soring array
     **/
public int[] twoSumBySorting(int[] nums, int target) {
    Arrays.sort(nums);
    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
        if (nums[left] + nums[right] == target) {
            return new int[]{left, right};
        } else if (nums[left] + nums[right] < target) {
            left++;
        } else {
            right--;
        }
    }
    throw new RuntimeException("No two sum target found");
}

    /**
     * Big-O: Time complexity : O(n)
     * Justification: traversing list containing n elements only once.
     **/
    public int[] twoSumByMap(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int complement = target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            else{
                map.put(nums[i],i);
            }
        }
       throw new RuntimeException("No two sum target found");
    }
}

