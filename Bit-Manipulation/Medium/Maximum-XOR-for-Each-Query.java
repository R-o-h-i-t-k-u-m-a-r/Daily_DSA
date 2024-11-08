//Leetcode : 1829 Maximum-XOR-for-Each-Query
class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        //Note: To flip all the bits of number we need to do xor with a mask having all set bits
        //eg: to flip 101 
        // we can create mask of 111
        // and then 101 xor 111 = 010
        // to set all the like set of 3 bits 111 or set 4 bits 1111
        // we can get by doing 2^n - 1 
        //eg 3 set bits will be 2^3-1 = 7 which is equal to 111
        //step 1 : find xor of all the nums 
        int xor = 0;
        for(int num: nums){
            xor = xor ^ num;
        }
        //create mask of having all set bit so that we can use this mask to flip xor bits
        int mask = (1<<maximumBit)-1;
        int n = nums.length;
        int result[] = new int[n];

        for(int i=0;i<n;i++){
            int res = xor ^ mask;
            result[i] = res;
            xor = xor ^ nums[n-i-1]; //excluding the nums element from last
        }

        return result;
    }
}