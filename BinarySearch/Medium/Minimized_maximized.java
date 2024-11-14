//Leetcode : 2064. Minimized Maximum of Products Distributed to Any Store

import java.util.Arrays;

class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        //Approach : Using Brute Force 
        // int result = 0;
        // int store = -1;
        // while(true){
        //     result++;
        //     store = n;
        //     int count = 0;
        //     for(int item: quantities){
               
        //         //if there is no store remain but quantity remain
        //         if(store==0){
        //             break;
        //         }
        //         int temp = (int)Math.ceil((double)item/result);
        //         //if number of distribution is greater than n 
        //         if(temp>store){
        //             break;
        //         }
        //         store = store-temp;
        //         count++;

        //     }
        //     //if have visited all the item of quantities and we don't have store remain it means
        //     //we have distributed correctly with minimum of maximum distribution value.
        //     if(count == quantities.length && store==0){
        //         return result;
        //     }
        // }

        //Approach : Using Binary Search on Answer 
        int max = Arrays.stream(quantities).max().getAsInt(); //finding maximum value
        int left = 1;
        int right = max;
        int result = 0;
        //T.N: O(log(max)*m)
        while(left<=right){
            int mid = left+(right-left)/2;
            
            if(possibleToDistr(mid,quantities,n)==true){
                result = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return result;
    }

    public boolean possibleToDistr(int target, int quantities[], int store){
        //O(m)
        for(int item: quantities){
            store-=(item+target-1)/target; //equal to Math.ceil()
            if(store<0){
                return false;
            }
        }

        return true;
    }
}