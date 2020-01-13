package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Missing-Number-8c133fd16e3a473d8be24d2e98108cd6
 */
public class MissingNumber {

    /**
     * Time Complexity: O(n)
     * Justification: loop to find array sum runs O(n).
     * Another loop to find the total is also runs till n
     * its complexity is O(n+n) = O(n)
     */
    public int sumAllNumbes(int[] nums){
        int arraySum=0;
        int length = nums.length;
        int total=0;

        for(int i=0; i<length+1; i++){
            total= total + i;
        }

        for(int num: nums){
            arraySum= arraySum + num;
        }

        return (total- arraySum);

    }

    /**
     *Time Complexity: O(n)
     * Justification: Set allows O(1) queries,the main loop runs in O(n) time.
     * Creating num_set costs O(n) time.so overall complexity is O(n+n)=O(n)
     *
     * Space Complexity: O(n)
     * Justification: contains n-1 distinct elements
     */
    public int findUsingSet(int[] nums){
        Set<Integer> numSet = new HashSet<>();
        try{
            for(int num: nums){
                numSet.add(num);
            }
        }catch (ArithmeticException ex){
            throw new ArithmeticException("Integer Overflow");
        }


        int length = nums.length+1;
        for (int number=0;number<length;number++){
            if(!numSet.contains(number))
                return number;
        }
        throw new IllegalArgumentException("NUmber not Found");
    }

    /**
     * Time Complexity: O(n)
     * Justification: Although Gauss folmula can be computed using O(n),
     * summing nums costs O(n) time.
     *
     * Space Complexity: O(1)
     * Justification: This approach only pushes a few integers around, so it has constant memory usage.
     */
    public int findUsingGaussFormula(int[] nums){
        int arraySum = 0;
        int length = nums.length;
        int total = length*(length+1)/2;
        for(int num: nums){
            arraySum = arraySum+num;
        }
        return (total-arraySum);
    }


}
