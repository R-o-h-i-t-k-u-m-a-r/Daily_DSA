package Recursion.Medium;

//GFG: https://www.geeksforgeeks.org/problems/tower-of-hanoi-1587115621/1

class Solution {

    public int towerOfHanoi(int n, int source, int dist, int helper) {
        // Approach : Recursion IBH 
        // base case
        if(n==0) return 0;
        if(n==1) return 1;
        
        //hypothesis: move n-1 disk from source to helper rod
        int stepsOfSourceToHelper = towerOfHanoi(n-1,source,helper,dist);
        
        //induction: move n-1 disk from helper to dist rod
        int stepOfHelperToDist = towerOfHanoi(n-1,helper,dist,source);
        
        //include 1 step for moving remaning 1 disk of source rod to dist rod
        //and include total steps of stepsOfSourceTohelper rod and 
        //stepOfHelplerToDist rod
        return 1+stepsOfSourceToHelper + stepOfHelperToDist;
    }
}
