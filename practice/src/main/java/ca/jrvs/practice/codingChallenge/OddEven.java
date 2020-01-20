package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Sample-Check-if-a-number-is-even-or-odd-ea9f240e96b649ee8a74f931c77d6fc4
 */

public class OddEven {

    /**
     * Big-O: O(1)
     * Justification: it's an arithmetic operation
     */
    public String oddEvenMod(int i){
        return i % 2 == 0 ? "even" : "odd";
    }

    /**
     * Big-O: O(1)
     * Justification: it's an bitwise operation
     */
    public String oddEvenBit(int i){
        return (i & 1) == 0 ? "even" : "odd";
    }

}
