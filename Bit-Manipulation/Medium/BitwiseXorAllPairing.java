//Leetcode : 2425. Bitwise XOR of All Pairings


class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
       //Approach : Odd Even XOR Frequency 
       int m = nums1.length;
       int n = nums2.length;

       //if element in nums1 and nums2 are in even times then overall xor will be zero
       if(m%2==0 && n%2==0){
        return 0;
       }
       int xor = 0;
       //if element in nums1 is odd number of times then xor will num2 element xor
       if(m%2==1){
        for(int num: nums2){
            xor^=num;
        }
       }
       if(n%2==1){
        for(int num: nums1){
            xor^=num;
        }
       }

       return xor;
    }
}