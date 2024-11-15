//Leetcode : 1574. Shortest Subarray to be Removed to Make Array Sorted

class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        //Approach: Two Pointer
        int n = arr.length;
        //Step 1: Findount the the longest non decreasing sorted array from right right side
        //with the help of j pointer
        int j = n-1;
        while(j>0 && arr[j]>=arr[j-1]){
            j--;
        }

        int result = j; //assuming initially we need to remove 0 to j subarray to make array sorted
        //as till j to n-1  arr is already in non decreasing order
        int i = 0;

        //start finding correct i and j position and calculated deleted elements j-i-1
        while(i<j && ( i==0 || arr[i]>=arr[i-1])){
            //arr[i]>arr[j]
            while(j<n && arr[i]>arr[j]){
                j++;
            }
            //we have found correct such that arr[i]<=arr[j]
            result = Math.min(result,j-i-1);
            i++;
        }

        return result;
    }
}