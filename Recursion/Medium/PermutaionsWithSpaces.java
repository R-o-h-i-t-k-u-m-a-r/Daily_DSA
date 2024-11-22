//GFG : https://www.geeksforgeeks.org/problems/permutation-with-spaces3627/1

import java.util.ArrayList;

class Solution {

    ArrayList<String> permutation(String s) {
        // Approach: Recursion Pick and No-pick technique
        ArrayList<String> list = new ArrayList<>();
        solve(s,"",list);
        
        return list;
    }
    
    public void solve(String s, String input, ArrayList<String> list){
        //base case 
        if(s.length()==0){
            list.add(input);
            return;
        }
        
        if(input.length()==0){
            solve(s.substring(1),input+s.charAt(0),list);
            return;
        }
        
        //pick
        solve(s.substring(1),input+" "+s.charAt(0),list);
        //no pick
        solve(s.substring(1),input+s.charAt(0),list);
        
        
        return;
    }
}