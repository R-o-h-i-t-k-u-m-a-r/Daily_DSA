//Leetcode : 2825. Make String a Subsequence Using Cyclic Increments


class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        // Appraoch : Using Two Pointer
        int i = 0; //pointer for str1
        int j = 0; //pointer for str2
        while(i<str1.length() && j<str2.length()){
            char ch = str1.charAt(i);
            char nextChar = (char)((ch + 1 - 'a')%26 + 'a'); //finding next character
            if(ch == str2.charAt(j)){
                j++;
            }
            else if(nextChar == str2.charAt(j)){
                j++;
            }
            i++;
        }

        //System.out.println("Value of j is "+j);

        return j==str2.length()?true:false;

    }
}