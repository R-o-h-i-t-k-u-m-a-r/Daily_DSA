package String.Medium;

import java.util.Stack;

//Leetcode : 3412. Find Mirror Score of a String

class Solution {
    public long calculateScore(String s) {
       //Appraoch : Using HashMap
        //T.C : O(n)
        //creating hashmap to store index of each character
        Stack<Integer>[] map = new Stack[26];
        for(int i=0;i<26;i++){
            map[i] = new Stack<>();
        }
        long result = 0;
        int n = s.length();
        for(int i=0;i<n;i++){
            //extracting reverse character of current character
            char ch = (char)('z'-(s.charAt(i)-'a'));
            //System.out.println("reverse char of "+ s.charAt(i)+" is "+ch);
            if(map[ch-'a'].isEmpty()==false){
                int index = map[ch-'a'].pop();
                result+=(i-index);
            }
            else{ //if reverse char not found then store the current char index
                map[s.charAt(i)-'a'].push(i);
            }
        }

        return result;
    }
}
