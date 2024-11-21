//Leetcode : 2257. Count Unguarded Cells in the Grid

class Solution {
    int row;
    int col;
    int count = 0;
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        //Approach : Using Recursion
        row = m;
        col = n;
        int matrix[][] = new int[row][col];
        //mark gurad cells 
        for(int guard[] : guards){
            matrix[guard[0]][guard[1]] = 1; // mark guard cell by 1
        }
        //mark wall cells
        for(int wall[] : walls){
            matrix[wall[0]][wall[1]] = 2; // mark wall cell by 2
        }

        //T.C : O(G*(M+N))
        for(int guard[]: guards){

            //explore top cells
            solve(matrix,guard[0],guard[1]+1,'T');
            //explore right cells
            solve(matrix,guard[0]+1,guard[1],'R');
            //explore bottom cells
            solve(matrix,guard[0],guard[1]-1,'B');
            //explore left cells
            solve(matrix,guard[0]-1,guard[1],'L');

        }
        // int result = 0;
        // //T.C : O(m*n)
        // for(int i=0;i<row;i++){
        //     for(int j=0;j<col;j++){
        //         if(matrix[i][j]==0){
        //             result+=1;
        //         }
        //     }
        // }

        return m*n - guards.length - walls.length - count;
    }
    public void solve(int matrix[][], int i, int j, char dir){
        //base case
        if(i<0 || i>=row || j<0 || j>=col || matrix[i][j]==1 || matrix[i][j]==2){
            return;
        }
        //if current cell is not seen earlier by any guard  then only make this count to ignore dulicate
        if(matrix[i][j]!=-1){
            count++;
        }
        //mark current cell seen by guard
        matrix[i][j] = -1;

        //determine the direction of guard
        //top dir
        if(dir=='T'){
            solve(matrix,i,j+1,dir);
        }
        //right dir
        if(dir=='R'){
            solve(matrix,i+1,j,dir);
        }
        //bottom dir
        if(dir=='B'){
            solve(matrix,i,j-1,dir);
        }
        //left dir
        if(dir=='L'){
            solve(matrix,i-1,j,dir);
        }

        return;
    }
}