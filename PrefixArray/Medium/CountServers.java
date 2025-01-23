package PrefixArray.Medium;

//Leetcode : 1267. Count Servers that Communicate

class Solution {
    public int countServers(int[][] grid) {
        //Approach : Prefix Sum
        //T.C : O(m*n)
        //S.C : (row+col)
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        int rowServers[] = new int[row];//will track all servers row by row
        int colServers[] = new int[col];//will track all servers col by col

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1){
                    rowServers[i]++;
                    colServers[j]++;
                }
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1 && (rowServers[i]>1 || colServers[j]>1)){
                    count++;
                }
            }
        }

        return count;
    }
}