package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Duplicate-Characters-bb5f2398240746029b0d6aeb6cd123ab
 */
public class DuplicateCharacters {

    /**
     * Time Complexity: O()
     * Justification:
     *
     * Space Complexity: O()
     */
    public String[] findDuplicateCharacters(String inputStr){
        Set<String> set = new HashSet<>(inputStr.length());
        StringBuilder outStr = new StringBuilder();
        String[] output = null;

        // Creating array of string length
        char[] ch = new char[inputStr.length()];

        // Copy character by character into array
        for (int i = 0; i < inputStr.length(); i++) {
            ch[i] = inputStr.charAt(i);
        }

        for (char c : ch) {
            outStr.append(c);
            if (set.contains(c)) {
                output = outStr.toString().split("");
            }
        }
        return output;

    }
}
