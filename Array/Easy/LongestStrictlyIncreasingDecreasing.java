package Array.Easy;

//Leetcode : 3105. Longest Strictly Increasing or Strictly Decreasing Subarray


class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        //Approach : Brute Force
        //T.C : O(2*n^2)
        // int n = nums.length;
        // int result = 1;
        // for(int i=0;i<n-1;i++){
        //     for(int j=i+1;j<n;j++){
        //         if(nums[j-1]<nums[j]){
        //             result = Math.max(result,j-i+1);
        //         }
        //         else{
        //             //result = Math.max(result,j-i);
        //             break;
        //         }
        //     }
        // }

        // for(int i=0;i<n-1;i++){
        //     for(int j=i+1;j<n;j++){
        //         if(nums[j-1]>nums[j]){
        //             result = Math.max(result,j-i+1);
        //         }
        //         else{
        //             //result = Math.max(result,j-i);
        //             break;
        //         }
        //     }
        // }

        // return result;

        //Approach : Better
        //T.C : O(4*n)
        // int n = nums.length;
        // if(n==1){
        //     return 1;
        // }
        // int result = 1;
        // int i = 0;
        // int j = 1;
        // //O(2*n)
        // while(j<n){
        //     while(j<n && nums[j-1]<nums[j]){
        //         j++;
        //     }
        //     result = Math.max(result,j-i);
        //     i = j;
        //     j+=1;
        // }

        // i = 0;
        // j = 1;
        // //O(2*n)
        // while(j<n){
        //     while(j<n && nums[j-1]>nums[j]){
        //         j+=1;
        //     }
        //     result = Math.max(result,j-i);
        //     i = j;
        //     j+=1;
        // }
        // return result;

        //Approach : Optimal 
        //T.C : O(n)
        int n = nums.length;
        int incr = 1;
        int decr = 1;
        int result = 1;
        for(int i=1;i<n;i++){
            if(nums[i-1]<nums[i]){
                decr = 1;
                incr++;
                result = Math.max(result,incr);
            }
            else if(nums[i-1]>nums[i]){
                incr = 1;
                decr++;
                result = Math.max(result,decr);
            }
            else{
                incr = 1;
                decr = 1;
            }
        }
        return result;
    }
}