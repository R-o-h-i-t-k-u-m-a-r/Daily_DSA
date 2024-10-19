//GFG Problem: https://www.geeksforgeeks.org/turn-off-the-rightmost-set-bit/

public class GfG {

    // unsets the rightmost set bit 
    // of n and returns the result 
    public static int unsetLSB(int n) {
        return (n & (n - 1));
    }

    // Driver Code 
    public static void main(String[] args) {
        int n = 7;
        System.out.println(unsetLSB(n));
    }
}
