package Array.Medium;

//Leetcode : 1726. Tuple with Same Product

import java.util.HashMap;

class Solution {
    public int tupleSameProduct(int[] nums) {
        //Approach 1: Super Brute Force
        //T.C : O(n^4)
        // int n = nums.length;
        // int tuple = 0;

        // // Four nested loops to select a, b, c, d
        // for(int i=0;i<n;i++){ // Choose a
        //     for(int j=i+1;j<n;j++){ // Choose b (distinct from a)
        //         for(int k=0;k<n;k++){ // Choose c
        //             for(int l=k+1;l<n;l++){ // Choose d (distinct from c)
        //                 // Ensure all four indices are distinct
        //                 if(i != k && i != l && j != k && j != l){
        //                     int product1 = nums[i]*nums[j];
        //                     int product2 = nums[k]*nums[l];
                            
        //                     // Check if products are equal
        //                     if(product1 == product2){
        //                         tuple+=1;
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // }

        // // Each unique combination corresponds to 8 possible tuples
        // return (tuple/2)*8; // Divide by 2 to correct double-counting

        //Approach-2 (Improving a little more after sorting)
        //T.C : O(n^4 + nlogn)
        // int n = nums.length;
        // Arrays.sort(nums);
        // int tuple = 0;

        // for(int i=0;i<n;i++){
        //     for(int j=n-1;j>i;j--){
        //         for(int k=i+1;k<n;k++){
        //             for(int l=j-1;l>k;l--){
        //                 int p1 = nums[i]*nums[j];
        //                 int p2 = nums[k]*nums[l];
        //                 if(p1==p2){
        //                     tuple+=1;
        //                 }
        //             }
        //         }
        //     }
        // }

        // return tuple*8;

        //Approach-3 (Better Brute Force)
        //T.C : O(n^3)

        // int n = nums.length;
        // Arrays.sort(nums);
        // int tuple = 0;

        // for(int i=0;i<n;i++){
        //     for(int j=n-1;j>i;j--){
        //         int product = nums[i]*nums[j];
        //         HashSet<Integer> set = new HashSet<>();
        //         for(int k=i+1;k<j;k++){
        //             if(product%nums[k]==0){
        //                 int l = product / nums[k];
        //                 if(set.contains(l)){
        //                     tuple+=1;
        //                 }
        //                 set.add(nums[k]);
        //             }
        //         }
        //     }
        // }

        // return tuple*8;

        //Approach-4 (Using maths and combination)
        //T.C : O(n^2)
        int n = nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();//to store the freq of all possible products

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int product = nums[i]*nums[j];
                map.put(product,map.getOrDefault(product,0)+1);
            }
        }
        int tuple = 0;
        for(int freq: map.values()){
            tuple+=(freq*(freq-1))/2; //finding all possible pairs from freq using nC2 formula
        }
        return tuple*8;
    }
}