package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Count-Primes-87778c00d7d6422d994bd38abd9fe9b2
 */
public class CountPrimes {

    /**
     * Time Complexity
     * Justification:
     *
     * Space Complexity: O(n)
     * Justification: extra space of type boolean array with length n is used
     */
    public int countPrimes(int num) {
        boolean[] isPrime = new boolean[num];
        for (int i = 2; i < num; i++) {
            isPrime[i] = true;
        }

        //  50
        for (int i = 2; i * i < num; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < num; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < num; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
