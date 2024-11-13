//Leetcode : 2563. Count the Number of Fair Pairs

import java.util.Arrays;

class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        //Approach: Brute Force T.N = O(n^2)
        // long result = 0;
        // int n = nums.length;
        // for(int i=0;i<n;i++){
        //     for(int j=i+1;j<n;j++){
        //         if((nums[i]+nums[j])>=lower && (nums[i]+nums[j])<=upper){
        //             result++;
        //         }
        //     }
        // }
        // return result;
        
        //Approach : Using Sorting and Binary Search Algorithm
        //Resource: https://youtu.be/r3EnymXRC9A?si=CukfjnYghbxAvusY
        long result = 0;
        int n = nums.length;
        //sort the array
        Arrays.sort(nums); //O(nlogn)

        for(int i=0;i<n;i++){
            //Find the first element not less than (lower - nums[i])
            int leftIndex = lowerBound(nums,i+1,n,lower-nums[i]);
            //Find the first element greater than (upper - nums[i])
            int rightIndex = upperBound(nums,i+1,n,upper-nums[i]);

            //subtracting 1 from both leftIndex and rightIndex so that we get previous index of lowerBound
            //uperBound index
            int x = leftIndex-1-i;
            int y = rightIndex-1-i;

            result+=(y-x);
        }

        return result;
        
    }

    public int lowerBound(int[] nums, int start, int end, int target){
        //T.N = O(logn)
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[mid]<target){
                start = mid+1;
            }
            else{
                end = mid;
            }
        }
        return start;
    }

    public int upperBound(int nums[], int start, int end, int target){
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[mid]<=target){
                start = mid+1;
            }
            else{
                end = mid;
            }
        }

        return start;
    }
}