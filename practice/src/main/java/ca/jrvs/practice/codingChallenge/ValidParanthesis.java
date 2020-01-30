package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

public class ValidParanthesis {
    public boolean validParanthesis(String input) {
        HashMap<Character, Character> paranthesis = new HashMap<>();
        paranthesis.put(')', '(');
        paranthesis.put('}', '{');
        paranthesis.put(']', '[');


        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);

            // Check for opening bracked
            if (paranthesis.containsValue(c)) {
                stack.push(c);
            } else {
                if (stack.empty()) return false;
                Character top = stack.pop();
                if (paranthesis.get(c) != top) {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
