package DifferenceArary.Medium;

//Leetcode : 2381. Shifting Letters II


class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        //Approach : Difference Array Technique
        int n = s.length();
        //create difference array
         int diff[] = new int[n];
         for(int shift[]: shifts){//O(Q)
            int L = shift[0];
            int R = shift[1];
            int dir = shift[2]==0?-1:1;
            diff[L]+=dir;
            if(R+1<n){
                diff[R+1]-=dir;
            }
         }
         //calculate cumulative sum of difference array
         for(int i=1;i<n;i++){ //O(N)
            diff[i]+=diff[i-1];
         }
         StringBuilder result = new StringBuilder();
         for(int i=0;i<n;i++){
            int value = diff[i]%26; //wrap around for larger than 25
            if(value<0){
                value+=26; //wrap around for smaller than 0
            }
            char ch = (char)('a'+(s.charAt(i)-'a'+value)%26);
            result.append(ch);
         }

         return result.toString();
    }
}