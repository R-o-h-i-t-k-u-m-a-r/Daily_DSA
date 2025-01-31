package Graph.DFS;

import java.util.*;

//Leetcode : 827. Making A Large Island


class Solution {
    int n;
    int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}}; //top right down left
    public int DFS(int i,int j, int grid[][], boolean visited[][]){
        //base case
        if(i<0 || i>=n || j<0 || j>=n || grid[i][j]==0 || visited[i][j]==true){
            return 0;
        }
        //mark visited 
        visited[i][j] = true;
        int sum = 1;

        //explore all the four neighbour
        for(int dir[]: directions){
            int row = i + dir[0];
            int col = j + dir[1];
            sum+=DFS(row,col,grid,visited);
        }
        return sum;
    }
    public int largestIsland(int[][] grid) {
        //Approach : Brute Force
        //T.C : O(n^4)
        //Intution: check each possiblities after changing each 0 to 1
        // n = grid.length;
        // int result = 0;

        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(grid[i][j]==0){
        //             //update the grid value from 0 to 1
        //             grid[i][j] = 1;
        //             boolean visited[][] = new boolean[n][n];
        //             int max = 0;

        //             for(int x =0;x<n;x++){
        //                 for(int y=0;y<n;y++){
        //                     if(grid[x][y]==1 && visited[x][y]==false){
        //                         max = Math.max(max,DFS(x,y,grid,visited));
        //                     }
        //                 }
        //             }
        //             //undo the update from 1 to 0
        //             grid[i][j] = 0;
        //             result = Math.max(result,max);
        //         }
        //     }
        // }

        //Approach : Better than Brute force
        //Intution : we will not recompute the dfs of whole grid after updating each time
        // n = grid.length;
        // int m = grid.length;
        // int maxArea = 0;
        // boolean visited[][] = new boolean[n][n];

        // //find max large island from grid without changing any thing
        // for(int i = 0; i<m; i++) {
        //     for(int j = 0; j<n; j++) {
        //         if(grid[i][j] == 1 && !visited[i][j]) {
        //             int size = DFS(i, j,grid, visited);
        //             maxArea = Math.max(maxArea, size);
        //         }
        //     }
        // }

        
        // for(int i = 0; i < m; i++) {
        //     for(int j = 0; j < n; j++) {
        //         if(grid[i][j] == 0) {  // Try converting each 0 to 1
        //             grid[i][j] = 1;
                    
        //             visited = new boolean[n][n];
        //             int size = DFS(i, j,grid, visited);
                    
        //             maxArea = Math.max(maxArea, size);
        //             grid[i][j] = 0; // Backtrack
        //         }
        //     }
        // }

        // return (maxArea == 0) ? m * n : maxArea; // If the grid was full of 1s

        //Approach 3: By Hashing technique
        n = grid.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        int id = 2;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    int size = mapDFS(i,j,id,grid);
                    map.put(id,size);//store the size of island having this id
                    id+=1; //increase the id by one
                }
            }
        }
        int result = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    HashSet<Integer> set = new HashSet<>();
                    for(int dir[]: directions){
                        int new_i = i+dir[0];
                        int new_j = j+dir[1];

                        if(new_i>=0 && new_i<n && new_j>=0 && new_j<n && grid[new_i][new_j]!=0){
                            set.add(grid[new_i][new_j]);
                        }
                    }
                    int sum = 0;
                    for(int num: set){
                        sum+=map.get(num);
                    }
                    result = Math.max(result,sum+1);
                }
            }
        }

        return result==0?n*n:result;
    }

    public int mapDFS(int i, int j,int id,int grid[][]){
        //base case
        if(i<0 || i>=n || j<0 || j>=n ||  grid[i][j]!=1){
            return 0;
        }
        //assign the id 
        grid[i][j] = id;
        int sum = 1;

        for(int dir[]: directions){
            sum+=mapDFS(i+dir[0],j+dir[1],id,grid);
        }
        return sum;
    }
}