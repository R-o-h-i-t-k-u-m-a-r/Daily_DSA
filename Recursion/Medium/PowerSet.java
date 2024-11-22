//GFG : https://www.geeksforgeeks.org/problems/power-set4302/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution
{
    public List<String> AllPossibleStrings(String s)
    {
        // Approach : Using Recursion pick & no pick choice approach
        //T.C : O(2^n nlog(n))
        List<String> list =  new ArrayList<>();
        solve(s,"",list);
        
        //sort the list
        Collections.sort(list);
        return list;
    }
    
    public void solve(String input, String output, List<String> list){
        //base case
        if(input.length()==0){
            if(output.length()!=0){
                list.add(output);
            }
            return;
        }
        
        //pick the string character
        solve(input.substring(1),output+input.charAt(0),list);
        //no pick the string character
        solve(input.substring(1),output,list);
        
        return;
    }
}