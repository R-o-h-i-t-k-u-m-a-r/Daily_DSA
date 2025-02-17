package Backtracking.Medium;

//Leetcode : 1079. Letter Tile Possibilities

import java.util.HashSet;

class Solution {
    int count; //global variable for storing the subsets count
    public void solve(String tiles, StringBuilder curr, HashSet<String> result, boolean used[],int n){
        //add the curr string into result
        result.add(curr.toString());

        //find subset of curr string
        for(int i=0;i<n;i++){
            //if character is used before then not include
            if(used[i]==true){
                continue;
            }
            //do
            used[i] = true;
            curr.append(tiles.charAt(i));

            //explore
            solve(tiles,curr,result,used,n);

            //undo: Backtrack
            used[i] = false;
            curr.deleteCharAt(curr.length()-1);
        }
    }

    public void func(int freq[]){
        count++; //increase the setset count

        for(int i=0;i<26;i++){
            if(freq[i]==0){
                continue;
            }
            //do
            freq[i]-=1;
            //explore
            func(freq);
            //undo
            freq[i]+=1;
        }
    }
    public int numTilePossibilities(String tiles) {
        //Approach 1: Resursive Backtracking 
        // T.C : O(n!)
        // S.C : O(n * n!), total possible sequences = n! and each having n length
        // int n = tiles.length();
        // boolean used[] = new boolean[n];
        // HashSet<String> result = new HashSet<>();
        // StringBuilder curr = new StringBuilder();

        // solve(tiles,curr,result,used,n);

        // return result.size()-1;//substraction 1 for empty string

        //Approach 2: Recursive Backtracking with frequency array
        //T.C : O(n!)
        //S.C : O(n)
        
        count = 0;

        //get the frequency of all the characters
        int freq[] = new int[26];
        for(char ch: tiles.toCharArray()){
            freq[ch-'A']+=1;
        }

        func(freq);
        return count-1;
    }
}
