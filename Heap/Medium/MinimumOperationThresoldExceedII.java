package Heap.Medium;

import java.util.PriorityQueue;

//Leetcode : 3066. Minimum Operations to Exceed Threshold Value II

class Solution {
    public int minOperations(int[] nums, int k) {
        //Approach 1: Using Min-Heap
        PriorityQueue<Long> minHeap = new PriorityQueue<>(); //by-default min heap
        //insert all the numbers into minHeap
        for(int num: nums){
            minHeap.offer((long)num);//O(logn)
        }
        int operation = 0;
        while(!minHeap.isEmpty()){
            if(minHeap.peek()>=k || minHeap.size()==1){
                return operation;
            }
            long x = minHeap.poll();
            long y = minHeap.poll();
            long value = Math.min(x,y)*2 + Math.max(x,y);
            minHeap.offer(value);//insert the value into min heap
            operation+=1; //increase the operation by 1
        }

        return operation;
    }
}