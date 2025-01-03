package PrefixArray.Medium;

//Leetcode : 769. Max Chunks To Make Sorted

class Solution {
    public int maxChunksToSorted(int[] arr) {
        //Approach 1: Prefix Array
        // int n = arr.length;
        // int preMax[] = new int[n];
        // int sMin[] = new int[n];

        // //fill the prefix  max array
        // preMax[0] = arr[0];
        // for(int i=1;i<n;i++){
        //     preMax[i] = Math.max(arr[i],preMax[i-1]);
        // }

        // //fill the suffix min array
        // sMin[n-1] = arr[n-1];
        // for(int i=n-2;i>=0;i--){
        //     sMin[i] = Math.min(arr[i],sMin[i+1]);
        // }

        // int chunks = 0;
        // for(int i=0;i<n;i++){
        //     int max = i==0?-1:preMax[i-1];
        //     int min = sMin[i];
        //     if(max<min){
        //         chunks++;
        //     }
        // }

        // return chunks;

        //Approach 2: Optimization of approach 1
        // int n = arr.length;
        // int splits = 0;
        // int numSum = 0;
        // int sortedSum = 0;

        // for(int i=0;i<n;i++){
        //     numSum+=arr[i];
        //     sortedSum+=i;
        //     if(numSum==sortedSum){
        //         splits++;
        //     }
        // }

        // return splits==0?1:splits;

        //Approach 3: Optimization over approach 2
        int n = arr.length;
        int splits = 0;
        int max = -1;

        for(int i=0;i<n;i++){
            max = Math.max(max,arr[i]);
            if(max==i){
                splits++;
            }
        }

        return splits;
    }
}