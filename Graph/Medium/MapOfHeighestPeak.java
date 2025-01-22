package Graph.Medium;

import java.util.*;

//Leetcode : 1765. Map of Highest Peak


class Solution {
    public int[][] highestPeak(int[][] isWater) {
        //Approach 1: Multi-Source BFS Traversal
        //T.C : O(m * n) , We will visit all cells once
        //S.C : O(m * n), in worst case queue will contain all the cells
        int row = isWater.length;
        int col = isWater[0].length;
        int height[][] = new int[row][col];

        // Initialize the queue with all water cells and set their height to 0
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(isWater[i][j]==1){
                    height[i][j] = 0;
                    queue.offer(new int[]{i,j});
                }
                else{
                    height[i][j] = -1;
                }
            }
        }
        //start multi-source BFS traversing from all the water cells as source
        int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}}; //up right down left
        while(queue.isEmpty()==false){
            int cell[] = queue.poll();
            int i = cell[0];
            int j = cell[1];

            //explore all the four valid adjacent cell to assign height 
            for(int dir[]: directions){
                int new_i = i+dir[0];
                int new_j = j+dir[1];

                //if current cell is under bound and it is not visited prevoiusly
                if(new_i>=0 && new_j>=0 && new_i<row && new_j<col && height[new_i][new_j]==-1){
                    //add one more hight to its adjacent cell so that absoulute diff will be 1
                    height[new_i][new_j] = height[i][j] + 1;
                    //insert this cell into queue
                    queue.offer(new int[]{new_i,new_j});
                }
            }
        }

        return height;
    }
}