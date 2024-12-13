//Leetcode : 2593. Find Score of an Array After Marking All Elements

import java.util.PriorityQueue;

class Solution {
    public long findScore(int[] nums) {
        // Approach 1: Simulation (TLE)
        // long sum = 0;
        // int n = nums.length;
        // while(true){
        //     int index = getMin(nums,n);
        //     if(index==-1){
        //         return sum;
        //     }
        //     sum+=nums[index];
        //     nums[index] = -1;
        //     if(index-1>=0){
        //         nums[index-1] = -1;
        //     }
        //     if(index+1<n){
        //         nums[index+1] = -1;
        //     }
        // }

        // Approach 2: using relative sorting
        // int n = nums.length;
        // int arr[][] = new int[n][2];
        // for(int i=0;i<n;i++){
        //     arr[i][0] = nums[i];
        //     arr[i][1] = i;
        // }

        // Arrays.sort(arr,(a,b)->{
            // if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            // return Integer.compare(a[1],b[1]);
        }); //O(nlogn)
        // boolean visited[] = new boolean[n];
        // long result = 0;
        // for(int i=0;i<n;i++){
        //     if(!visited[arr[i][1]]){
        //         visited[arr[i][1]] = true;
        //         int prev = arr[i][1]-1;
        //         int next = arr[i][1]+1;
        //         result+=arr[i][0];
        //         if(prev>=0){
        //             visited[prev] = true;
        //         }
        //         if(next<n){
        //             visited[next] = true;
        //         }
        //     }
        // }

        // return result;

        // Approach 3: Using Heap(min-heap)
        int n = nums.length;
        PriorityQueue<int[]> pq =  new PriorityQueue<>((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        }); //min-heap
        boolean visited[] = new boolean[n];
        long result = 0;

        for(int i=0;i<n;i++){
            pq.offer(new int[]{nums[i],i});
        }

        while(pq.isEmpty()==false){
            int arr[] = pq.poll();
            if(!visited[arr[1]]){
                visited[arr[1]] = true;
                result+=arr[0];
                if(arr[1]-1>=0){
                    visited[arr[1]-1] = true;
                }
                if(arr[1]+1<n){
                    visited[arr[1]+1] = true;
                }
            }
        }

        return result;
    }

public int getMin(int nums[],int n){
    int minIndex = -1;
    int minValue = Integer.MAX_VALUE;
    for(int i=0;i<n;i++){
        if(nums[i]!=-1 && minValue>nums[i]){
            minValue = nums[i];
            minIndex = i;
        }
    }

    return minIndex;
}

}