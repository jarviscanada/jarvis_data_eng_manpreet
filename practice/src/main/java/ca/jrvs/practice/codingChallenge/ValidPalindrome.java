package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Valid-Palindrome-c1bd1be1f7ad446fb62042c5a2438e1a
 */
public class ValidPalindrome {

    /**
     * Big-O: O()
     * Justification:
     */
    public boolean twoPointer(String inputStr) {
        String str = inputStr.toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        StringBuilder realStr = new StringBuilder();
        StringBuilder revStr = new StringBuilder();

        while (left <= right) {
            if (Character.isLetterOrDigit(str.charAt(left))) {
                realStr.append(str.charAt(left));
            }
            left++;
        }

        left = 0;
        while (right >= left) {
            if (Character.isLetterOrDigit(str.charAt(right))) {
                revStr.append(str.charAt(right));
            }
            right--;
        }

        if (realStr.toString().equals(revStr.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsingRecursion(String inputStr){
        String str = inputStr.toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        StringBuilder realStr = new StringBuilder();

        while (left <= right) {
            if (Character.isLetterOrDigit(str.charAt(left))) {
                realStr.append(str.charAt(left));
            }
            left++;
        }

        boolean result = applyRecursion(realStr.toString());
        return result;

    }

    public boolean applyRecursion(String str){
        if(str.length() ==0 || str.length()==1)
            return true;

        if(str.charAt(0)==str.charAt(str.length()-1)){
            return applyRecursion(str.substring(1, str.length()-1));
        }
        return false;
    }

}
