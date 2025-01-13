package String.Medium;

//Leetcode : 3223. Minimum Length of String After Operations


class Solution {
    public int minimumLength(String s) {
       //Approach : Odd Even Frequency Obervation
       //creating frequency array to store frequency of each character
       int freq[] = new int[26];
       int n = s.length();
       int deleted = 0;
       for(int i=0;i<n;i++){
        char ch = s.charAt(i);
        freq[ch-'a']++;
        //if frequency reach to 3 then we can easily delete two same character 
        if(freq[ch-'a']==3){
            deleted+=2;
            freq[ch-'a'] = 1; //set frequency to 1 after deleting two character out of 3
        }
       } 

       return n - deleted; 
    }
}