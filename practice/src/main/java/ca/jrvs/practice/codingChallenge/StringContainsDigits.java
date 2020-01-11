package ca.jrvs.practice.codingChallenge;


/**

 * ticket: https://www.notion.so/Check-if-a-String-contains-only-digits-509549ae9daf42ecbbe73ec21a401759
 */
public class StringContainsDigits {

    /**
     * Big-O: Time complexity : O(n)
     * Justification: the whole string is travered with length n
     *
     **/
    public boolean checkDigitsUsingAscii(String str){
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) < 48 || str.charAt(i) > 57)
                return false;
        }
        return true;
    }

    /**
     * Big-O: Time complexity : O(n)
     * Justification: In the implementation of vaueOf, the whole string is traversed.
     *
     **/
    public boolean checkDigitsUsingApi(String str){
        try{
            Integer.valueOf(str);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    /**
     * Big-O: Time complexity : O(n)
     * Justification:Running a DFA-compiled regular expression against a string is indeed O(n),
     * but can require up to O(2^n) construction time/space (where n = regular expression size).
     *
     **/
    public boolean checkDigitsUsingRegex(String str){
        return str.matches("^[0-9]*$");
    }
}
