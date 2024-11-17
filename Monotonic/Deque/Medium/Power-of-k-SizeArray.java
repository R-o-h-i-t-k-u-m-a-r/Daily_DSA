package Monotonic.Deque.Medium;

//Leetcode : 3254. Find the Power of K-Size Subarrays I

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] resultsArray(int[] nums, int k) {
        //Appraoch 1: Brute Force
        // int n = nums.length;
        
        // int result[] = new int[n-k+1];
        // Arrays.fill(result,-1);

        //T.C = O(n*k)
        // for(int i=0;i<n-k+1;i++){
        //     int count = 1;
        //     for(int j=i;j<i+k-1;j++){
        //         if((nums[j]+1==nums[j+1])){
        //             count++;
        //         }
        //         else{
        //             break;
        //         }
        //     }
        //     if(count==k){
        //         result[i] = nums[i+k-1];
        //     }
        // }

        // return result;

        //Approach 2: Sliding window
        // int n = nums.length;
        // int result[] = new int[n-k+1];
        // Arrays.fill(result,-1);
        // int count = 1;

        // //preprocess the first window
        // for(int i=0;i<k-1;i++){
        //     if(nums[i]+1==nums[i+1]){
        //         count++;
        //     }
        //     else{
        //         count = 1;
        //     }
        // }
        // if(count==k){
        //     result[0] = nums[k-1];
        // }
        // int j=k;
        // //process the remaning window
        // while(j<n){
        //     if(nums[j-1]+1==nums[j]){
        //         count++;
        //     }
        //     else{
        //         count = 1;
        //     }
        //     if(count>=k){
        //         result[j-k+1] = nums[j];
        //     }
        //     j++;
        // }

        // return result;

        //Approach 3: Using Monotonic(increasing order) Deque Technique
        int n = nums.length;

        Deque<Integer> deq = new LinkedList<>(); // Monotonic deque
        int[] result = new int[n - k + 1];
        int index = 0;

        for (int j = 0; j < n; j++) {
            // If deque size equals k, remove the front element
            if (deq.size() == k) {
                deq.pollFirst();
            }

            // If deque is not empty and current element is not consecutive to the last element
            if (!deq.isEmpty() && deq.peekLast() != nums[j] - 1) {
                deq.clear();
            }

            // Add the current element to the deque
            deq.offerLast(nums[j]);

            // Once we process the first k elements
            if (j >= k - 1) {
                if (deq.size() == k) {
                    result[index++] = deq.peekLast(); // Last element is the max due to monotonic property
                } else {
                    result[index++] = -1; // Otherwise, add -1
                }
            }
        }

        return result;

    }
}