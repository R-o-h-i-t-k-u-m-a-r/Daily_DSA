package Trie.Hard;

//Leetcode : 212. Word Search II

class TrieNode {
    boolean endOfWord;
    String word;
    TrieNode children[];

    TrieNode(){
        this.endOfWord = false;
        this.word = "";
        this.children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode current = root;
        for(char ch: word.toCharArray()){
            int index = ch-'a';
            if(current.children[index]==null){
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.endOfWord = true;
        current.word = word;
    }
}
class Solution {
    int row;
    int col;
    int directions[][] = {{-1,0},{1,0},{0,1},{0,-1}};

    public void DFS(char[][] board,int i,int j, TrieNode root, List<String> result){
        //base case
        if(i<0 || i>=row || j<0 || j>=col || board[i][j]=='$' || root.children[board[i][j]-'a']==null){
            return;
        }

        root = root.children[board[i][j]-'a'];
        if(root.endOfWord==true){
            result.add(root.word);
            root.endOfWord = false;
        }

        char temp = board[i][j];
        board[i][j] = '$';

        for(int dir[]: directions){
            int new_i = i + dir[0];
            int new_j = j + dir[1];

            DFS(board,new_i,new_j,root,result);
        }
        board[i][j] = temp;
    }
    public List<String> findWords(char[][] board, String[] words) {
        //Approach : Using Trie
        row = board.length;
        col = board[0].length;
        Trie trie = new Trie();
        List<String> result = new ArrayList<>();
        //insert all the words into Trie
        for(String word: words){
            trie.insert(word);
        }

        //Traverser to board to find all the word occurance
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                char ch = board[i][j];
                if(trie.root.children[ch-'a']!=null){
                    DFS(board,i,j,trie.root,result);
                }
            }
        }

        return result;
        
    }
}
