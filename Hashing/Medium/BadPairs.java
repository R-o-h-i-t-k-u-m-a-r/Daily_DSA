package Hashing.Medium;

//Leetcode : 2364. Count Number of Bad Pairs

import java.util.HashMap;

class Solution {
    public long countBadPairs(int[] nums) {
        //Approach 1: Brute Force
        //T.C : O(n^2) --> TLE
        // int n = nums.length;
        // int badPair = 0;
        // for(int i=0;i<n;i++){
        //     for(int j=i+1;j<n;j++){
        //         if((j-i)!=(nums[j]-nums[i])){
        //             badPair++;
        //         }
        //     }
        // }

        // return badPair;

        //Approach 2: using pattern of (i,j)!=nums[j]-nums[i]

        //step 1: convert array element into nums[i]-i and nums[j]-j form
        int n = nums.length;
        for(int i=0;i<n;i++){
            nums[i] = nums[i] - i;
        }
        //step 2: now we need to find how many numbers are same to nums[i] on the left side
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(nums[0],1);//hasing first value of array of frequency one
        long badPair = 0;
        for(int i=1;i<n;i++){
            int countleftnums = i;//count of left side number
            int samenumbercount = map.containsKey(nums[i])==true?map.get(nums[i]):0;
            badPair+=countleftnums - samenumbercount;
            //insert the current number to freq map
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        return badPair;
    }
}