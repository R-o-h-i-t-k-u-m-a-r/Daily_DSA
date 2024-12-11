package TimeInterval.Medium;

import java.util.*;

//Leetcode : 2779. Maximum Beauty of an Array After Applying Operation

class Solution {
    public int maximumBeauty(int[] nums, int k) {
        //Approach 1: Using Time Interval Technique
        // ArrayList<int []> list = new ArrayList<>();
        // for(int num: nums){
        //     int start = num - k;
        //     int end = num + k;
        //     list.add(new int[]{start,end});
        // }
        // Collections.sort(list,(a,b)->Integer.compare(a[0],b[0]));
        // Deque<int[]> deque = new ArrayDeque<>();
        // deque.addLast(list.get(0));
        // int count = 1;
        // int n = nums.length;
        // for(int i=1;i<n;i++){
        //     //check it is overlapping to previous or not
        //     if(list.get(i)[0]<=deque.getFirst()[1]){
        //         deque.addLast(list.get(i));
                
        //     }
        //     else{
        //         //if not overlap then remove all non-overlaping intervals
        //         while(!deque.isEmpty() && deque.getFirst()[1]<list.get(i)[0]){
        //             deque.removeFirst();
        //         }
        //         deque.addLast(list.get(i));
                
        //     }
        //     count = Math.max(count,deque.size());
        // }

        // return count;

        // Approach 2: Using Binary Search
        // int n = nums.length;
        // int result = -1;
        // Arrays.sort(nums);
        // for(int i=0;i<n;i++){
        //     int index = BS(nums,nums[i]+2*k,n);
        //     if(index!=-1){
        //         result = Math.max(result,index-i+1);
        //     }
        // }

        // return result;

        //Approach 3: Using Sliding Window
        int n = nums.length;
        Arrays.sort(nums);
        int i = 0;
        int j = 0;
        int result = 1;

        while(i<n){
            while(j<n && nums[j]<=(nums[i]+2*k)){
                j++;
            }
            result = Math.max(result,j-i);
            i++;
        }

        return result;
    }

    public int BS(int arr[],int val,int n){
        int l = 0;
        int r = n-1;
        int index = -1;

        while(l<=r){
            int mid = l +(r-l)/2;

            if(arr[mid]<=val){
                index = mid;
                l = mid+1;
            }
            else{
                r = mid -1;
            }
        }

        return index;
    }
}