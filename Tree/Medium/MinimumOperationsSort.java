package Tree.Medium;

import java.util.*;

//Leetcode : 2471. Minimum Number of Operations to Sort a Binary Tree by Level

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
    

    public int minimumOperations(TreeNode root) {
        //Approach : Using BFS Traversal
        Queue<TreeNode> queue = new LinkedList<>();
        int swaps = 0;
        queue.offer(root);

        while(queue.isEmpty()==false){
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            while(n-- >0){
                TreeNode node = queue.poll();
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                list.add(node.val);
            }
            swaps+=minSwap(list);
        }

        return swaps;
    }

    public int minSwap(List<Integer> list){
        List<Integer> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        int n = list.size();
        int swaps = 0;
        HashMap<Integer,Integer> map = new HashMap<>();//to store index of unsorted list
        for(int i=0;i<n;i++){
            map.put(list.get(i),i);
        }
        for(int i=0;i<n;i++){
            if(sortedList.get(i)==list.get(i)){
                continue;
            }
            //swap the elements to each index
            int currIdx = map.get(sortedList.get(i));
            map.put(list.get(i),currIdx);
            map.put(list.get(currIdx),i);
            Collections.swap(list,currIdx,i);
            swaps++;
        }

        return swaps;
    }
}