package Recursion.Medium;

//Leetcode : 779. K-th Symbol in Grammar

class Solution {
    public int kthGrammar(int n, int k) {
        //Approach : Recursion IBH 
        //base case
       if(n==1 || k==1){
        return 0;
       }

       //calculate mid of grammer
       int mid = (int)Math.pow(2,n-1)/2;
       //if k is less than or equal to mid then k will lies upto to mid of grammer and we can look this
       //k for n-1 as grammer of n upto mid is equal to grammer of n-1.
       if(k<=mid){
        return kthGrammar(n-1,k);
       }
       //if k is greater than mid then we need to map grammer of n-1 with grammer of n after mid by 
       // doing k-mid and need to flip the grammer as grammer after mid of n is equal to flip of grammer
       //of n-1.
       return 1 - kthGrammar(n-1,k-mid);

    }
}