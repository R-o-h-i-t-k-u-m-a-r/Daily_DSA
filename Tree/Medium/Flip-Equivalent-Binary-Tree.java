//Leetcode: 951

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        //Approach : Using Recursion
        //if both root node is null then they are equal 
        if(root1==null && root2==null){
            return  true;
        }
        //check flip if root1 and root2 has same value 
        if(root1!=null && root2!=null && root1.val==root2.val){
            //check root1 and root2 childs are equivalent or not without fliping
            boolean flip = flipEquiv(root1.left,root2.left) && flipEquiv(root1.right,root2.right);
            // check root1 and root2 childs are equivalent or not when we don't get equal value wihout 
            //fliping
            if(flip==false){
                flip = flipEquiv(root1.left,root2.right) && flipEquiv(root1.right,root2.left);
            }
            return flip;
        }
        else{
            return false;
        }
    }
}