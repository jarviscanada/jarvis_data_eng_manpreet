package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-fb655fbf277b4f62ace18d19fe997bff
 */
public class Fibonacci {

    /**
     * Big-O:
     * Justification:
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
     * Big-O:
     * Justification:
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
