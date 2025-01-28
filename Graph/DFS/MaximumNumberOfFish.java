package Graph.DFS;

import java.util.*;

//Leetcode : 2658. Maximum Number of Fish in a Grid


class Solution {
    int directions[][] = {{0,1},{0,-1},{1,0},{-1,0}}; //right left down up

    public int DFS(int i,int j,int grid[][], int row,int col){
        //base case
        if(i<0 || i>=row || j<0 || j>=col || grid[i][j]==0){
            return 0;
        }

        int sum = grid[i][j];
        grid[i][j] = 0;

        //explore all the adjacent cell
        for(int dir[]: directions){
            int new_i = i + dir[0];
            int new_j = j+ dir[1];

            sum+=DFS(new_i, new_j, grid,row,col);
        }

        return sum;
    }

    public int BFS(int i,int j,int grid[][], int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        int sum = grid[i][j];
        grid[i][j] = 0;

        while(queue.isEmpty()==false){
            int curr[] = queue.poll();
            int curr_i = curr[0];
            int curr_j = curr[1];
            

            for(int dir[]: directions){
                int new_i = curr_i + dir[0];
                int new_j = curr_j + dir[1];

                if(new_i>=0 && new_i<row && new_j>=0 && new_j<col && grid[new_i][new_j]>0){
                    queue.offer(new int[]{new_i,new_j});
                    sum+=grid[new_i][new_j];
                    grid[new_i][new_j] = 0;
                }
            }
        }

        return sum;
    }
    public int findMaxFish(int[][] grid) {
        //Approach 1: DFS Traversal
        //Intution: we need to get maximum number of fishes from all the water areas
        //T.C : O(n)
        //S.C : (n)

        int row = grid.length;
        int col = grid[0].length;
        int result = 0;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]>0){
                    result = Math.max(result,DFS(i,j,grid,row,col));
                    //result = Math.max(result,BFS(i,j,grid,row,col));
                }
            }
        }

        return result;
    }
}