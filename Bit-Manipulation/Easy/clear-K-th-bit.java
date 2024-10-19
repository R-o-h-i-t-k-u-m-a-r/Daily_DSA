//GFG Problem : https://www.geeksforgeeks.org/program-to-clear-k-th-bit-of-a-number-n/
// Java program to clear K-th bit of a number N 
class GFG 
{
     
    // Function to clear the kth bit of n 
    static int clearBit(int n, int k) 
    { 
        //here we will use bit wise not operator to only reset the set bit of left shift 
        return (n & (~(1 << (k - 1)))); 
    } 
     
    // Driver code 
    public static void main (String[] args) 
    { 
        int n = 5, k = 1; 
     
        System.out.println(clearBit(n, k)); 
    } 
}
 