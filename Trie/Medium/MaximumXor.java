package Trie.Medium;

//Leetcode : 421. Maximum XOR of Two Numbers in an Array



class Solution {
    static class TrieNode {
        TrieNode left;
        TrieNode right;
    }

    //method to insert number bit into trie node
    public void insert(TrieNode root,int num){
        TrieNode current = root;
        for(int i = 31;i>=0;i--){
            int ith_bit = (num>>i)&1; //by right shifting num i'th time and anding with 1 we get i'th bit
            if(ith_bit==0){
                //we need to go left side of root 
                if(current.left==null)//if left node is not available then create left node
                {
                    current.left = new TrieNode();
                }
                //shift current node to left child
                current = current.left;
            }
            else{
                if(current.right==null){
                    current.right = new TrieNode();//create new right child
                }
                current = current.right; //shift current node to right child
            }
        }
    }

    public int maxXor(TrieNode root,int num){
        TrieNode current = root;
        int xorSum = 0;
        //I am moving from left most bit(MSB) to right most(LSB) to get max answer
        //so as to get set bit 1 in left most position (MSB) to get large decimal value
        for(int i=31;i>=0;i--){
            int ith_bit = (num>>i)&1; //ith bit of num
            if(ith_bit==0)//to xor with 0 we need 1 to get max value
            {
                if(current.right!=null){
                    xorSum+=Math.pow(2,i); //finding decimal value at i'th bit from LSB
                    current = current.right; //shift current node to it's right child
                }
                else{
                    //if not present we need to left side but this will not give max value
                    //so need to calculate value for this side
                    current = current.left;
                }
            }
            else{
                if(current.left!=null){
                    xorSum+=Math.pow(2,i);
                    current = current.left;
                }
                else{
                    current = current.right;
                }
            }
        }

        return xorSum;
    }
    public int findMaximumXOR(int[] nums) {
        //Approach : Using Trie 
        //T.C : O(n*32)
        //create root trie node
        TrieNode root = new TrieNode();

        //Insert all the nums into trie node
        for(int num: nums){
            insert(root,num);
        }

        int max = 0;

        //find maximum xor of all number using trie
        for(int num: nums){
            max = Math.max(max,maxXor(root,num));
        }

        return max;
    }
}