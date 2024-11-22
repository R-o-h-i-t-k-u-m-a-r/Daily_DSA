package Matrix.Medium;

import java.util.HashMap;

//Leetcode : 1072. Flip Columns For Maximum Number of Equal Rows


class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        // Approach 1: Brute Force
        // int m = matrix.length;
        // int n = matrix[0].length;

        // int maxRow = 0;

        // //T.C : O(m * (n+(m*n)))
        // for(int currRow[] : matrix){ //O(m)
        //     int inverted[] = new int[n];

        //     for(int i=0;i<n;i++){ //O(n)
        //         inverted[i] = currRow[i]^1; //flip
        //     }
            
        //     int count = 0;
        //     for(int row[] : matrix){ //O(m)
        //         if(Arrays.equals(row,currRow) || Arrays.equals(row,inverted)){ //O(n)
        //             count++;
        //         }
        //     }
        //     maxRow = Math.max(maxRow,count);
        // }

        // return maxRow;

        // Approach 2: Using Hashing
        int m = matrix.length;
        int n = matrix[0].length;
        HashMap<String,Integer> map = new HashMap<>();
        
        
        for(int i=0;i<m;i++){
            StringBuilder rowPattern = new StringBuilder();
            int value = matrix[i][0];
            for(int j=0;j<n;j++){
                rowPattern.append(matrix[i][j]==value?'S':'B');
            }
            map.put(rowPattern.toString(),map.getOrDefault(rowPattern.toString(),0)+1);
        }

        int maxRows = 0;
        for(int value : map.values()){
            maxRows = Math.max(maxRows,value);
        }

        return maxRows;
    }
}