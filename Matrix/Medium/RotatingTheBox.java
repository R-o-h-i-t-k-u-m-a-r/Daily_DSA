package Matrix.Medium;
//Leetcode : 1861. Rotating the Box

class Solution {
    public char[][] rotateTheBox(char[][] box) {
        //Approach 1 : Brute Force or Simulation
        //T.C : O(row*col + col*row*col)
        // int row = box.length;
        // int col = box[0].length;
        // char matrix[][] = new char[col][row];

        // //invert the box array to 90 degrees colockwise
        // for(int i=0;i<row;i++){
        //     for(int j=0;j<col;j++){
        //         matrix[j][row-i-1] = box[i][j];
        //     }
        // }
        // System.out.println("Inverted matrix is: ");
        // for(int i=0;i<col;i++){
        //     for(int j=0;j<row;j++){
        //         System.out.print(matrix[i][j]+" ");
        //     }
        //     System.out.println();
        // }

        //check gravity from bottom of matrix of each stones
        // for(int i=col-1;i>=0;i--){
        //     for(int j=0;j<row;j++){
        //         //if any space is found
        //         if(matrix[i][j]=='.'){
        //             int stoneRow = -1;

        //             //explore the index of first stone found from all the above empty space
        //             for(int k=i-1;k>=0;k--){
        //                 if(matrix[k][j]=='*'){
        //                     break;
        //                 }
        //                 else if(matrix[k][j]=='#'){
        //                     stoneRow = k;
        //                     break;
        //                 }
        //             }

        //             if(stoneRow!=-1){
        //                 matrix[i][j] = '#';
        //                 matrix[stoneRow][j] = '.';
        //             }
        //         }
        //     }
        // }

        // return matrix;

        // Approach 2: better
        int m = box.length;
        int n = box[0].length;
        char matrix[][] = new char[n][m];

        //inverted matrix of box 
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[j][m-i-1] = box[i][j];
            }
        }

        //O(m*n)
        for(int j=0;j<m;j++){
            int spaceBottomRow = n-1;
            for(int i=n-1;i>=0;i--){
                //if any obstacle found
                if(matrix[i][j] == '*'){
                    spaceBottomRow = i-1;
                    continue;
                }

                if(matrix[i][j]=='#'){
                    matrix[i][j] = '.';
                    matrix[spaceBottomRow][j] = '#';
                    spaceBottomRow--;
                }
            }
        }

        return matrix;
    }
}