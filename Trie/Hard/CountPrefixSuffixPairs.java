package Trie.Hard;

//Leetcode : 3042. Count Prefix and Suffix Pairs I


class Trie {
    Trie child[] = new Trie[26];
 }
class Solution {

    public void insert(Trie root, String word){
        Trie temp = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(temp.child[index]==null){
                temp.child[index] = new Trie();
            }
            temp = temp.child[index];
        }
    }

    public boolean search(Trie root,String word){
        Trie temp = root;

        for(char ch: word.toCharArray()){
            int index =  ch - 'a';
            if(temp.child[index]==null){
                return false;
            }
            temp = temp.child[index];
        }
        return true;
    }
    public int countPrefixSuffixPairs(String[] words) {
        //Approach 1: Brute Force using built-in function
        // int count = 0;
        // int n = words.length;

        // for(int i=0;i<n;i++){
        //     for(int j=i+1;j<n;j++){
        //         if(words[j].startsWith(words[i]) && words[j].endsWith(words[i])){
        //             count++;
        //         }
        //     }
        // }

        // return count;

        //Approach 2: Using Trie
        // T.C : ~O(n^2*l), n = number of words, l = average length of each word
        // S.C : ~O(n*l), need to store all characters of words
         
        int n = words.length;

        int count = 0;

        for(int j=0;j<n;j++){
            Trie prefixTree = new Trie();
            Trie suffixTree = new Trie();

            String word = words[j];
            String reverseWord = new StringBuilder(word).reverse().toString();

            //insert both word and reverseWord into prefix and suffix tree
            insert(prefixTree,word);
            insert(suffixTree,reverseWord);

            for(int i = 0;i<j;i++){
                String prefixWord = words[i];
                String suffixWord = new StringBuilder(prefixWord).reverse().toString();

                if(search(prefixTree,prefixWord) && search(suffixTree,suffixWord)){
                    count++;
                }
            }
        }

        return count;
    }
}