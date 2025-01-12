package MatrixPattern.Easy;

import java.util.*;

//Leetcode : 3417. Zigzag Grid Traversal With Skip


class Solution {
    public List<Integer> zigzagTraversal(int[][] grid) {
        //Approach : Pattern Observation
        //T.C : O(row*col)
        int row = grid.length;
        int col = grid[0].length;
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<row;i++){
            if(i%2==0){ //start left to right traversal in case of even number of row
                for(int j=0;j<col;j++){
                    if(j%2 == i%2)//visit only columns which are even
                    {
                        list.add(grid[i][j]);
                    }
                }
            }
            else//other traverse right to left 
            {
                for(int j=col-1;j>=0;j--){
                    if(j%2==i%2){
                        list.add(grid[i][j]);
                    }
                }
            }
        }

       return list;
    }
}