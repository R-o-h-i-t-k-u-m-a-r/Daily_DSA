package Trie.Medium;

//Leetcode : 2185. Counting Words With a Given Prefix


class Solution {
    static class Trie {
        int prefix = 0;
        Trie child[] =  new Trie[26];
    }

    public void insert(Trie root, String word){
        Trie temp = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(temp.child[index]==null){
                temp.child[index] = new Trie();
            }
            temp = temp.child[index];
            temp.prefix+=1;
        }
    }

    public int search(Trie root, String word){
        Trie temp = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(temp.child[index]==null){
                return 0;
            }
            temp = temp.child[index];
        }
        return temp.prefix;
    }
    public int prefixCount(String[] words, String pref) {
        //Approach : Brute Force using Buit-in method
        //T.C : O(n^l + m), where n = number of words, m = length of pref, l = average length of word
        //S.C : O(n^l)
        
        // int result = 0;
        // for(String word: words){
        //     if(word.startsWith(pref)==true){
        //         result++;
        //     }
        // }

        // return result;

        //Approach 2: Using Trie
        int n = words.length;

        //initialize the root of Trie
        Trie root = new Trie();

        //insert all the words into Trie
        for(String word: words){
            insert(root,word);
        }

        //search prefix pref  into trie
        int result = search(root,pref);

        return result;
    }
}