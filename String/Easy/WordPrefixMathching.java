package String.Easy;

//Leetcode : 1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence

class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        // Appraoch : Brute Force
        String words[] = sentence.split(" ");
        int n = words.length;

        for(int i=0;i<n;i++){
            if(words[i].startsWith(searchWord)){
                return i+1;
            }
        }

        return -1;
    }
}
