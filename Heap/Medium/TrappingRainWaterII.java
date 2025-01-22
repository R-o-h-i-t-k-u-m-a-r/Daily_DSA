package Heap.Medium;

import java.util.*;

//Leetcode : 407. Trapping Rain Water II


class Solution {
    public int trapRainWater(int[][] heightMap) {
        //Approach : Min-Heap
        //T.C : O(m*n log(m*n))
        //S.C : O(m*n)
        int row = heightMap.length;
        int col = heightMap[0].length;
        //creating min-heap to store height and cordinate of each cell
        PriorityQueue<int[]> boundryCells = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        boolean visited[][] = new  boolean[row][col];//visited array to not re-visit the cell

        //inserting first and last column of the grid into boundryCells
        for(int i=0;i<row;i++){
            boundryCells.offer(new int[]{heightMap[i][0],i,0});
            visited[i][0] = true;

            boundryCells.offer(new int[]{heightMap[i][col-1],i,col-1});
            visited[i][col-1] = true;
        }
        //inserting first and last row of the grid into boundryCells
        for(int j=0;j<col;j++){
            boundryCells.offer(new int[]{heightMap[0][j],0,j});
            visited[0][j] = true;

            boundryCells.offer(new int[]{heightMap[row-1][j],row-1,j});
            visited[row-1][j] = true;
        }

        int water = 0;
        int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}};//up right down left 
        //explore all the boundry cells using min-heap and store the amount of water
        while(!boundryCells.isEmpty()){
            int cell[] = boundryCells.poll();
            int height = cell[0];
            int i = cell[1];
            int j = cell[2];

            //now explore all four adjacent building of currrent cell
            for(int dir[]: directions){
                int new_i = i + dir[0];
                int new_j = j + dir[1];

                if(new_i>=0 && new_i<row && new_j>=0 && new_j<col && visited[new_i][new_j]==false){
                    //get the amount of water this adcent building will hold with current cell
                    water+=Math.max(height-heightMap[new_i][new_j],0);
                    //insert this adjjacent building into min-heap with updated height
                    boundryCells.offer(new int[]{Math.max(height,heightMap[new_i][new_j]),new_i,new_j});
                    //make this adjacent cell visited
                    visited[new_i][new_j] = true;
                }
            }
        }

        return water;
    }
}