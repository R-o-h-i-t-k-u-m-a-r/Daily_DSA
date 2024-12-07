//Leetcode : 1760. Minimum Limit of Balls in a Bag

class Solution {
    public boolean isPossible(int nums[],int mid, int max){
        int count = 0;
        for(int num: nums){
            int ops = num/mid;
            if(num%mid==0){
                ops--;
            }
            count+=ops;
        }
        if(count<=max){
            return true;
        }
        return false;
    }
    public int minimumSize(int[] nums, int maxOperations) {
        //Approach : Binary Search On Answer 
        int max = Integer.MIN_VALUE;
        for(int num: nums){
            max = Math.max(max,num);
        }
        int l = 1;
        int r = max;
        int result = max;

        //O(log(max)*n)
        while(l<=r){
            int mid = l+(r-l)/2;

            if(isPossible(nums,mid,maxOperations)==true){
                result = mid;
                r = mid -1;
            }
            else{
                l = mid+1;
            }
        }

        return result;
    }
}