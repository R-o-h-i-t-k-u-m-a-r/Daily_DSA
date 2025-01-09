package SegmentTree.Medium;

//It is also known as Lazy Propagation
public class UpdateRangeFormat {
    //T.C: O(logn)
    //S.C: O(4*N)
    public void updateRange(int start,int end,int val,int index,int l,int r,int[] segmentTree,int[] lazyTree){
        //check if there is some value in lazy tree or not
        if(lazyTree[index]!=0){
            segmentTree[index]+= (r-l+1)*lazyTree[index];
            //if this is not leaf node then update child node value in lazyTree
            if(l!=r){
                lazyTree[index*2+1]+=lazyTree[index];//left child
                lazyTree[index*2+2]+=lazyTree[index]; //right child
            }
            lazyTree[index] = 0;//reset to 0 after updating to segmentTree
        }
        //out of range case
        if(r<start || l>end || l>r){
            return;
        }
        //within range case 
        if(start<=l && end>=r){
            segmentTree[index]+=(r-l+1)*val; //updating node value with it's total child count
            if(l!=r){//if node has child node then update its child node value into lazyTree
                lazyTree[index*2+1]+=val;//left child 
                lazyTree[index*2+2]+=val;//right child
            }
            //return from root node no need to explore its child node below
            return;
        }
        //overlapping case
        int mid = l+(r-l)/2;
        updateRange(start, end, val, index*2+1, l, mid, segmentTree, lazyTree);
        updateRange(start, end, val, index*2+2, mid+1, r, segmentTree, lazyTree);

        //after updating value into left and right node child we need to update root node value
        segmentTree[index] = segmentTree[index*2+1] + segmentTree[index*2+2];

        return;
    }
}
