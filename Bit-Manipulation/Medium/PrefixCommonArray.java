package Bit-Manipulation.Medium;

//Leetcode : 2657. Find the Prefix Common Array of Two Arrays

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        //Approach : Using HashTable and Bit Manipulation
        //T.C : O(n)
        //S.C : O(50)~O(1)
        //create array of 50 size
        int map[] = new int[51];
        int count = 0;
        int n = A.length;
        int C[] = new int[n]; //result array
        //take xor of both array A and B and store resultant into map array 
        for(int i=0;i<n;i++){
            int a = A[i];//get array A element
            int b = B[i];//get array B element
            map[a]^=a; //take cumulative xor with a into map
            if(map[a]==0){//if it becomes 0 it means element a has been xor twice 
                count++;//increament count
            }
            map[b]^=b;//take cumulative xor with b into map
            if(map[b]==0){//if it becomes 0 it means element b has been xor twice
                count++;//increament count
            }
            C[i] = count;//store the count 
        }

        return C;
    }
}