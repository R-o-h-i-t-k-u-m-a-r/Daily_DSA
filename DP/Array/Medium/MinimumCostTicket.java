//Leetcode: 983. Minimum Cost For Tickets

import java.util.*;

class Solution {
    int t[];
    public int mincostTickets(int[] days, int[] costs) {
        //Approach : Using Recursion Memoisation
        //T.C : O(nlogn)
        
        int n = days.length;
        t = new int[n];
        Arrays.fill(t,-1);
        return func(days,costs,n,0);
    }

    public int func(int days[],int costs[], int n, int dayIndex){
        if(dayIndex>=n){
            return 0;
        }
        if(t[dayIndex]!=-1){
            return t[dayIndex];
        }
        
        //take day1 pass
        int day1 =  costs[0] + func(days,costs,n,dayIndex+1);
        //take day7 pass
        //find valid index for day7 pass
        int day7Index = daySearch(days,n,days[dayIndex]+7-1);
        int day7 = costs[1]+ func(days,costs,n,day7Index);

        //take day30 pass
        //find valid index for day30 pass
        int day30Index = daySearch(days,n,days[dayIndex]+30-1);
        int day30 = costs[2]+func(days,costs,n,day30Index);

        return t[dayIndex] = Math.min(day1,Math.min(day7,day30));
    }
    //Binary Search --> O(logn)
    public int daySearch(int days[],int n,  int day){
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low + (high-low)/2;

            if(days[mid]<=day){
                low = mid+1;
            }
            else{ 
                high = mid -1;
            }
        }

        return low;
    }
}