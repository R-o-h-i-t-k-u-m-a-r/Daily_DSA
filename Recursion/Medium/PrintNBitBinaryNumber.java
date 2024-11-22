//GFG : https://www.geeksforgeeks.org/problems/print-n-bit-binary-numbers-having-more-1s-than-0s0252/1

import java.util.ArrayList;

class Solution {
    ArrayList<String> NBitBinary(int N) {
        // Approach : Recursion Pick & No-Pick Technique
        ArrayList<String> list = new ArrayList<>();
        solve("",0,0,N,list);
        
        return list;
    }
    
    public void solve(String input, int one, int zero, int n, ArrayList<String> list){
        if(n==0){
            list.add(input);
            return;
        }
        //if we have same number of one'2 and zero's the we need to include 1 only
        if(one==zero){
            solve(input+'1',one-1,zero,n-1,list);
            return;
        }
        
        //inclue 1 to input string
        solve(input+'1',one-1,zero,n-1,list);
        //inclue 0 to input string
        solve(input+'0',one,zero-1,n-1,list);
        
        return;
    }
}