package SlidingWindow.Medium;

import java.util.Arrays;

//Leetcode: 3254. Find the Power of K-Size Subarrays I

class Solution {
    public int[] resultsArray(int[] nums, int k) {
        //Appraoch : Brute Force
        // int n = nums.length;
        
        // int result[] = new int[n-k+1];
        // Arrays.fill(result,-1);

        //T.C = O(n*k)
        // for(int i=0;i<n-k+1;i++){
        //     int count = 1;
        //     for(int j=i;j<i+k-1;j++){
        //         if((nums[j]+1==nums[j+1])){
        //             count++;
        //         }
        //         else{
        //             break;
        //         }
        //     }
        //     if(count==k){
        //         result[i] = nums[i+k-1];
        //     }
        // }

        // return result;

        //Approach : Sliding window
        int n = nums.length;
        int result[] = new int[n-k+1];
        Arrays.fill(result,-1);
        int count = 1;

        //preprocess the first window
        for(int i=0;i<k-1;i++){
            if(nums[i]+1==nums[i+1]){
                count++;
            }
            else{
                count = 1;
            }
        }
        if(count==k){
            result[0] = nums[k-1];
        }
        int j=k;
        //process the remaning window
        while(j<n){
            if(nums[j-1]+1==nums[j]){
                count++;
            }
            else{
                count = 1;
            }
            if(count>=k){
                result[j-k+1] = nums[j];
            }
            j++;
        }

        return result;
    }
}