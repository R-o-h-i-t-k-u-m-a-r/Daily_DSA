package Array.Easy;

//Leetcode : 1800. Maximum Ascending Subarray Sum

class Solution {
    public int maxAscendingSum(int[] nums) {
        //Approach : Brute Force
        //T.C : O(n^2)
        // int result = 0;
        // int n = nums.length;
        // for(int i=0;i<n;i++){
        //     int sum = nums[i];
        //     for(int j = i+1;j<n;j++){
        //         if(nums[j-1]<nums[j]){
        //             sum+=nums[j];
        //         }
        //         else{
        //             break;
        //         }
        //     }
        //     result = Math.max(result,sum);
        // }

        // return result;
        
        //Approach : linear search
        //T.C : O(n)
        int n = nums.length;
        int result = 0;
        int sum = nums[0];
        for(int i=1;i<n;i++){
            if(nums[i-1]<nums[i]){
                sum+=nums[i];
            }
            else{
                result = Math.max(result,sum);
                sum = nums[i];
            }
        }
        result = Math.max(result,sum);
        return result;
    }
}