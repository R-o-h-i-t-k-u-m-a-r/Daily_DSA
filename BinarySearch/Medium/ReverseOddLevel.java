//Leetcode: 2415. Reverse Odd Levels of Binary Tree

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
    public TreeNode reverseOddLevels(TreeNode root) {
        //Approach 1: Using BFS Traversal
        // Queue<TreeNode> queue = new LinkedList<>();
        // queue.offer(root);
        // int level = 0;

        // while(!queue.isEmpty()){
        //     int n = queue.size();
        //     TreeNode levelNode[] = new TreeNode[n];
        //     int index = 0;
        //     while(n-- >0){
        //         TreeNode currNode = queue.poll();
        //         levelNode[index] = currNode;
        //         index++;
        //         if(currNode.left!=null){
        //             queue.offer(currNode.left);
        //         }
        //         if(currNode.right!=null){
        //             queue.offer(currNode.right);
        //         }
        //     }
        //     //swap the level node value if it is odd level
        //     if(level%2==1){
        //         int i = 0;
        //         int j = levelNode.length -1;
        //         while(i<j){
        //             int temp = levelNode[i].val;
        //             levelNode[i].val = levelNode[j].val;
        //             levelNode[j].val = temp;
        //             i++;
        //             j--;
        //         }
        //     }

        //     level++;
        // }

        // return root;

        //Approach 2: Using DFS Traversal
        solve(root.left,root.right,1);

        return root;
    }

    public void solve(TreeNode L, TreeNode R, int level){
        //base case
        if(L==null || R==null){
            return;
        }
        //if level is odd
        if(level%2==1){
            int temp = L.val;
            L.val = R.val;
            R.val = temp;
        }

        solve(L.left,R.right,level+1);
        solve(L.right,R.left,level+1);

        return;
    }
}