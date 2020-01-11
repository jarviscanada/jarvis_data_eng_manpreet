package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Swap-two-numbers-b666b7d362c243968db031c5fe63a7b2
 */
public class SwapTwoNumbers {

    /**
     * Big-O: O(1)
     * Justification: it's a bitwise operation
     */
    public int[] swapNmbersBitManipulation(int[] num){
        if(num.length>2){
            throw new IllegalArgumentException("Invalid Input: Numbers more than 2");
        }
        num[0] = num[0] ^ num[1];
        num[1] = num[0] ^ num[1];
        num[0] = num[0] ^ num[1];
        return num;
    }

    /**
     * Big-O: O(1)
     * Justification: it's an arithmetic operation
     */
    public int[] swapNmbersArithemeticOperation(int[] num){
        if(num.length>2){
            throw new IllegalArgumentException("Invalid Input: Numbers more than 2");
        }
        num[0] = num[0] + num[1];
        num[1] = num[0] - num[1];
        num[0] = num[0] - num[1];
        return num;
    }

}
