package Trie.Hard;

//Leetcode : 2416. Sum of Prefix Scores of Strings


class Solution {
    static class Trie {
        int prefix = 0; // stores the number of prefixes ending here
        Trie children[] = new Trie[26]; // array to store children
    }

    public void insert(Trie root,String word){
        Trie temp = root;

        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(temp.children[index]==null){
                temp.children[index] = new Trie();
            }
            temp = temp.children[index];//shifte the root node to it's child
            temp.prefix+=1; //increase the child node prefix by one
        }
    }

    public int search(Trie root, String word){
        Trie temp = root;
        int sum = 0;

        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(temp.children[index]==null){
                break;
            }
            temp = temp.children[index];
            sum+=temp.prefix;
        }

        return sum;
    }
    public int[] sumPrefixScores(String[] words) {
        //Approach : Using Trie
        //T.C : O(n*l), n = number of words, l = average length of each word
        //S.C : O(n*l), need to store all characters of words

        //initialize the root of Trie 
        Trie root = new Trie();

        //insert all the word into trie
        for(String str: words){
            insert(root,str);
        }
        int n = words.length;
        int result[] = new int[n];

        //find all the prefix score of word from trie
        for(int i=0;i<n;i++){
            result[i] = search(root,words[i]);
        }

        return result;
    }
}