package Graph.Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//Leetcode : 2290. Minimum Obstacle Removal to Reach Corner

class Solution {
    public int minimumObstacles(int[][] grid) {
        // Approach 1:  Using 0-1 BFS traversal
        int row = grid.length;
        int col = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>(); // for 0-1 BFS traversal
        queue.add(new int[]{0,0,0});

        //all four direction 
        int directions[][] = {{0,1},{1,0},{-1,0},{0,-1}};

        //visited array 
        boolean visited[][] = new boolean[row][col];

        //apply 0-1 BFS traversal
        while(queue.isEmpty()==false){
            int node[] = queue.poll();
            int i = node[0];
            int j = node[1];
            int dist = node[2];

            if(i==row-1 && j==col-1){
                return dist;
            }
            
            //skip if already visited
            if(visited[i][j]==true) continue;

            //mark current node visited
            visited[i][j] = true;

            //explore the directions
            for(int dir[]: directions){
                int newRow = i + dir[0];
                int newCol = j+ dir[1];

                if(newRow>=0 && newCol>=0 && newRow<row && newCol<col && visited[newRow][newCol]==false){
                    if(grid[newRow][newCol]==0){
                        queue.addFirst(new int[]{newRow,newCol,dist});
                    }
                    else{
                        queue.addLast(new int[]{newRow,newCol,dist+1});
                    }
                    
                }
            }
        }

        return -1;
    }
}