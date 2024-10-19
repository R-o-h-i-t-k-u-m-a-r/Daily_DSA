//Leetcode Problem : 1545. Find Kth Bit in Nth Binary String
class Solution {
    public String reverse(String str){
        StringBuilder st = new StringBuilder(str);
        st.reverse();
        //now flip the character
        for(int i=0;i<st.length();i++){
            if(st.charAt(i)=='0'){
                st.setCharAt(i,'1');
            }
            else{
                st.setCharAt(i,'0');
            }
        }
        return st.toString();
    }
    public String func(int n){
        if(n==1){
            return "0";
        }
        return func(n-1) + "1"+ reverse(func(n-1));
    }
    public char findKthBit(int n, int k) {
        //String result = func(n);
        //return result.charAt(k-1);
        if(n==1){
            return '0';
        }

        int length = (1 << n)-1; //pow(2,n)-1

        if(k<Math.ceil(length/2.0)){
            return findKthBit(n-1,k);
        }
        else if(k==Math.ceil(length/2.0)){
            return '1';
        }
        else{
            char ch = findKthBit(n-1,length-(k-1)); //handled reversed
            return ch=='0'?'1':'0'; //handled flipped bit
        }
    }
}