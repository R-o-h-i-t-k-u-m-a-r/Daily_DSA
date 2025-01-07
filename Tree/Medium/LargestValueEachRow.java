package Tree.Medium;

import java.util.List;

//Leetcode: 515. Find Largest Value in Each Tree Row


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
    public List<Integer> largestValues(TreeNode root) {
        //Appraoch : BFS traversal
        //T.C: O(V+E)
        //List<Integer> result = new ArrayList<>();
        //if there is no tree
        // if (root == null) {
        //     return result;
        // }

        // Queue<TreeNode> queue = new LinkedList<>();
        // queue.offer(root);

        // while(queue.isEmpty()==false){
        //     int n = queue.size();
        //     int max = Integer.MIN_VALUE;
        //     while(n-- >0){
        //         TreeNode node = queue.poll();
        //         max = Math.max(max,node.val);
        //         if(node.left!=null){
        //             queue.offer(node.left);
        //         }
        //         if(node.right!=null){
        //             queue.offer(node.right);
        //         }
        //     }
        //     result.add(max);
        // }

        // return result;

        //Approach: DFS Traversal
        //T.C : O(V+E)
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }

        DFS(root,0,result);

        return result;
    }

    public void DFS(TreeNode root,int depth, List<Integer> result){
        if(root==null){
            return;
        }
        if(depth==result.size()){
            result.add(root.val);
        }
        else{
            result.set(depth,Math.max(result.get(depth),root.val));
        }

        DFS(root.left,depth+1,result);
        DFS(root.right,depth+1,result);

        return;
    }
}