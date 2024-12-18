//Leetcode : 2182. Construct String With Repeat Limit

import java.util.PriorityQueue;

class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        // Approach 1: Brute Force
        // int[] count = new int[26]; // Frequency array to store character counts
        
        // // Count the frequency of each character
        // for (char ch : s.toCharArray()) { // T.C: O(n)
        //     count[ch - 'a']++;
        // }

        // StringBuilder result = new StringBuilder();
        // int i = 25; // Start from the largest character (z)

        // while (i >= 0) { // T.C: O(26)
        //     if (count[i] == 0) {
        //         i--;
        //         continue;
        //     }

        //     char ch = (char) ('a' + i); // Convert index to character
        //     int freq = Math.min(count[i], repeatLimit); // Append up to 'repeatLimit' times
            
        //     for (int k = 0; k < freq; k++) {
        //         result.append(ch);
        //     }
        //     count[i] -= freq;

        //     if (count[i] > 0) {
        //         // Find the next largest character
        //         int j = i - 1;
        //         while (j >= 0 && count[j] == 0) { // O(26)
        //             j--;
        //         }

        //         if (j < 0) {
        //             break; // No more characters left to append
        //         }

        //         result.append((char) ('a' + j)); // Append the next largest character once
        //         count[j]--;
        //     }
        // }

        // return result.toString(); 

        // Approach 2: Using Max-Heap
        int count[] = new int[26]; //Frequency array to store character counts

        //count the frequency of each character
        for(char ch: s.toCharArray()){
            count[ch-'a']++;
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->Character.compare(b,a)); //max-heap
        StringBuilder result = new StringBuilder();

        //insert all the character into pq
        for(int i=0;i<26;i++){
            if(count[i]!=0){
                char ch = (char) ('a'+ i);
                pq.offer(ch);
            }
        }

        while(pq.isEmpty()==false){
            //get the maximum frequent character
            char ch = pq.poll();
            //get the frequency of maximum character
            int freq = Math.min(count[ch-'a'],repeatLimit);
            //append the character till freq times to the result
            for(int k=0;k<freq;k++){
                result.append(ch);
            }
            //decrease the frequency of max character from frequch array
            count[ch-'a']-=freq;

            //now explore the left most max character
            if(count[ch-'a']>0 && pq.isEmpty()==false){
                char lch = pq.poll();
                result.append(lch);
                count[lch-'a']-=1;
                if(count[lch-'a']>0){
                    pq.offer(lch);
                }
                //insert the maximum character back to pq as its frequency is available
                pq.offer(ch);
            }
        }

        return result.toString();
    }
}