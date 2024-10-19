//GFG Problem: https://www.geeksforgeeks.org/toggling-k-th-bit-number/
// Java program to toggle
// k-th bit of a number

class Toggle
{
    static int toggleKthBit(int n, int k)
    {
        // here we will use of bit wise xor to toggle the kth bit
        return (n ^ (1 << (k-1)));
    }
    
    // main function 
    public static void main (String[] args) 
    {   
        int n = 5, k = 1;
        System.out.println(toggleKthBit(n , k));
    }
}