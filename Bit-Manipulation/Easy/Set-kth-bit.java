//GFG Problem : https://www.geeksforgeeks.org/problems/set-kth-bit3724/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

class Solution{
    static int setKthBit(int N,int K){
        // by doing bitwise left shif we set bit 1 at specific position
        // and by doing bitwise or will set that number bit 
        return N | 1<<K ;
    }
}