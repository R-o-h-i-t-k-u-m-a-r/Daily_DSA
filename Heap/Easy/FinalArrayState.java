//Leetcode : 3264. Final Array State After K Multiplication Operations I

import java.util.PriorityQueue;

class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // Approach 1: Using Min-heap
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        }); //min-heap

        //heapifing priority queue
        for(int i=0;i<n;i++){
            pq.offer(new int[]{nums[i],i});
        }

        while(k-- >0){ //O(k)
            int arr[] = pq.poll(); //O(log{n})
            int num = arr[0] * multiplier;
            int index = arr[1];
            
            //update the nums array
            nums[index] = num;
            //insert the updated num into pq
            pq.offer(new int[]{num,index}); //O(log(n))
        }

        return nums;
    }
}