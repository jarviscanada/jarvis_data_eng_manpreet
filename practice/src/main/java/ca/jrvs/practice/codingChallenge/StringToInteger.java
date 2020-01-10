package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/String-to-Integer-atoi-ab4890c11e834e35bb5788d6f59b291e
 */
public class StringToInteger {

    /**
     * Big-O: Time complexity : O(n)
     * Justification: string of length n is traversed only once
     *
     **/
    public int atoiBuiltIn(String str){
        int i = 0;
        StringBuilder newStr = new StringBuilder();
        str = str.trim();
        if(!(Character.isDigit(str.charAt(0)) || str.charAt(0) == '-')){
           return 0;
        }
        if (str.charAt(0)== '-') {
            newStr.append(str.charAt(0));
            i++;
        }
        int right = str.length()-1;
        while(i<=right){
            if (str.charAt(i)>=48 && str.charAt(i)<=57){
                newStr.append(str.charAt(i));
            }
            i++;
        }

        try{
            return Integer.parseInt(newStr.toString());
        }catch(IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid Atoi");
        }
    }

    /**
     * Big-O: Time complexity : O(n)
     * Justification: string of length n is traversed only once
     *
     **/
    public int atoiWithoutBuildIn(String str){
        int i = 0;
        int num = 0;
        boolean negative = false;
        str = str.trim();
        if(!(Character.isDigit(str.charAt(0)) || str.charAt(0) == '-')){
            return 0;
        }
        if (str.charAt(0)== '-') {
            negative = true;
            i++;
        }
        int right = str.length()-1;
        while(i<=right){
            if (str.charAt(i)>=48 && str.charAt(i)<=57){
                int val = str.charAt(i) % 48;
                num = num == 0 ?  val: (num * 10 + val);
            }
            i++;
        }
        if (negative) num = num * -1;
        return num;
    }


}

