package Array.Medium;

import java.util.*;

//Leetcode : 2948. Make Lexicographically Smallest Array by Swapping Elements


class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        //Approach 1: Brute Force
        //Intution: Try to place smallest number in each index from it's right side index element 
        //T.C : O(n^3)
        //S.C : O(1)
        // int n = nums.length;

        // for(int i=0;i<n;i++){
        //     boolean flag = true;
        //     int curr = nums[i];
        //     while(flag){
        //         //if any element found smaller than the current number with diff of limit then swap
        //         for(int j = i+1;j<n;j++){
        //             if(nums[j]<nums[i] && nums[i]-nums[j]<=limit){
        //                 int temp = nums[i];
        //                 nums[i] = nums[j];
        //                 nums[j] = temp;
        //                 break;
        //             }
        //         }
        //         //if smaller element found 
        //         if(nums[i]==curr){
        //             flag = false;
        //         }
        //         curr = nums[i];
        //     }
        // }

        // return nums;

        //Approach 2: (using sorting and grouping using unordered_map)
        //T.C : ~O(n*logn)
        //S.C : ~O(n)

        int n = nums.length;
        int arr[] = nums.clone();
        Arrays.sort(arr);

        //hash map to keep record of each number group
        HashMap<Integer,Integer> numGroup = new HashMap<>();
        //hash map to keep record of group of list of elements
        HashMap<Integer,LinkedList<Integer>> groupList = new HashMap<>();

        //group each number to their belonging group
        int group = 0;
        numGroup.put(arr[0],group); 
        groupList.putIfAbsent(group,new LinkedList<>());
        groupList.get(group).add(arr[0]);

        for(int i=1;i<n;i++){
            if(arr[i]-arr[i-1]>limit){
                group+=1;
            }
            numGroup.put(arr[i],group);
            groupList.putIfAbsent(group,new LinkedList<>());
            groupList.get(group).add(arr[i]);
        }

        int result[] = new int[n];
        for(int i=0;i<n;i++){
            int num = nums[i];
            int numberGroup = numGroup.get(num);
            result[i] = groupList.get(numberGroup).pollFirst(); //use and remove smallest element
        }

        return result;
    }
}