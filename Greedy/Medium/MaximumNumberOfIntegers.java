package Greedy.Medium;

import java.util.*;

//Leetcode : 2554. Maximum Number of Integers to Choose From a Range I

class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        //Approach : Using Greedy
        HashSet<Integer> set = new HashSet<>();
        for(int num: banned){
            set.add(num);
        }
        int size = banned.length;
        int count = 0;
        int sum = 0;
        Arrays.sort(banned);

        //choose greedly from smallest number to largest to make count large
        for(int i=1;i<=n;i++){
            // if(set.contains(i)==true){
            //     continue;
            // }
            if(BS(banned,size,i)==true){ //binary search
                continue;
            }
            if(sum+i<=maxSum){
                count++;
                sum+=i;
            }
            else{
                break;
            }
        }
        return count;
        
    }

    public boolean BS(int arr[],int n, int num){
        int left = 0;
        int right = n-1;

        while(left<=right){
            int mid = left+(right-left)/2;

            if(arr[mid]==num){
                return true;
            }
            else if(arr[mid]<num){
                left = mid+1;
            }
            else{
                right = mid -1;
            }
        }

        return false;
    }
}