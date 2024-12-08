package TimeInterval.Medium;

import java.util.Arrays;

//Leetcode : 2054. Two Best Non-Overlapping Events

class Solution {
    int n;
    int t[][] = new int[100001][3];
    public int maxTwoEvents(int[][] events) {
        //Approach 1: Brute Force
        // int result = -1;
        // int n = events.length;
        // for(int i=0;i<n;i++){
        //     int start = events[i][0];
        //     int end = events[i][1];
        //     int val = events[i][2];
        //     result = Math.max(result,val);

        //     for(int j=0;j<n;j++){
        //         if(i==j){
        //             continue;
        //         }
        //         if(end<events[j][0]){
        //             result =  Math.max(result,val+events[j][2]);
        //         }
        //     }
        // }

        // return result;

        // Appraoch 2: Recursion with Binary Search
        n = events.length;
        Arrays.sort(events,(a,b)->Integer.compare(a[0],b[0]));//sort by start time
        for(int arr[]: t){
            Arrays.fill(arr,-1);
        }

        return solve(events,0,0);
    }
    public int solve(int events[][],int i,int count){
        if(count==2 || i>=n){
            return 0;
        }
        if(t[i][count]!=-1){
            return t[i][count];
        }
        //take 
        int nextValidEventIndex = binarySearch(events,events[i][1]);
        int take = events[i][2]+solve(events,nextValidEventIndex,count+1);
        //not take
        int not_take = solve(events,i+1,count);

        return t[i][count] =  Integer.max(take,not_take);
    }

    public int binarySearch(int events[][], int endTime){
        int l = 0;
        int r = n-1;
        int result = n;

        while(l<=r){
            int mid = l + (r-l)/2;

            if(endTime<events[mid][0]){
                result = mid;
                r = mid -1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }
}