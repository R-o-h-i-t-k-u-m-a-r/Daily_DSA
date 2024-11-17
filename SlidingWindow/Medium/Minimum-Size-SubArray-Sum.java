//Leetcode : 209. Minimum Size Subarray Sum

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
       //Appraoch : Using Sliding Window Technique
        int i = 0;
        int j = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        int n = nums.length;

        while(j<n){
            sum = sum +nums[j];
            
            //if we found the subarray sum greater than or equal to target then
            if(sum>=target){
                //try to shrink the window size from left side till total sum of shink window is 
                //greater than or equal to target
                while(i<=j && sum-nums[i]>=target)
                {
                    //reduce the window sum to the left most element
                    sum = sum - nums[i];
                    i++;
                }

                //Update the result after finding the correct window size
                result = Math.min(result,j-i+1);
            }
            j++;

        }

        return result==Integer.MAX_VALUE?-1:result;
    }
}