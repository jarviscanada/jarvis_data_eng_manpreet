package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-fb655fbf277b4f62ace18d19fe997bff
 */
public class Fibonacci {

    /**
     * Time Complexity: O(2^n)
     * Justification: in this recursive operation, operations are traversed as a recussion tree whose complexity is exponential.
     *
     * Space Complexity: O(n)
     * Justification: Length of recursion tree to be n, hence takes n spaces
     */
    public int recursiveFibonacci(int totalNum){
        int result;
        if(totalNum== 0 || totalNum == 1){
            result = 1;
        }else{
            result = recursiveFibonacci(totalNum-1) + recursiveFibonacci(totalNum - 2);
        }
        return result;
    }

    /**
     * Time Complexity: O(n)
     * Justification: the series is traversed only ones
     *
     * Space Complexity: O(n)
     * Justification: Extra space is required.
     */
    public int dynamicFibonacci(int totalNum){

        int[] result = new int[totalNum];
        if(totalNum == 0 || totalNum == 1){
            return 1;
        }
        result[0]=1;
        result[1]=1;
        for(int i = 2 ; i < totalNum ; i++){
            result[i] = result[i-1] + result[i-2];
        }
        return result[totalNum-1];
    }

}
