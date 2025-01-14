package Trie.Medium;

//Leetcode : 648. Replace Words

import java.util.StringTokenizer;
import java.util.List;
import java.util.StringJoiner;
class Solution {
    static class TrieNode {
        boolean endOfWord;
        String word; 
        TrieNode child[];

        TrieNode(){
            child = new TrieNode[26];
            endOfWord = false;
            word = "";
        }
    }

    public void insert(TrieNode root,String word){
        TrieNode current = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            //if node is present then create new 
            if(current.child[index]==null){
                current.child[index] = new TrieNode();
            }
            //if node is present shift current node to it's child node
            current = current.child[index];
        }
        //after inserting all the word characters into trie make end of word true and assign word
        current.endOfWord = true;
        current.word = word;
    }

    public String findRoot(TrieNode root,String word){
        TrieNode current = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            //if child node is not present then no need to look further characters
            if(current.child[index]==null){
                return word;
            }
            //shift current node to it's child node 
            current = current.child[index];
            //encounter first end of word to get the minimum length of string word
            if(current.endOfWord==true){
                return current.word;
            }
        }
        return word;
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        //Approach : Using Trie
        //T.C : o(n*l + m*l) , n = number of words in the dictionary, m be the number of words in the
        //sentence, and l be the average length of each word.
        //S.C : O(n*l + m*l)
        //initialize root of TrieNode
        TrieNode root = new TrieNode();
        //insert all the dictionary string into TrieNode 
        for(String word: dictionary){
            insert(root,word);
        }
        // String words[] = sentence.split("\\s+");
        // StringBuilder result = new StringBuilder();
        // int n = words.length;
        // //search root in trienode
        // for(int i=0;i<n;i++){
        //     if(root.child[words[i].charAt(0)-'a']==null){
        //         if(i==0){
        //             result.append(words[i]);
        //         }
        //         else{
        //             result.append(" "+words[i]);
        //         }
        //         continue;
        //     }
        //     String str = findRoot(root,words[i]);
        //     if(i==0){
        //         result.append(str);
        //     }
        //     else{
        //     result.append(" "+str);
        //     }
        // }

        // return result.toString();

        StringTokenizer st = new StringTokenizer(sentence);
        StringJoiner sj = new StringJoiner(" ");

        while(st.hasMoreTokens()){
            String word = st.nextToken();
            sj.add(findRoot(root,word));
        }

        return sj.toString();
    }
}