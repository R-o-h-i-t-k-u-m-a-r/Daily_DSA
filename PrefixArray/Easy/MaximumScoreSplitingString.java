package PrefixArray.Easy;

//Leetcode: 1422. Maximum Score After Splitting a String

class Solution {
    public int maxScore(String s) {
        //Approach 1: Brute Force
        //T.C : O(n^2)
        // int result = Integer.MIN_VALUE;
        // int leftZero = 0;
        // int n = s.length();
        // for(int i=0;i<n;i++){
        //     if(s.charAt(i)=='0'){
        //         leftZero+=1;
        //     }
        //     int rightOne = 0;
        //     for(int j= i+1;j<n;j++){
        //         if(s.charAt(j)=='1'){
        //             rightOne+=1;
                    
        //         }
                
        //     }
        //     if(rightOne>0){
        //         result = Math.max(result,leftZero+rightOne);
        //     }
        //     else{
        //         result = Math.max(result,leftZero-1);
        //     }
            
        // }

        // return result;

        //Approach 2: Prifix sum array
        // int n = s.length();
        // int rightOne[] = new int[n];
        // int leftZero = 0;
        // int result = Integer.MIN_VALUE;
        // rightOne[n-1] = 0; //no one's at the right side of last index element
        // //fill rightOne array
        // for(int i=n-2;i>=0;i--){
        //     if(s.charAt(i+1)=='1'){
        //         rightOne[i] = rightOne[i+1] + 1;
        //     }
        //     else{
        //         rightOne[i] = rightOne[i+1];
        //     }
        // }

        // for(int i=0;i<n;i++){
        //     if(s.charAt(i)=='0'){
        //         leftZero+=1;
        //     }
        //     if(rightOne[i]>0){
        //         result = Math.max(result,leftZero+rightOne[i]);
        //     }
        //     else{
        //         result = Math.max(result,leftZero-1);
        //     }
            
        // }

        // return result;

        //Approach 3: Prefix sum variable
        int n = s.length();
        int result = Integer.MIN_VALUE;
        int rightOne = 0;
        int leftZero = 0;

        //count total number of 1's
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                rightOne++;
            }
            else{
                leftZero++;
            }
        }

        //if there is no one's then no need to split
        if(rightOne==0 || leftZero==0){
            return n-1;
        }
        leftZero = 0;
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)=='0'){
                leftZero++;
            }
            else{
                rightOne--;
            }
            result = Math.max(result,leftZero+rightOne);
        }

        return result;
    }
}