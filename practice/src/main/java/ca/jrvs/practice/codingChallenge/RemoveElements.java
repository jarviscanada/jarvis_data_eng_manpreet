package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/Remove-Element-d04ca7a20fd14196b4a326d398a13472
 */
public class RemoveElements {

    /**
     * Big-O: O(n)
     * Justification: array length to be n and both i and j are traversed atmost n+n time
     *
     */
    public int removeUsingTwoPointer(int[] nums, int val){
        int  i = 0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }
}
