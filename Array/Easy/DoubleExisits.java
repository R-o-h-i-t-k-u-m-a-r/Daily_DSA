package Array.Easy;

import java.util.*;

//Leetcode : 1346. Check If N and Its Double Exist

class Solution {
    public boolean checkIfExist(int[] arr) {
        // Appraoch : Using HashTable
        // T.C : O(n)
        // S.C : O(n)
        // HashSet<Integer> set = new HashSet<>();
        
        // for(int val: arr){
        //     if(set.isEmpty() == true){
        //         set.add(val);
        //         continue;
        //     }
        //     if(set.contains(2*val)==true || (val%2==0 && set.contains(val/2)==true)){
        //         return true;
        //     }
        //     set.add(val);
        // }
        // return false;

        // Appraoch 2: Using Binary Search
        // T.C : O(nlogn+nlogn)
        Arrays.sort(arr);
        int n = arr.length;

        for(int i=0;i<n;i++){
            int j = BS(2*arr[i],arr, n);

            //check if both element has diffent index
            if(j!=-1 && i!=j){
                return true;
            }
        }
        return false;
    }

    public int BS(int num,int arr[],int n){
        int left = 0;
        int right = n-1;

        

        while(left<=right){
            int mid = (left+right)/2;

            if(arr[mid]==num){
                return mid;
            }
            else if(arr[mid]<num){
                left = mid+1;
            }
            else{
                right = mid -1;
            }
        }

        return -1;
    }
}
