package DP.Array.Hard;

import java.util.Arrays;

//Leetcode : 1639. Number of Ways to Form a Target String Given a Dictionary


class Solution {
    private int m;
    private int k;
    private final int MOD = (int) 1e9 + 7;
    private int[][] memo;

    public int numWays(String[] words, String target) {
      //Approach : Recursion memoisation
      m = target.length();
      k = words[0].length();

      long[][] freq = new long[26][k]; //frequency array

      //Populate frequency array
      for(String word: words){
        for(int i=0;i<k;i++){
            freq[word.charAt(i)-'a'][i]++;
        }
      }

      memo = new int[m][k];
      for(int row[]: memo){
        Arrays.fill(row,-1);
      }

      return solve(0,0,freq,target);
    }

    private int solve(int i, int j, long[][] freq, String target){
        if(i==m){
            return 1;
        }
        if(j==k){
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int notTaken = solve(i, j + 1, freq, target) % MOD;

        int taken = (int) ((freq[target.charAt(i) - 'a'][j] * solve(i + 1, j + 1, freq, target)) % MOD);

        return memo[i][j] = (notTaken + taken) % MOD;
    }
}