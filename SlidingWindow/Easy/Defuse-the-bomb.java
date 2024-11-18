package SlidingWindow.Easy;

//Leetcode : 1652. Defuse the Bomb


class Solution {
    public int[] decrypt(int[] code, int k) {
        //Appraoch 1: Brute Force
        //T.C = O(n*k)
        // int n = code.length;
        // int result[] = new int[n];

        // if(k==0){
        //     return result;
        // }
        // int sum = 0;
        // for(int i=0;i<n;i++){
        //     sum = 0;
        //     if(k<0){
        //         for(int j = i-Math.abs(k);j<i;j++){
        //             sum+=code[(j+n)%n];
        //         }
        //     }
        //     else{
        //         for(int j = i+1;j<=i+k;j++){
        //             sum+=code[j%n];
        //         }
        //     }
        //     result[i] = sum;
        // }

        // return result;

        //Approach 2: Sliding Window
        //T.N = O(n)
        int n = code.length;
        int result[] = new int[n];
        if(k==0){
            return result;
        }
        int i = -1;
        int j = -1;

        //positioning the window size as per the +ve or -ve k value
        //in case of +ve k value, window will start from 1 and end to k for the first element of array
        if(k>0){
            i = 1;
            j = k;
        }
        else{
            i = n - Math.abs(k);
            j = n - 1;
        }

        //Preprocessing the first window sum 
        int windowSum = 0;
        for(int pointer = i;pointer<=j;pointer++){
            windowSum+=code[pointer];
        }

        for(int K = 0; K<n;K++){
            result[K] = windowSum;
            windowSum-=code[i%n];
            i++;

            windowSum+=code[(j+1)%n];
            j++;
        }

        return result;
    }
}