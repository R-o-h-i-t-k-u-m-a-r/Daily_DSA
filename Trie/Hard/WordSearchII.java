package Trie.Hard;

import java.util.*;

//Leetcode : 212. Word Search II

class TrieNode {
    TrieNode child[];
    String word;
    boolean endOfWord;

    TrieNode(){
        child = new TrieNode[26]; //all small case alphabets 
        word = "";
        endOfWord = false;
    }
}

class Trie {
    TrieNode root;
    //Initialize the root
    Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode current = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(current.child[index]==null){
                current.child[index] = new TrieNode(); //if child is not present then create new child node
            }
            current = current.child[index]; //move pointer to it's child node
        }
        current.word = word; //assign string word after inserting all the characters
        current.endOfWord = true; //mark true end of word
    }
}


class Solution {
    int row;
    int col;
    int directions[][] = {{1,0},{0,1},{-1,0},{0,-1}}; //top right bottom left

    public void DFS(int i,int j,char[][] board,TrieNode root,ArrayList<String> result){
        //base case
        if(i<0 || i>=row || j<0 || j>=col || board[i][j]=='$' || root.child[board[i][j]-'a']==null){
            return;
        }
        //shift root node to it's child node
        int index = board[i][j] - 'a';
        root = root.child[index];
        //if we have find the word in trienode then store into result
        if(root.endOfWord==true){
            result.add(root.word);
            //mark false of end of word for duplicate safety
            root.endOfWord = false;
        }
        
        char temp = board[i][j];
        //mark visited in board char cell for revisit safety
        board[i][j] = '$';

        //explore all four direction for current board cell character to matched any word in trie
        for(int dir[]: directions){
            int new_row = i + dir[0];
            int new_col = j + dir[1];
            //search this new board cell character into trienode 
            DFS(new_row,new_col,board,root,result);
        }

        //after exploring all four directions re-assign board cell character to previous 
        board[i][j] = temp;
    }
    public List<String> findWords(char[][] board, String[] words) {
        //Approach : Using Trie
        row = board.length;
        col = board[0].length;

        //create root node trie
        Trie trie = new Trie();

        //insert all the words into trie 
        for(String word: words){
            trie.insert(word);
        }

        //creating list to store word
        ArrayList<String> result = new ArrayList<>();

        //Now iterate to board matrix to find word present in trie
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //start searching word which first character present in trie
                char ch = board[i][j];
                if(trie.root.child[ch-'a']!=null){
                    DFS(i,j,board,trie.root,result);
                }
            }
        }

        return result;
    }
}

