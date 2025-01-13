package Trie.Medium;

//Leetcode : 208. Implement Trie (Prefix Tree)


class TrieNode {
    TrieNode child[];
    boolean endOfWord;
    String word;

    TrieNode(){
        child = new TrieNode[26];
        endOfWord = false;
        word = "";
    }
}
class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for(char ch: word.toCharArray()){
            if(current.child[ch-'a']==null){
                current.child[ch-'a'] = new TrieNode();
            }
            //shift current node to its child node
            current = current.child[ch-'a'];
        }
        //mark complete word at current node
        current.endOfWord = true;
        //assign word at current node
        current.word = word;
    }
    
    public boolean search(String word) {
        TrieNode current = root;
        for(char ch: word.toCharArray()){
            if(current.child[ch-'a']==null){
                return false;
            }
            current = current.child[ch-'a'];
        }
        return current.endOfWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(char ch: prefix.toCharArray()){
            if(current.child[ch-'a']==null){
                return false;
            }
            current = current.child[ch-'a'];
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */