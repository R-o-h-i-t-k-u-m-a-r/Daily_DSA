//Leetcode : 689. Maximum Sum of 3 Non-Overlapping Subarrays

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int[][] dp = new int[20001][4];
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        //Approach : Recursion memoisation

        // Initialize the memoization array with -1
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int n = nums.length -k +1;
        int subarraySum[] = new int[n];
        int i=0;
        int j=0;
        int windowSum = 0;

        //sliding window algorithm
        while(j<nums.length){
            windowSum+=nums[j];

            if(j-i+1==k){
                subarraySum[i] = windowSum;
                windowSum-=nums[i];
                i++;
            }
            j++;
        }

        List<Integer> indices = new ArrayList<>();
        
        solve(subarraySum,k,0,3,indices);

        return indices.stream().mapToInt(Integer::intValue).toArray();
    }

    public void solve(int sums[],int k,int index,int count,List<Integer> indices){
        if(count==0 || index>=sums.length){
            return;
        }

        int take = sums[index] + helper(sums,k,index+k,count-1);
        int not_take = helper(sums,k,index+1,count);

        if(take>=not_take){
            indices.add(index);
            solve(sums,k,index+k,count-1,indices);
        }
        else{
            solve(sums,k,index+1,count,indices);
        }
    }

    public int helper(int sums[],int k,int index,int count){
        if(count==0){
            return 0;
        }

        if(dp[index][count]!=-1){
            return dp[index][count];
        }
        if(index>=sums.length){
            return Integer.MIN_VALUE;
        }

        int take = sums[index] + helper(sums,k,index+k,count-1);
        int not_take = helper(sums,k,index+1,count);

        return dp[index][count] = Math.max(take,not_take);
    }
}