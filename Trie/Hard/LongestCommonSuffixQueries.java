package Trie.Hard;

//Leetcode : 3093. Longest Common Suffix Queries

class Solution {
    static class TrieNode {
        int index;
        TrieNode child[];

        TrieNode(int index){
            this.index = index; //for minimum longest common suffix string length
            child = new TrieNode[26];//for all 26 small case alphabets
        }
    }
    public void insert(TrieNode root,int index ,String[] wordsContainer){
        TrieNode current = root;
        //insert string character in reverse order
        int n = wordsContainer[index].length();
        String word = wordsContainer[index];
        for(int i=n-1;i>=0;i--){
            int char_idx = word.charAt(i) - 'a';
            //if there is no child associated with this index then create new node
            if(current.child[char_idx]==null){
                current.child[char_idx] = new TrieNode(index);
            }
            //Shift current node to it's child node
            current = current.child[char_idx];
            //update the current node index with minimum length string
            if(wordsContainer[current.index].length()>n){
                current.index = index;
            }
        }
    }

    public int search(TrieNode root,String word){
        TrieNode current = root;
        int result_idx = root.index;
        int n = word.length();
        for(int i=n-1;i>=0;i--){
            int char_idx = word.charAt(i)-'a';
            //if there is no node associated with this character then return the minimum length string
            if(current.child[char_idx]==null){
                return result_idx;
            }
            //if child node is present then shift current node to child node
            current = current.child[char_idx];
            //update the result index with child node index
            result_idx = current.index;
            
        }
        return result_idx;
    }
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        //Approach : Using Trie
        //T.C : O(n)+O(q)
        //S.C : O(n*26)
        //Initialize root of trienode
        TrieNode root = new TrieNode(0); //at root node assuming 0'th index wordscontainer has small length
        //Insert all the string of wordscontainer
        int n = wordsContainer.length;
        for(int i=0;i<n;i++){
            //before inserting string check whether root index string has less length then current string
            int index = root.index;
            if(wordsContainer[index].length()>wordsContainer[i].length()){
                root.index = i;
            }
            insert(root,i,wordsContainer);
        }
        //Query into Trie for Logest common suffix 
        int q = wordsQuery.length;
        int result[] = new int[q]; //result array to store index of logest common suffix
        for(int i=0;i<q;i++){
            result[i] = search(root,wordsQuery[i]);
        }

        return result;
    }
}