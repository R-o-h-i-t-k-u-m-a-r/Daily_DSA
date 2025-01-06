package PrefixArray.Medium;

//Leetcode : 1769. Minimum Number of Operations to Move All Balls to Each Box

class Solution {
    public int[] minOperations(String boxes) {
        //Approach 1: Brute Force
        //T.C : O(n^2)
        // int n = boxes.length();
        // int result[] = new int[n];
        // for(int i=0;i<n;i++){
        //     int step = 0;
        //     for(int j=0;j<n;j++){
        //         if(i!=j && boxes.charAt(j)=='1'){
        //             step+=Math.abs(i-j);
        //         }
        //     }
        //     result[i] = step;
        // }

        // return result;

        //Approach 2: Prefix Sum
        //T.C : O(n)
        int n = boxes.length();
        int result[] = new int[n];
        
        //Find distance to move all boxes from left side to each box
        int cumVal = 0;
        int cumSum = 0;
        for(int i=0;i<n;i++){
            result[i] = cumSum;
            //update the currentvalue sum and cumulative sum
            cumVal+=boxes.charAt(i)=='1'?1:0;
            cumSum+=cumVal;
        }
        //Find distance to move all boxes from right side to each box
        cumVal = 0;
        cumSum = 0;
        for(int i=n-1;i>=0;i--){
            result[i]+= cumSum;
            //update the currentvalue sum and cumulative sum
            cumVal+=boxes.charAt(i)=='1'?1:0;
            cumSum+=cumVal;
        }

        return result;
    }
}