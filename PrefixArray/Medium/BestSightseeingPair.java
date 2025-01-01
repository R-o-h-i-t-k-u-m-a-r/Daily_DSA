package PrefixArray.Medium;

//Leetcode: 1014. Best Sightseeing Pair

class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        // Approach : Prefix sum
        int n = values.length;
        //int preMax[] = new int[n]; // will store values[i]+i max value till i'th index
        //preMax[0]= values[0] + 0; 
        // for(int i=1;i<n;i++){
        //     preMax[i] = Math.max(preMax[i-1],values[i]+i);
        // }
        int preMax = values[0]+0;

        int result = Integer.MIN_VALUE;
        for(int j=1;j<n;j++){
            //result = Math.max(result,preMax[j-1]+values[j]-j);
            result = Math.max(result,preMax+values[j]-j);
            preMax = Math.max(preMax,values[j]+j);
        }

        return result;
    }

    
}
