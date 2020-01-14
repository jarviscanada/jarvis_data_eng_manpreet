package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Duplicates-from-Sorted-Array-487f8f0c9b4c4412b052f0491cd5e09e
 */
public class RemoveDuplicaties {

    /**
     * Time Complexity: O(n)
     * Justification: Each of I and j loop are traversed one time.
     *
     * Space Complexity: O(1)
     */
    public int removeUsingTwoPointer(int[] nums){
        if (nums.length == 0)
            return 0;

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
