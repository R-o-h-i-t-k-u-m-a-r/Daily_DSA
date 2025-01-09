package SegmentTree.Medium;

//Leetcode : 307. Range Sum Query - Mutable

class NumArray {
    public int segmentTree[];
    public int n;
    public NumArray(int[] nums) {
        n = nums.length;
        this.segmentTree = new int[4*n];
        buildSegment(0,0,n-1,nums);
    }

    public void buildSegment(int index,int l,int r, int nums[]){
        //base case
        if(l==r){
            this.segmentTree[index] = nums[l];
            return;
        }
        int mid = l + (r-l)/2;
        //build left segment
        buildSegment(index*2+1,l,mid,nums);
        //build right segment
        buildSegment(index*2+2,mid+1,r,nums);

        //store sum of both left and right segment node value into segmentTree array
        this.segmentTree[index] = this.segmentTree[index*2+1] + this.segmentTree[index*2+2];

        return;
    }

    public void updateSegmentTree(int index,int val,int i,int l,int r){
        //base case 
        if(l==r){
            this.segmentTree[i] = val;
            return;
        }

        int mid = l + (r-l)/2;
        
        if(index<=mid){
            updateSegmentTree(index,val,2*i+1,l,mid);
        }
        else{
            updateSegmentTree(index,val,2*i+2,mid+1,r);
        }

        //update the root node after updating left and right segment
        this.segmentTree[i] = this.segmentTree[2*i+1] + this.segmentTree[2*i+2];
        return;
    }
    
    public void update(int index, int val) {
        updateSegmentTree(index,val,0,0,this.n-1);
    }
    
    public int sumRange(int left, int right) {
        return querySum(left,right,0,0,this.n-1);
    }

    public int querySum(int start,int end,int index, int l,int r){
        //base case
        if(l>end || r<start){
            return 0;
        }
        if(l>=start && r<=end){
            return this.segmentTree[index];
        }

        int mid = l+(r-l)/2;

        int leftNode = querySum(start,end,index*2+1,l,mid);
        int rightNode = querySum(start,end,index*2+2,mid+1,r);

        return leftNode+rightNode;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
