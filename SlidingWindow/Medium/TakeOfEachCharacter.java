package SlidingWindow.Medium;

//Leetcode : 2516. Take K of Each Character From Left and Right


class Solution {
    int result = Integer.MAX_VALUE;
    public int takeCharacters(String s, int k) {
        // Approach 1: Using Recursion
        // T.C : 2^n
        // int n = s.length();
        // int left = 0;
        // int right = n-1;
        // int freq[] = new int[3];
        // int minutes = 0;
        // solve(s,k,left,right,minutes,freq);

        // return result==Integer.MAX_VALUE?-1:result;

        // Approach 2: Using Sliding Window Technique
        int n = s.length();
        int freq[] = new int[3]; //freq to be deleted

        //find out freq of all three characters
        for(char ch: s.toCharArray()){
            freq[ch-'a']++;
        }
        //if any character has less frequency than k then 
        if(freq[0]<k || freq[1]<k || freq[2]<k){
            return -1;
        }

        //Slidign Window Variables
        int i = 0;
        int j = 0;
        int notDeletedWindowSize = 0;

        while(j<n){
            // Reduce count for the character at index `j`
            freq[s.charAt(j)-'a']--;

            // If any count falls below `k`, shrink the window from the left
            while(i<=j && (freq[0]<k || freq[1]<k || freq[2]<k)){
                freq[s.charAt(i)-'a']++;
                i++;
            }
            // Update the maximum size of the window
            notDeletedWindowSize = Math.max(notDeletedWindowSize,j-i+1);
            j++;
        }

        return n-notDeletedWindowSize;
    }
    public void solve(String s, int k, int left, int right, int minutes, int freq[]){
        //base case
        if(freq[0]>=k && freq[1]>=k && freq[2]>=k){
            result = Math.min(result,minutes);
            return;
        }
        // if left is greater than right it means we have visited all characters
        if(left>right){
            return;
        }

        //delete char from left side
        int leftFreq[] = freq.clone();
        leftFreq[s.charAt(left)-'a']++;
        solve(s,k,left+1,right,minutes+1,leftFreq);

        //delete char from right side
        int rightFreq[] = freq.clone();
        rightFreq[s.charAt(right)-'a']++;
        solve(s,k,left,right-1,minutes+1,rightFreq);
        return;
        
    }
}