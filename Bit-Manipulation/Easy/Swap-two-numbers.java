// GFG Problem : https://www.geeksforgeeks.org/problems/swap-two-numbers3844/1

class Solution{
    static List<Integer> get(int a,int b)
    {
        // Here we will use bit wise XOR property to remove the same bit
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        
        return List.of(a,b);
    }
}