package String.Easy;

//Leetcode : 1408. String Matching in an Array


class Solution {
    public List<String> stringMatching(String[] words) {
        //Approach 1: Brute Force
        // List<String> result = new ArrayList<>();
        // int n = words.length;

        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(i!=j && words[j].contains(words[i])){
        //             result.add(words[i]);
        //             break;
        //         }
        //     }
        // }

        // return result;

        //Approach 2: Using KMP Algorithm
        int n = words.length;
        List<String> result = new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j && KMP(words[j],words[i])==true){
                    result.add(words[i]);
                    break;
                }
            }
        }

        return result;
    }

    public boolean KMP(String str1, String str2){
        int str2LPS[] = LPS(str2);
        int n = str1.length();
        int m = str2.length();

        int i = 0;
        int j = 0;
        while(i<n && j<m){
            if(str1.charAt(i)!=str2.charAt(j)){
                if(j!=0){
                    j = str2LPS[j-1];
                }
                else{
                    i++;
                }
            }
            else if(str1.charAt(i)==str2.charAt(j)){
                i++;
                j++;
            }
            else{
                i++;
            }
        }

        if(j>=m){
            return true;
        }

        return false;
    }

    public int[] LPS(String str){
        int n = str.length();
        int result[] = new int[n];
        int len = 0;
        int i = 1;

        while(i<n){
            if(str.charAt(i)==str.charAt(len)){
                len++;
                result[i] = len;
                i++;
            }
            else{
                if(len!=0){
                    len = result[len-1];
                }
                else{
                    result[i] = 0;
                    i++;
                }
            }
        }

        return result;
    }
}