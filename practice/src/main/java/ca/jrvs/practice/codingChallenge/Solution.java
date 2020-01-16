package ca.jrvs.practice.codingChallenge;


import java.util.Arrays;

/**
 * ticket: https://www.notion.so/Valid-Anagram-8502c12d4d114e5d96136ec265899d79
 */

public class Solution {

    /**
     * Time Complexity: O(nlogn)
     * Justification: sorting costs nlogn and itering through foor loop atmost once until n is On
     * hence, nlogn
     *
     * Space Complexity: O(1)
     * Justification: toCharArray() makes a copy of the string so it costs O(n)O(n) extra space,
     * but we ignore this for complexity analysis because:     *
     * -It is a language dependent detail.
     * -It depends on how the function is designed.
     * For example, the function parameter types can be changed to char[].
     */
    public boolean checkAnagramUsingSort(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1,charArray2);
    }


    /**
     * Time Complexity: O(n)
     * Justification:2 for loop costs us n+n which is O(n).
     *
     * Space Complexity: O(1)
     * Justification:  Although we do use extra space, the space complexity is O(1)
     * because the table's size stays constant no matter how large nn is.
     */
    public boolean checkAnagramUsingHashtable(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            counter[str1.charAt(i) - 'a']++;
            counter[str1.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0)
                return false;
        }
        return true;
    }
}
