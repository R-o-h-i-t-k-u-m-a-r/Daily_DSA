package Matrix.Medium;

//Leetcode : 1975. Maximum Matrix Sum 

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        int negCount = 0;
        int row = matrix.length;
        int col = matrix[0].length;

        for(int i=0;i<row;i++){
            for(int j = 0;j<col;j++){
                if(matrix[i][j]<0){
                    negCount++;
                }
                min = Math.min(min,Math.abs(matrix[i][j]));
                sum+=Math.abs(matrix[i][j]);
            }
        }

        if(negCount%2==0){
            return sum;
        }
        return sum-(2*min);
    }
}
