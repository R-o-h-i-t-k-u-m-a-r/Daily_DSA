//GFG: https://www.geeksforgeeks.org/problems/height-of-binary-tree/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
class Solution {
    //Function to find the height of a binary tree.
    int height(Node node) 
    {
        //Approach : Using Recursion IBH
        //Step 3: Base case part of IBH
        //bae case: if node is null then height should be 0
        if(node==null){
            return 0;
        }
        //base case: if node is leaf node then count 1 it's height
        if(node.left==null && node.right==null){
            return 1;
        }
        
        //Step 1: Hypothesis part of IBH
        //Find left subtree height
        int leftSubTreeHeight = height(node.left);
        //Find right substree height
        int rightSubTreeHeight = height(node.right);
        
        //Pick the maximum height between left and right substree
        //and add 1 for counting root node height
        
        //Step 2: Induction part of IBH
        return 1+Math.max(leftSubTreeHeight,rightSubTreeHeight);
    }
}
