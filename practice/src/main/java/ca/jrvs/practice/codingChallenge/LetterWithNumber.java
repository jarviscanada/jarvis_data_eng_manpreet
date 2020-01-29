package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Print-letter-with-number-ad0af19e6be0481796b8edbf53255fdd
 */
public class LetterWithNumber {

    /**
     * Big-O: Time complexity : O(n)
     * Justification: The whole string is traversed.
     *
     **/
    public String printLetterWithNumber(String input) {
        StringBuilder newStr = new StringBuilder();
        if (input.contains(" ")) {
            throw new IllegalArgumentException("Invalid Input: string conains spaces");
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                newStr.append(input.charAt(i))
                        .append(input.charAt(i) - 96);
            } else if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                newStr.append(input.charAt(i))
                        .append(input.charAt(i) - 64);
            } else{
                throw new IllegalArgumentException("Invalid Input");
            }
        }
        return newStr.toString();
    }

}
