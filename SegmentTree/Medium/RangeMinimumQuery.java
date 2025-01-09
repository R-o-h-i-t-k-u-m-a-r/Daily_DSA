package SegmentTree.Medium;

//GFG : https://www.geeksforgeeks.org/problems/range-minimum-query/1

class GfG
{
    static int st[];
    
    public static void buildSegment(int index,int l,int r,int arr[]){
        //base case 
        if(l==r){
            st[index] = arr[l];
            return;
        }
        //find mid 
        int mid = l+(r-l)/2;
        //build left segment
        buildSegment(index*2+1,l,mid,arr);
        //build right segment
        buildSegment(index*2+2,mid+1,r,arr);
        
        //store minimum value from left and right segment node
        st[index] = Math.min(st[index*2+1],st[index*2+2]);
        return;
    }
    
    public static int minQuery(int start,int end,int index,int l,int r)
    {
        //base case 
        if(l>end || r<start){
            return Integer.MAX_VALUE;
        }
        if(l>=start && r<=end){
            return st[index];
        }
        
        int mid = l + (r-l)/2;
        
        int leftNode = minQuery(start,end,index*2+1,l,mid);
        int rightNode = minQuery(start,end,index*2+2,mid+1,r);
        
        return Math.min(leftNode,rightNode);
    }
    
    public static int[] constructST(int arr[], int n)
    {
        //build segmentTree
        //T.C : O(logn)
        //S.C : O(n)
        st = new int[4*n];
        buildSegment(0,0,n-1,arr);
        
        return st;
    }
    
    
    /* The functions returns the
      min element in the range
      from l and r */
    public static int RMQ(int st[], int n, int l, int r)
    {
       // Add your code here
       return minQuery(l,r,0,0,n-1); //O(Q*logn)
    }
    
    
}