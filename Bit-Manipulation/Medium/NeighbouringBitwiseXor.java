//Leetcode : 2683. Neighboring Bitwise XOR

class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        //Approach 1: Using XOR Associative property(a^b=c then b^c=a or a^c=b)
        // int n = derived.length;
        // int original[] = new int[n];
        // //taking 1 as assumption
        // original[0] = 1;
        // for(int i=1;i<n;i++){
        //     original[i] = original[i-1]^derived[i-1];
        // }
        // //validate from the last and first element xor
        // if((original[n-1]^original[0])==derived[n-1]){
        //     return true;
        // }
        // //if first assumtion becomes false then taking 0 as first element
        // original[0] = 0;
        // for(int i=1;i<n;i++){
        //     original[i] = original[i-1]^derived[i-1];
        // }
        // //validate from the last and first element xor
        // if((original[n-1]^original[0])==derived[n-1]){
        //     return  true;
        // }

        // return false;

        //Approach 2: XOR duplicate cancellation property 
        //if value of derived array{a,b,c,d} is {a^b,b^c,c^d,d^a}
        //then each value occur two times it means after taking xor of whole array it 
        //should be equal to zero if there any  original array exist for derived array
        int xor = 0;
        for(int num: derived){
            xor^=num;
        }
        if(xor==0){
            return true;
        }
        return false;

    }
}
