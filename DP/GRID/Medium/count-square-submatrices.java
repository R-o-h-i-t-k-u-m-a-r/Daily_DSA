//Leetcode : 1277

class Solution {
    int m;
    int n;
    public int solve(int i, int j, int matrix[][],int t[][]){
        //base case
        if(i>=m || j>=n){
            return 0;
        }
        if(matrix[i][j]==0){
            return 0;
        }
        if(t[i][j]!=-1){
            return t[i][j];
        }
        int right = solve(i,j+1,matrix,t);
        int diagonal = solve(i+1,j+1,matrix,t);
        int below = solve(i+1,j,matrix,t);

        return t[i][j] = 1+Math.min(right,Math.min(diagonal,below));
    }
    public int countSquares(int[][] matrix) {
        //Appraoch 1: Using Recursion with memoisation
        // m = matrix.length;
        // n = matrix[0].length;
        // int t[][] = new int[m+1][n+1];
        // for(int i=0;i<=m;i++){
        //     Arrays.fill(t[i],-1);
        // }
        // int result = 0;

        // for(int i=0;i<m;i++){
        //     for(int j=0;j<n;j++){
        //         //check square if matrix cell having one
        //         if(matrix[i][j]==1){
        //             result+=solve(i,j,matrix,t);
        //         }
        //     }
        // }

        // return result;

        //Approach 2: Using Bottom-Up approach
        m = matrix.length;
        n = matrix[0].length;
        int result = 0;
        int t[][] = new int[m+1][n+1];
        //t[i][j] = total square submatrices (having 1s) ending at cell[i][j]

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 || j==0){
                    t[i][j] = matrix[i][j];
                }
                else{
                    if(matrix[i][j]==1){
                        t[i][j] = 1 + Math.min(t[i-1][j],Math.min(t[i-1][j-1],t[i][j-1]));
                    }
                }
                result+=t[i][j];
            }

            
        }

        return result;
    }
}