package Graph.Medium;
import  java.util.*;

//Leetcode : 2577. Minimum Time to Visit a Cell In a Grid

class Solution {
    public int minimumTime(int[][] grid) {
        // Approach : Using Dijiktra's Algorithm
        // T.C : O(E * log(V))
        // E--> number of edges --> m*n
        // V--> number of vertices--> m*n
        //un reachable case
        if(grid[0][1]>1 && grid[1][0]>1){
            return -1;
        }

        int row = grid.length;
        int col = grid[0].length;
        //creating min heap
        PriorityQueue<int[]> pq =  new PriorityQueue<>((a,b)->a[0]-b[0]); //{time,row,col}
        // int result[][] = new int[row][col];
        boolean visited[][] = new boolean[row][col];
        int directions[][] = {{1,0},{0,1},{-1,0},{0,-1}}; // top , right , bottom, left

        // for(int i=0;i<row;i++){
        //     Arrays.fill(result[i],Integer.MAX_VALUE);
        // }
        // result[0][0] = 0;
        pq.offer(new int[]{0,0,0});

        while(!pq.isEmpty()){
            int arr[] = pq.poll();
            int time = arr[0];
            int i = arr[1];
            int j = arr[2];

            //if we have reached to dist node
            if(i==row-1 && j==col-1){
                // return result[i][j];
                return time;
            }

            //if current node is already visited
            if(visited[i][j]==true) continue;

            //mark visited to current node
            visited[i][j] = true;

            //explore all four directions for current node
            for(int dir[]: directions){
                int newRow = i + dir[0];
                int newCol = j+ dir[1];

                //check boundry condition 
                if(newRow>=0 && newRow<row && newCol>=0 && newCol<col){
                    if(grid[newRow][newCol]<=(time+1)){
                        pq.offer(new int[]{time+1,newRow,newCol});
                        // result[newRow][newCol] = Math.min(result[newRow][newCol],time+1);
                        // result[newRow][newCol] = time+1;
                        

                    }
                    else if((grid[newRow][newCol]-time)%2==0){
                        pq.offer(new int[]{grid[newRow][newCol]+1,newRow,newCol});
                    //result[newRow][newCol] = Math.min(result[newRow][newCol],grid[newRow][newCol]+1);
                        // result[newRow][newCol] = grid[newRow][newCol]+1;
                        
                    }
                    else{
                        pq.offer(new int[]{grid[newRow][newCol],newRow,newCol});
                    //result[newRow][newCol] = Math.min(result[newRow][newCol],grid[newRow][newCol]);
                        // result[newRow][newCol] = grid[newRow][newCol];
                        
                    }
                }
            }
        }

        return -1;
    }
}