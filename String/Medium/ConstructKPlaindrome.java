package String.Medium;

//Leetcode : 1400. Construct K Palindrome Strings

import java.util.*;

class Solution {
    public boolean canConstruct(String s, int k) {
        //Approach : Odd Even Frequency Observation
        //if string length is less than k then it is not possible to make k palindrome
        if(s.length()<k){
            return false;
        }
        //if string length is equal to k then each character will be itself palindrome
        if(s.length()==k){
            return true;
        }
        //hash map to store frequency of each character
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        //we do not need to worry about even length character's to make palindrome 
        //only odd number of frequency character can be adjust to even length characters
        //to make palindrome which has total count less than or equal to k other wise 
        //remaining odd length frequency character will lead to separate plaindrome count
        int oddChar = 0;
        for(Map.Entry<Character,Integer> set: map.entrySet()){
            if(set.getValue()%2==1){
                oddChar++;
            }
        }
        if(oddChar>k){
            return false;
        }
        return true;
        
    }
}