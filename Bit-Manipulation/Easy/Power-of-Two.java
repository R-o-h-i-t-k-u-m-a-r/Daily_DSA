//Leetcode : 231. Power of Two

class Solution {
    public boolean isPowerOfTwo(int n) {
        //Number having power of 2 will only have one set bit and other will be reset
        //so we check if its last bit is set or not then we could find it .
        if(n<=0){
            return false;
        }
        return (n & n-1)==0;
    }
}