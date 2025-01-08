package SegmentTree.Medium;

import java.util.*;

//GFG : https://www.geeksforgeeks.org/problems/sum-of-query-ii5310/1


class Solution{
    
    public void buildSegment(int index,int l,int r, int[] segmentTree, int[] arr){
        //base case
        if(l==r){
            segmentTree[index] = arr[l];
            return;
        }
        int mid = l+(r-l)/2;
        
        //build left subtree
        buildSegment(2*index+1,l,mid,segmentTree,arr);
        //build right subtree
        buildSegment(2*index+2,mid+1,r,segmentTree,arr);
        
        //add both left and right subtree value to root node
        segmentTree[index] = segmentTree[index*2+1] + segmentTree[index*2+2];
        return;
    }
    
    public int Query(int start,int end,int index,int l,int r,int[] segmentTree){
        //base case
        if(l>end || r<start){
            return 0;
        }
        if(l>=start && r<=end){
            return segmentTree[index];
        }
        int mid = l+(r-l)/2;
        
        return Query(start,end,index*2+1,l,mid,segmentTree)+
        Query(start,end,index*2+2,mid+1,r,segmentTree);
    }
    List<Integer> querySum(int n, int arr[], int q, int queries[])
    {
        // Approach : Using Segment Tree
        
        //Step 1: Build Segment Tree
        int segmentTree[] = new int[4*n];
        buildSegment(0,0,n-1,segmentTree,arr);
        
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<q*2;i+=2){ //O(Q)
            int start = queries[i] - 1;
            int end = queries[i+1] - 1;
            result.add(Query(start,end,0,0,n-1,segmentTree)); //O(logn)
        }
        
        return result;
    }
}