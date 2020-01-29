package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/Rotate-String-7b8b3bd28c5948c4ba9b8a31d3d0d5ef
 */
public class RotateString {

    /**
     * Time complexity : O(n^2)
     * Justification: n is the length of str1
     *
     * Space Complexity: O(n)
     * Justification: the space used building str1+str1
     **/
    public boolean rotateStringSimpleCheck(String str1, String str2){
        return str1.length() == str2.length() && (str1 + str1).contains(str2);
    }
}
