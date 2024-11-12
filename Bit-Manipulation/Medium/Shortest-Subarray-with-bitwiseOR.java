//Leatcode : 3097. Shortest Subarray With OR at Least K II

class Solution {
    //T.C = O(32)
    public void addNumToWindow(int num,int bitCountWindow[]){
        for(int i = 0;i<32;i++){
            if(((num>>i)&1)!=0){
                bitCountWindow[i]++;
            }
        }
    }
    //T.C = O(32)
    public void removeNumToWindow(int num,int bitCountWindow[]){
        for(int i = 0;i<32;i++){
            if(((num>>i)&1)!=0){
                bitCountWindow[i]--;
            }
        }
    }
    //T.C = O(32)
    public int getDecimalFromBinary(int bitCountWindow[]){
        int result = 0;
        for(int i = 0;i<32;i++){
            if(bitCountWindow[i]!=0){
                result = result | (1<<i);
            }
        }
        return result;
    }
    public int minimumSubarrayLength(int[] nums, int k) {
        //Approach : Using Sliding Window 
        int n = nums.length;
        int result = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int bitCountWindow[] = new int[32]; //this will hold total number of set bit at i'th position


        //impliment sliding window algorithm
        //T.C = O(2*n)
        while(j<n){
            //add current num to window
            addNumToWindow(nums[j],bitCountWindow);

            //check bitwise or of window all elements is at least k or not if it is then shrink the 
            // window size till bitwise or of window all elements is at least 
            while(i<=j && getDecimalFromBinary(bitCountWindow)>=k){
                result = Math.min(result, j-i+1);
                //shrink the window size
                removeNumToWindow(nums[i],bitCountWindow);
                i++;
            }
            j++;
        }

        return result==Integer.MAX_VALUE?-1:result;
    }
}
