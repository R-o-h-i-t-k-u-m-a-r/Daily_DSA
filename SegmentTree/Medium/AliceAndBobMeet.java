package SegmentTree.Medium;

//Leetcode : 2940. Find Building Where Alice and Bob Can Meet


class Solution {
    public int querySegmentTree(int start,int end,int index,int l,int r,int segmentTree[],int heights[]){
        //out of range case
        if(l>end || r<start){
            return -1; //return -1 for out of bound query
        }
        //when query is within range 
        if(l>=start && r<=end){
            return segmentTree[index]; //return the index of maximum element
        }
        int mid = l+(r-l)/2;
        //query into left segment
        int left_idx = querySegmentTree(start,end,index*2+1,l,mid,segmentTree,heights);
        //query into right segment
        int right_idx = querySegmentTree(start,end,index*2+2,mid+1,r,segmentTree,heights);


        //handle the index out of bound case between left and right segment index
        if(left_idx==-1){
            return right_idx;
        }
        if(right_idx==-1){
            return left_idx;
        }

        //Return the index between left and right which has maximum height
        return heights[left_idx]>=heights[right_idx]?left_idx:right_idx;
    }
    // Function to return the index of the maximum element in the range from start to end
    public int RMIQ(int start,int end,int segmentTree[],int heights[]){
        int n = heights.length;
        return querySegmentTree(start,end,0,0,n-1,segmentTree,heights);
    }
    public void buildSegment(int index,int l,int r,int[] segmentTree,int[] heights){
        //if we have reached to leaf node
        if(l==r){
            segmentTree[index] = l;//index stored
            return;
        }
        int mid = l+(r-l)/2;
        //build left segment 
        buildSegment(index*2+1,l,mid,segmentTree,heights);
        //build right segment
        buildSegment(index*2+2,mid+1,r,segmentTree,heights);

        //update segment tree index after build left and right
        int left_idx = segmentTree[index*2+1];
        int right_idx = segmentTree[index*2+2];
        //store the index which has greater height value between left_idx and right_idx
        segmentTree[index] = heights[left_idx]>heights[right_idx]?left_idx:right_idx;

        return;
    }
    public int[] constructST(int[] heights, int n){
        int segmentTree[] = new int[4*n];
        buildSegment(0,0,n-1,segmentTree,heights);
        return segmentTree;
    }
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        //Approach (Using Segment Tree Range Maximum Query + Binary Search)
        //T.C : O(n + q*(logn)^2)
        //S.C : O(n)
        int n = heights.length;
        int m = queries.length;
        int segmentTree[] = constructST(heights,n);
        int result[] = new int[m];

        for(int i=0;i<m;i++){
            int max = Math.max(queries[i][0],queries[i][1]);
            int min = Math.min(queries[i][0],queries[i][1]);

            //check if alice and bob both are in same building
            if(max==min){
                result[i] = max;
                continue;
            }
            //check if one of alice and bob are next to each other building and can move to same
            if(heights[min]<heights[max]){
                result[i] = max;
                continue;
            }
            int min_idx = Integer.MAX_VALUE;
            int l = max+1;
            int r = n-1;
            //now apply binary search between l and r for Right max index query function
            while(l<=r){
                int mid = l + (r-l)/2;
                int num = RMIQ(l,mid,segmentTree,heights);
                if(heights[num]>heights[min] && heights[num]>heights[max]){
                    min_idx = Math.min(min_idx,num);
                    r = mid - 1;
                }
                else{
                    l = mid+1;
                }
            }
            if(min_idx==Integer.MAX_VALUE){
                result[i] = -1;
            }
            else{
                result[i] = min_idx;
            }
        }

        return result;
    }
}