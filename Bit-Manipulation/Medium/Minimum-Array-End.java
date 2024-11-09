// Leetcode: 3133 Minimum Array End
class Solution {
    public long minEnd(int n, int x) {
        //if we do bitwise and of a number to any small number then we always get smaller result 
        //but if do bitwise and with equal or higher number then can get result equal to greater than
        //number so minimum possible number will be x initially.
        long result = x;
        for(int i=1;i<n;i++){
            // adding one to result to get the next adjacent number
            // to get the minimum result value which will be bitwise and equal to x
            // we need to do bitwise or to result with x this will help to set all the set bits of 
            //x into result and will ensure the minimum value that will be equal to bitwise and equal to x
            result = (result+1) | x;
        }
        return result;
    }
}