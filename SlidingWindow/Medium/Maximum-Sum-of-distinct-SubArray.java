package SlidingWindow.Medium;

import java.util.HashSet;
import java.util.Set;

//Leetcode : 2461. Maximum Sum of Distinct Subarrays With Length K

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        //Approach: Sliding Window
        Set<Integer> set = new HashSet<>();
        long result = 0;
        long windowSum  = 0;
        int n = nums.length;
        int i = 0;
        int j = 0;

        while(j<n){
            //if current element is present in hashset then remove all the set elements till the 
            //current element is found
            while(set.contains(nums[j])==true){
                windowSum-=nums[i];
                set.remove(nums[i]);
                i++;
            }
            //add the current element to set and update the windowSum
            windowSum+=nums[j];
            set.add(nums[j]);
           

            //check if current window size is equal to k
            if((j-i+1)==k){
                //update the result and window sum
                result = Math.max(result,windowSum);
                windowSum-=nums[i];
                //remove element from set
                set.remove(nums[i]);
                //shrink the window size
                i++;
            }
            j++;
        }

        return result;
    }
}