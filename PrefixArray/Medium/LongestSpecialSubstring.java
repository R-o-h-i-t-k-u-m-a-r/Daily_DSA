package PrefixArray.Medium;

//Leetcode : 2981. Find Longest Special Substring That Occurs Thrice I


class Solution {
    public int maximumLength(String s) {
        //Approach 1: Brute Force
        // int  result = -1;
        // int n = s.length();
        // HashMap<String,Integer> freq = new HashMap<>();

        // for(int i=0;i<n;i++){ //O(n)
        //     String str = ""+s.charAt(i);
        //     freq.put(str,freq.getOrDefault(str,0)+1);
        //     for(int j = i+1;j<n;j++){ //O(n)
        //         if(str.charAt(str.length()-1)!=s.charAt(j)){
        //             break;
        //         }
        //         str+=s.charAt(j); //O(n)
        //         //System.out.println("Speccial "+str);
        //         freq.put(str,freq.getOrDefault(str,0)+1);
        //     }
        // }

        // for(Map.Entry<String,Integer> set: freq.entrySet()){
        //     if(set.getValue()>=3){
        //         result = Math.max(result,set.getKey().length());
        //     }
        // }

        // return result;
        
        //Appraoch 2: Optimise of brute force
        // HashMap<Pair<Character,Integer>,Integer> map = new HashMap<>();
        // int n = s.length();
        // for(int i=0;i<n;i++){
        //     char ch = s.charAt(i);
        //     int l = 0;
        //     for(int j=i;j<n;j++){
        //         if(s.charAt(j)!=ch){
        //             break;
        //         }
        //         l++;
        //         Pair<Character,Integer> pair = new Pair<>(ch,l);
        //         map.put(pair,map.getOrDefault(pair,0)+1);
        //     }
        // }

        // int result = -1;
        // for(Map.Entry<Pair<Character,Integer>,Integer> set: map.entrySet()){
        //     if(set.getValue()>=3){
        //         result = Math.max(result,set.getKey().getValue());
        //     }
        // }

        // return result;

        //Approach 3: By Storing sub-string freq in 2d table
        int n = s.length();
        int freq[][] = new int[26][n+1];
        char ch = s.charAt(0);
        int length = 0;
        for(int i=0;i<n;i++){
            if(ch==s.charAt(i)){
                length++;
                freq[ch-'a'][length]+=1;
            }
            else{
                length = 1;
                freq[s.charAt(i)-'a'][length]+=1;
                ch = s.charAt(i);
            }
        }

        int result = -1;

        for(int i = 0;i<26;i++){
            int cumSum = 0;
            for(int j = n;j>=1;j--){
                cumSum+=freq[i][j];
                if(cumSum>=3){
                    result = Math.max(result,j);
                    break;
                }
            }
        }

        return result;
        
    }
}