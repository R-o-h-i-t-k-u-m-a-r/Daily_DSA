//Leetcode: 494. Target Sum

import java.util.*;

class Solution {
    HashMap<String,Integer> map = new HashMap<>();
    int t[][];
    int totalSum = 0;
    public int findTargetSumWays(int[] nums, int target) {
        //
        int n = nums.length;
        for(int num: nums){
            totalSum+=num;
        }
        t = new int[n][2*totalSum+1];
        for(int i=0;i<n;i++){
            Arrays.fill(t[i],-1);
        }
        return func(nums,target,n,0,0);
    }

    public int func(int nums[],int target,int n , int index,int sum){
        if(index>=n){
            if(sum==target){
                return 1;
            }
            return 0;
        }
        //String key = ""+index+"_"+sum;
        // if(map.containsKey(key)==true){
        //     return map.get(key);
        // }
        if(t[index][sum+totalSum]!=-1){
            return t[index][sum+totalSum];
        }

        //take +ve 
        int pos = func(nums,target,n,index+1,sum+nums[index]);
        //take -ve
        int neg = func(nums,target,n,index+1,sum-nums[index]);

        //map.put(key,pos+neg);

        return t[index][sum+totalSum]= pos+neg;
    }

    
}