package Array.Medium;

//Leetcode : 2270. Number of Ways to Split Array


class Solution {
    public int waysToSplitArray(int[] nums) {
      //Approach 1: Naive way
      //calculate the total sum
    //   long sum = 0;
    //   for(int num: nums){
    //     sum+=num;
    //   }  
    //   long windowSum = 0;
    //   int count = 0;
    //   int n = nums.length;
    //   for(int i=0;i<n-1;i++){
    //     windowSum+=nums[i];
    //     sum-=nums[i];
    //     if(windowSum>=sum){
    //         count++;
    //     }
    //   }
    //   return count;

    //Approach 2: Prefix Sum
    int n = nums.length;
    long prefix[] = new long[n];
    int split = 0;

    //populate prefix sum array
    prefix[0] = nums[0];
    for(int i=1;i<n;i++){
        prefix[i] = nums[i] + prefix[i-1];
    }

    for(int i=0;i<n-1;i++){
        long leftSum = prefix[i];
        long rightSum = prefix[n-1] - prefix[i];
        if(leftSum>=rightSum){
            split++;
        }
    }

    return split;
    }
}