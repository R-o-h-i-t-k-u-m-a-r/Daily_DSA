//Leetcode : 784. Letter Case Permutation

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> list = new ArrayList<>();
        solve(s,"",list);

        return list;
    }

    public void solve(String input, String output, List<String> list){
        if(input.length()==0){
            list.add(output);
            return;
        }
        //if input character is digit then simply add it to output string
        if(Character.isDigit(input.charAt(0))){
            solve(input.substring(1),output+input.charAt(0),list);
            return;
        }
        //make character uppercase
        char ch = Character.isLowerCase(input.charAt(0))==true?Character.toUpperCase(input.charAt(0)):Character.toLowerCase(input.charAt(0));
        solve(input.substring(1),output+ch,list);
        //do not change the character
        solve(input.substring(1),output+input.charAt(0),list);
        return;
    }
}