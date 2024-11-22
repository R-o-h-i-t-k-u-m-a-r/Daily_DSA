//InterviewBit : https://www.interviewbit.com/problems/generate-all-parentheses-ii/

import java.util.ArrayList;

public class Solution {
    public ArrayList<String> generateParenthesis(int A) {
        ArrayList<String> list = new ArrayList<>();
        solve("",A,A,list);
        
        return list;
    }
    
    public void solve(String input, int open, int close, ArrayList<String> list){
        //base case
        if(open == 0  && close == 0){
            list.add(input);
            return;
        }
        
        //at the begining of function call add opening parenthesis
        if(input.length()==0){
            solve(input+"(", open-1,close,list);
            return;
        }
        //if we don't have remain open parenthesis then add remaining all close parenthesis
        if(open == 0 && close>0){
            solve(input+")",open,close-1,list);
            return;
        }
        //if we have equal number of open and close parenthesis then need to add opening only
        if(open == close){
            solve(input+"(",open-1,close,list);
            return;
        }
        
        //otherwise pick open parenthesis
        solve(input+"(",open-1,close,list);
        //pick close parenthesis
        solve(input+")",open,close-1,list);
        return;
    }
}
