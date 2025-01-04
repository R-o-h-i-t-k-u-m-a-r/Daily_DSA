//Leetcode : 1930. Unique Length-3 Palindromic Subsequences

import java.util.*;

class Solution {
    //HashSet<String> set = new HashSet<>();
    //HashMap<String, Integer> memo = new HashMap<>();
    public int countPalindromicSubsequence(String s) {
        //Approach 1: Recursive 
        // T.C : TLE
        // int n = s.length();
        // func("", 0, n, s);
        // return set.size();

        //Approach 2: linear scan with character frequency tracking
        //T.C: O(26*n) ~ O(n)
        // int n = s.length();
        // HashSet<Character> uniqueChar = new HashSet<>();
        // for(int i=0;i<n;i++){
        //     uniqueChar.add(s.charAt(i));
        // }

        // int result = 0;

        // for(char ch: uniqueChar){ //O(26)
        //     int left_idx = -1;
        //     int right_idx = -1;

        //     //finding left most and right most index of each unique character
        //     for(int i=0;i<n;i++){ //O(n)
        //         if(ch==s.charAt(i)){
        //             if(left_idx==-1){
        //                 left_idx = i;
        //             }
        //             right_idx = i;
        //         }
        //     }

        //     //remaining characters between leftmost and rightmost index will form palindrome
        //     HashSet<Character> set = new HashSet<>();
        //     for(int i=left_idx+1;i<right_idx;i++){ //O(n)
        //         set.add(s.charAt(i));
        //     }

        //     result+=set.size();
        // }

        // return result;


        //Approach 3: Using Prefix Array
        int n = s.length();
        int left[] = new int[26];
        int right[] = new int[26];
        Arrays.fill(left,-1);
        Arrays.fill(right,-1);
        
        //find left most and right most index of each character
        for(int i=0;i<n;i++){
            int index = s.charAt(i) - 'a';
            if(left[index]==-1){
                left[index] = i;
            }
            right[index] = i;
        }

        int result = 0;
        for(int i=0;i<26;i++){
            if(left[i]==-1){
                continue;
            }
            int left_idx = left[i];
            int right_idx = right[i];
            HashSet<Character> set = new HashSet<>();
            for(int j=left_idx+1;j<right_idx;j++){
                set.add(s.charAt(j));
            }

            result+=set.size();
        }

        return result;
    }

    // public int func(String input, int index, int n, String s) {
    //     // Base case
    //     if (input.length() > 3) {
    //         return 0; // Discard subsequences longer than 3
    //     }
    //     if (index >= n) {
    //         if (input.length() == 3 && input.charAt(0) == input.charAt(2)) {
    //             set.add(input); // Add valid palindromic subsequence
    //             return 1;
    //         }
    //         return 0;
    //     }

    //     // Memoization key
    //     String key = input + "|" + index;
    //     if (memo.containsKey(key)) {
    //         return memo.get(key);
    //     }

    //     // Recursive calls
    //     int take = func(input + s.charAt(index), index + 1, n, s); // Include current character
    //     int notTake = func(input, index + 1, n, s); // Skip current character

    //     // Store result in memo
    //     memo.put(key, take + notTake);
    //     return take + notTake;
    // }
}