//GFG Problem : https://www.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1

class CheckBit {
    // Function to check if Kth bit is set or not.
    static boolean checkKthBit(int n, int k) {
        //Using left shift operator
        //return (n & 1<<k)!=0;
        
        //Using right shift operator
        return (n>>k & 1 )!=0;
    }
}