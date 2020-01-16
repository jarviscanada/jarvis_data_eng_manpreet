package ca.jrvs.practice.codingChallenge;

import java.util.*;

/**
 * ticket: https://www.notion.so/Duplicate-Characters-bb5f2398240746029b0d6aeb6cd123ab
 */
public class DuplicateCharacters {

    /**
     * Time Complexity: O(n)
     * Justification: Traverse over string once and traverse on Hashmap which implies complexity to be O(n+n)~O(n)
     *
     * Space Complexity: O(n)
     * Justification: Extra space is required for HashMap and List which is O(n+n) = O(n)
     */
    public String[] findDuplicateCharacters(String inputStr){
        HashMap<String, Integer> map = new HashMap<>();
        inputStr = inputStr.toLowerCase();

        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) < 'a' || inputStr.charAt(i) > 'z') continue;
            String character = String.valueOf(inputStr.charAt(i));
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        List<String> duplicates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) duplicates.add(entry.getKey());
        }

        String[] duplicatesArray = duplicates.toArray(new String[duplicates.size()]);
        return duplicatesArray;

    }
}
