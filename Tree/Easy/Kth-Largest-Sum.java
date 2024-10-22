//Leetcode : https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/?envType=daily-question&envId=2024-10-22

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
    public long kthLargestLevelSum(TreeNode root, int k) {
        // Using BFS Traversal and min-heap
        Queue<TreeNode> queue = new LinkedList<>(); //queue
        PriorityQueue<Long> pq = new PriorityQueue<>(); //min-heap 
        queue.offer(root);
        while(queue.isEmpty()==false){
            long sum = 0;
            int n = queue.size();
            //get the sum of each tree level
            while(n!=0){
                sum+=queue.peek().val;
                //if root node has left child then push it into queue
                if(queue.peek().left!=null){
                    queue.offer(queue.peek().left);
                }
                //if root node has right child then push it into queue
                if(queue.peek().right!=null){
                    queue.offer(queue.peek().right);
                }
                //after adding root child remove it from queue
                queue.poll();
                n--;
            }
            //add the level sum to the list
            pq.offer(sum);
            
            //if min heap has more element than k then remove the top element
            if(pq.size()>k){
                pq.poll();
            }
        }
        //if list has less then k element then return -1
        if(pq.size()<k){
            return -1;
        }

        return pq.peek();
    }
}