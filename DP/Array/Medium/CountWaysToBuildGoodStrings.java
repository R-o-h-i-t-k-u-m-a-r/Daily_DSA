package DP.Array.Medium;

import java.util.Arrays;

//Leetcode: 2466. Count Ways To Build Good Strings

class Solution {
    int mod = (int)1e9 + 7;
    int t[] = new int[100000+1];
    public int countGoodStrings(int low, int high, int zero, int one) {
        Arrays.fill(t,-1);
        return func(low,high,zero,one,0);
    }

    public int func(int low,int high,int zero,int one, int ans){
        if(ans>high){
            return 0;
        }
        if(t[ans]!=-1){
            return t[ans];
        }
        //pick zero
        int pickZero = func(low,high,zero,one,ans+zero);
        //pick one
        int pickOne = func(low,high,zero,one,ans+one);
        int self = 0;
        if(ans>=low && ans<=high){
            self = 1;
        }

        return t[ans] = (self+pickZero+pickOne)%mod;
    }
}
