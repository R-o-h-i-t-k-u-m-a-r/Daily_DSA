package GameTheory.Medium;

import java.util.ArrayList;

//Leetcode : 2017. Grid Game


class Solution {
    public long gridGame(int[][] grid) {
        // int col = grid[0].length;
        // ArrayList<int[]> pathRobot1 = new ArrayList<>();

        // // Step 1: Calculate the optimal path for Robot 1
        // getPath(grid, 0, 0, col, pathRobot1);

        // System.out.println("Path visited by Robot 1 is ");
        // for(int cell[]: pathRobot1){
        //     System.out.println("row "+cell[0]+" col "+cell[1]);
        // }

        // // Step 2: Update the grid by marking Robot 1's path as zero
        // for (int[] cell : pathRobot1) {
        //     grid[cell[0]][cell[1]] = 0;
        // }

        // // Step 3: Calculate the maximum score for Robot 2
        // return DFS(grid, 0, 0, col);
        //Above approach will not work as we are maximizing the robot 1 points to get minimum robot2 points
        //for e.g grid =[[2, 4, 6],[8, 9, 10]]

        //Approach - (Using cumulative sum + game strategy)
        //T.C : O(col), col = total columns in the grid
        //S.C : O(1)

        int col = grid[0].length;

        //get cumulative sum of first row
        long firstRowRemain = 0;
        for(int j=0;j<col;j++){
            firstRowRemain+=grid[0][j];
        }
        
        long secondRowRemain = 0;//to holding secondRow remain points
        long maxPoints = Long.MAX_VALUE;

        for(int j=0;j<col;j++){
            // Robot1 took this strategy
            firstRowRemain-=grid[0][j];
            // Robot2 will try to do best after Robot1 has taken the above strategy
            maxPoints = Math.min(maxPoints,Math.max(firstRowRemain,secondRowRemain));
            secondRowRemain+=grid[1][j];
        }

        return maxPoints;
    }

    // Recursive function to find the optimal path for Robot 1
    private long getPath(int[][] grid, int i, int j, int col, ArrayList<int[]> path) {
        if (i == 1 && j == col - 1) {
            path.add(new int[]{i, j});
            return grid[i][j];
        }

        long curr = grid[i][j];
        long right = Integer.MIN_VALUE;
        long down = Integer.MIN_VALUE;

        ArrayList<int[]> rightPath = new ArrayList<>();
        ArrayList<int[]> downPath = new ArrayList<>();

        if (j + 1 < col) {
            right = getPath(grid, i, j + 1, col, rightPath);
        }
        if (i + 1 < 2) {
            down = getPath(grid, i + 1, j, col, downPath);
        }

        if (right > down) {
            path.addAll(rightPath);
            curr += right;
        } else {
            path.addAll(downPath);
            curr += down;
        }

        path.add(new int[]{i, j});
        return curr;
    }

    // Recursive function to calculate the optimal score for Robot 2
    private long DFS(int[][] grid, int i, int j, int col) {
        if (i == 1 && j == col - 1) {
            return grid[i][j];
        }

        long curr = grid[i][j];
        long right = Integer.MIN_VALUE;
        long down = Integer.MIN_VALUE;

        if (j + 1 < col) {
            right = DFS(grid, i, j + 1, col);
        }
        if (i + 1 < 2) {
            down = DFS(grid, i + 1, j, col);
        }

        curr += Math.max(right, down);
        return curr;
    }
}
