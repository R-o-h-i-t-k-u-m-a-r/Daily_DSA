package String.Easy;

//Leetcode : 1790. Check if One String Swap Can Make Strings Equal

class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        //Approach 1: Brute Force
        // if(s1.equals(s2)==true){
        //     return true;
        // }
        // StringBuilder str2 = new StringBuilder(s2);
        // int n = s1.length();

        // for(int i=0;i<n;i++){
        //     char ch1 = str2.charAt(i);
        //     for(int j=i+1;j<n;j++){
        //         char ch2 = str2.charAt(j);
        //         //swap the i'th and j'th characters
        //         str2.setCharAt(i,ch2);
        //         str2.setCharAt(j,ch1);
        //         if(s1.equals(str2.toString())==true){
        //             return true;
        //         }
        //         //if not equal after swapping then unswap back
        //         str2.setCharAt(i,ch1);
        //         str2.setCharAt(j,ch2);
        //     }
        // }

        // return false;
        

        //Approach 2: Better 
        //T.C : O(n)
        //Intution: the string s1 and s2 can be equal if they have exactly two different positions
        //of character as we are allowed to swap only one time and if s1 contain some character that
        //string s2 does not contain then they can not be equal
        // if(s1.equals(s2)==true){
        //     return true;
        // }

        // int freq[] = new int[26];//to store frequency of all 26 alphabets
        // int n = s1.length();
        // int diff_count = 0;//to store the count of character difference
        // for(int i=0;i<n;i++){
        //     int index1 = s1.charAt(i) - 'a';
        //     int index2 = s2.charAt(i) - 'a';
        //     freq[index1]+=1;//increase the freq of this character
        //     freq[index2]-=1; //decrease the freq of this character
        //     if(s1.charAt(i)!=s2.charAt(i)){
        //         diff_count+=1;
        //         if(diff_count>2){//we can not swap more than two different characters
        //             return false;
        //         }
        //     }
        // }
        // //if both string will have same characters then their frequecy will be zero
        // for(int i=0;i<26;i++){
        //     //if not zero means this character is not common in both strings
        //     if(freq[i]!=0){
        //         return false;
        //     }
        // }
        // return true;

        //Appraoch : Optimal 
        //T.C : O(n)
        int n = s1.length();
        if(s1.equals(s2)==true){
            return true;
        }
        int first = 0;
        int second = 0;
        int diff_count = 0;
        for(int i=0;i<n;i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                diff_count++;
                if(diff_count>2){
                    return false;
                }
                else if(diff_count==1){
                    first = i;
                }
                else{
                    second = i;
                }
            }
        }

        //if there is character difference of two then should present in both strings
        return (s1.charAt(first)==s2.charAt(second)) && (s1.charAt(second)==s2.charAt(first));
    }
}