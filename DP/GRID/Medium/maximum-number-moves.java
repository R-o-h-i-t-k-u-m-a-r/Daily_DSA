//Leetcode : 2684

class Solution {
    int m;
    int n;
    int dir[] = {-1,0,1};
    public int DFS(int row,int col, int grid[][], int t[][]){
        int count = 0;

        //check if current row and col is already memois or not
        if(t[row][col]!=-1){
            return t[row][col];
        }

        //visit all the three direction of current cell
        for(int i=0;i<3;i++){
            int newRow = row+dir[i];
            int newCol = col+1;

            //validate the newRow and newCol
            if(newRow>=0 && newRow<m && newCol>=0 && newCol<n && grid[row][col]<grid[newRow][newCol]){
                count = Math.max(count, 1+DFS(newRow,newCol,grid, t));
            }
        }

        return t[row][col] = count;
    }
    public int maxMoves(int[][] grid) {
        //Approach : Using Recursion and memoisation
        int result = 0;
        m = grid.length;
        n = grid[0].length;

        //create array for memoisation
        int t[][] = new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            Arrays.fill(t[i],-1);
        }

        for(int row = 0; row<m;row++){
            result = Math.max(result,DFS(row,0,grid,t));
        }

        return result;
    }
}