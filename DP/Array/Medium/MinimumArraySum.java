package DP.Array.Medium;

import java.util.Arrays;

//Leetcode : 3366. Minimum Array Sum 


class Solution {
    int t[][][];
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        //Approach : Recursion and Memoisation
        t = new int[101][101][101];
        //Initalizing the memo table with -1
        for(int i=0;i<101;i++){
            for(int j=0;j<101;j++){
                Arrays.fill(t[i][j],-1);
            }
        }
        return solve(nums,k,op1,op2,0);
    }

    public int solve(int nums[], int k, int op1, int op2, int i){
        //base case
        if(i>=nums.length){
            return 0;
        }
        if(t[i][op1][op2]!=-1){
            return t[i][op1][op2];
        }
        int result = Integer.MAX_VALUE;

        //explore op1 
        if(op1>0){
            int num = (nums[i]+1)/2; //ceil value
            int ans = num + solve(nums,k,op1-1,op2,i+1);
            result = Math.min(result,ans);
        }

        //explore op2
        if(op2>0 && nums[i]>=k){
            int num = nums[i]-k;
            int ans = num + solve(nums,k,op1,op2-1,i+1);
            result = Math.min(result,ans);
        }
        //explore op1&&op2 or op2&&op1
        if(op1>0 && op2>0){
            int num = (nums[i]+1)/2;
            if(num>=k){
                num = num - k;
                int ans = num + solve(nums, k, op1-1,op2-1,i+1);
                result = Math.min(result,ans);
            }

            if(nums[i]>=k){
                num = nums[i]-k;
                num = (num+1)/2;
                int ans = num + solve(nums,k, op1-1,op2-1,i+1);
                result = Math.min(result,ans);
            }
        }

        //explore nothing 
        int ans = nums[i] + solve(nums,k,op1,op2,i+1);
        result = Math.min(result,ans);

        return t[i][op1][op2] = result;
    }
}