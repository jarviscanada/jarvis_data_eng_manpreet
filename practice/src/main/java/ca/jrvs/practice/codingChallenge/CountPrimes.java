package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Count-Primes-87778c00d7d6422d994bd38abd9fe9b2
 */
public class CountPrimes {

    /**
     * Big-O: O()
     * Justification:
     */
    public int countPrimes(int num) {
        int[] flag = new int[num];
        int count = 0;
        if (num <= 1) {
            throw new IllegalArgumentException("Invalid Imput: Please enter positive number");
        }
        for(int i=2; i<=num; i++){
            for(int j=2; j<=i;j++){
                if(i!=j){
                    if(i%j==0){

                    }
                }
            }
            flag[i]=1;
        }
        for(int i=2; i<=num; i++){
            if(flag[i]==1){
                count++;
            }
        }
    return count;
    }
}
