package Hashing.Medium;

import java.util.HashMap;

//Leetcode : 3160. Find the Number of Distinct Colors Among the Balls

class Solution {
    public int[] queryResults(int limit, int[][] queries) {
       //Approach : Using HashMap
       //T.C : O(n)
       //S.C :O(n)
       HashMap<Integer,Integer> map = new HashMap<>();
       HashMap<Integer,Integer> freq = new HashMap<>();
       int n = queries.length;
       int result[] = new int[n];
       int index = 0;

       for(int query[]: queries){
        
        int u = query[0];
        int v = query[1];
        //if u is not present in the map then insert it
        if(map.containsKey(u)==false){
            map.put(u,v);//color v at index u
            //increase the frequency of color v 
            freq.put(v,freq.getOrDefault(v,0)+1);
        }
        //if u is already present in the map 
        else {
            //get the color
            int color = map.get(u);
            //decrease the frequency of this color
            freq.put(color,freq.get(color)-1);
            //if this color frequency becomes zero then remove this color from frequency map
            if(freq.get(color)<=0){
                freq.remove(color);
            }
            //now over-ride the color v at index u
            map.put(u,v);
            //increase the frequency of color
            freq.put(v,freq.getOrDefault(v,0)+1);
        }
        result[index] = freq.size();
        index+=1;
       }

       return result; 
    }
}
