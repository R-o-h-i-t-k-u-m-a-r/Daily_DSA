//GFG Problem: https://www.geeksforgeeks.org/problems/set-bits0143/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

class Solution {
    static int setBits(int n) {
        //here we will use the the techquique of reseting last set bit of a number till the 
        // the number get into 0.
        int count = 0;
        while(n!=0){
            n = n & n-1;
            count++;
        }
        return count;
    }
}