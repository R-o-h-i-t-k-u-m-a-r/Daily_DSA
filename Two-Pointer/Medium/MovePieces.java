//Leetcode : 2337. Move Pieces to Obtain a String

import java.util.HashMap;

class Solution {
    public boolean canChange(String start, String target) {
        //Approach 1: Brute Force(Using Recursion to generate all possible combination)
        //Not Worked due to MLE 
        // HashMap<String,Boolean> map = new HashMap<>(); //for memoisation
        // return solve(start.toCharArray(),start.length(),target,map);

        //Appraoch 2: Using Two Pointer
        //T.C : O(n)
        int n = start.length();
        int i = 0; //pointer for start
        int j = 0; //pointer for target

        while(i<n || j<n){
            //ignore the underscore if found
            while(i<n && start.charAt(i)=='_'){
                i++;
            }
            while(j<n && target.charAt(j)=='_'){
                j++;
            }
            //if any of strings iteration has been completed
            if(i==n || j==n){
                return i==n && j==n; //return true if both string is traversed completely
            }
            //if there is different character at index i and j 
            if(start.charAt(i)!=target.charAt(j)){
                return false;
            }
            
            if(start.charAt(i)=='L' && i<j){
                return false; //L can not move to right
            }
            if(start.charAt(i)=='R' && i>j){
                return false; //R can not move to left
            }
            i++;
            j++;
        }

        return true;
    }

    public boolean solve(char arr[], int n, String target, HashMap<String,Boolean> map){
        String str = String.valueOf(arr);
        if(str.equals(target)){
            return true;
        }
        //memoisation
        if(map.containsKey(str)){
            return map.get(str);
        }
        for(int i=0;i<n;i++){
            char ch = arr[i];
            if(ch=='_'){
                continue;
            }
            else if(ch=='L' && i>0 && arr[i-1]=='_'){
                //swap it 
                char temp[] = arr.clone();
                temp[i-1] = 'L';
                temp[i] = '_';
                if(solve(temp,n,target,map)){
                    return true;
                }
                
            }
            else if(ch=='R' && i+1<n && arr[i+1]=='_'){
                //swap it
                char temp[] = arr.clone();
                temp[i+1] = 'R';
                temp[i] = '_';
                if(solve(temp,n,target,map)){
                    return true;
                }
                
               
            }
        }

        map.put(str,false);
        return false;
    }
}