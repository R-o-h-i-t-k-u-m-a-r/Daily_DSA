package String.Easy;

//Leetcode : 3042. Count Prefix and Suffix Pairs I


class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        //Approach 1: Brute Force using built-in function
        int count = 0;
        int n = words.length;

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(words[j].startsWith(words[i]) && words[j].endsWith(words[i])){
                    count++;
                }
            }
        }

        return count;
    }
}