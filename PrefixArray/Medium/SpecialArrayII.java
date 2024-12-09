package PrefixArray.Medium;

//Leetcode : 3152 Special Array II


class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        //Approach 1: Using Prefix Sum Array
        int n = nums.length;
        int prefSum[] = new int[n]; //to hold the number of different parity pair at i'th index
        prefSum[0] = 0; 
        for(int i=1;i<n;i++){
            if((nums[i]%2==0 && nums[i-1]%2==0) || (nums[i]%2==1 && nums[i-1]%2==1)){
                prefSum[i] = prefSum[i-1]+1;
            }
            else{
                prefSum[i] = prefSum[i-1];
            }
        }
        boolean result[] = new boolean[queries.length];
        int index = 0;
        for(int query[]: queries){
            int to = query[0];
            int from = query[1];
            if(prefSum[from]-prefSum[to]==0){
                result[index] = true;
            }
            else{
                result[index] = false;
            }
            index++;
        }

        return result;
    }
}