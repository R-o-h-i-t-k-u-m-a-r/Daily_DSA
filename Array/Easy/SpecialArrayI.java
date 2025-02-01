package Array.Easy;

//Leetcode : 3151. Special Array I

class Solution {
    public boolean isArraySpecial(int[] nums) {
        //Approach : Simulation
        //T.C : O(n)
        int n = nums.length;
        if(n==1){
            return true;
        }
        // for(int i=0;i<n-1;i++){
        //     if((nums[i]%2==0 && nums[i+1]%2==0) || (nums[i]%2==1 && nums[i+1]%2==1)){
        //         return false;
        //     }
        // }
        
        for(int i=0;i<n-1;i++){
            if((nums[i]&1)==0 && (nums[i+1]&1)==0){
                return false;
            }
            else if((nums[i]&1)==1 && (nums[i+1]&1)==1){
                return false;
            }
        }
        return true;
    }
}