//GFG : https://www.geeksforgeeks.org/problems/permutations-of-a-given-string2041/1

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<String> findPermutation(String s) {
        // Approach : Recursion Pick & No-Pick Technique
        HashSet<String> set = new HashSet<>();
        solve(s,0,set);
        
        List<String> list = new ArrayList<>();
        for(String str: set){
            list.add(str);
        }
        
        //sort all the permutations
        Collections.sort(list);
        return list;
    }
    
    public void solve(String string, int start, HashSet<String> set){
        //base case
        if(start==string.length()-1){
            set.add(string);
            return;
        }
        
        for(int i= start;i<string.length();i++){
            //swap the character of string 
            String str = swap(string,start,i);
            solve(str,start+1,set);
        }
        return;
    }
    public String swap(String string, int start, int end){
        if(start==end){
            return new String(string);
        }
        StringBuilder str =  new StringBuilder(string);
        str.setCharAt(start,string.charAt(end));
        str.setCharAt(end,string.charAt(start));
        
        return str.toString();
        
    }
}