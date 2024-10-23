// Leetcode: https://leetcode.com/problems/cousins-in-binary-tree-ii/description/?envType=daily-question&envId=2024-10-23
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
    public TreeNode approachOne(TreeNode root){
        //Approach 1: Using Two BFS Traversal 
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);

        //Step 1: impliment first bfs traversal to get sum of each level
        while(!queue.isEmpty()){
            int n = queue.size();
            int levelSum = 0;
            while(n!=0){
                TreeNode currNode = queue.poll();
                levelSum+=currNode.val;
                if(currNode.left!=null){
                    queue.offer(currNode.left);
                }
                if(currNode.right!=null){
                    queue.offer(currNode.right);
                }
                n--;
            }
            list.add(levelSum);
        }

        //Step 2: impliment second bfs traversal to get the cousins value
        queue.offer(root);
        root.val = 0; // mark root node cousin to 0 as root will not have any cousin
        int levelIndex = 1; //we will start from second level to find cousin 
        

        while(!queue.isEmpty()){
            int n = queue.size();
            while(n!=0){
                TreeNode currNode = queue.poll();
                
                //get the currNode sibling sum
                int siblingSum = currNode.left!=null?currNode.left.val: 0;
                siblingSum+= currNode.right!=null?currNode.right.val:0;
                //now update the currNode sibling value
                if(currNode.left!=null){
                    currNode.left.val = list.get(levelIndex) - siblingSum;
                    queue.offer(currNode.left);
                }
                if(currNode.right!=null){
                    currNode.right.val = list.get(levelIndex) - siblingSum;
                    queue.offer(currNode.right);
                }
                n--;
            }
            levelIndex++;
        }

        return root;
    }
    public TreeNode replaceValueInTree(TreeNode root) {
        if(root == null){
            return root;
        }
        // Appraoch 2: Using single BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSum = root.val;

        //impliment bfs traversal
        while(!queue.isEmpty()){
            int n = queue.size();
            int nextLevelSum = 0;
            while(n!=0){
                TreeNode currNode = queue.poll();
                //update the currNode value 
                currNode.val = levelSum - currNode.val;
                //get the currNode child value
                int siblingSum = currNode.left!=null?currNode.left.val:0;
                siblingSum+=currNode.right!=null?currNode.right.val:0;
                //update currNode child node value with its total sibling value
                if(currNode.left!=null){
                    //store the nextlevelsum first before update the node value
                    nextLevelSum+=currNode.left.val;
                    currNode.left.val = siblingSum;
                    queue.offer(currNode.left);
                }
                if(currNode.right!=null){
                    nextLevelSum+=currNode.right.val;
                    currNode.right.val = siblingSum;
                    queue.offer(currNode.right);
                }
                n--;
            }
            levelSum = nextLevelSum;
        }

        return root;
    }
}