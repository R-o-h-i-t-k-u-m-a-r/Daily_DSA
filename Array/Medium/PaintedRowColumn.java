//Leetcode : 2661. First Completely Painted Row or Column

import java.util.HashMap;

class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        //Approach 1: Brute Force(Simulation)
        //T.C : O(row*col) + O(row*col(row+col))
        //S.C : O(row*col)
        // int row = mat.length;
        // int col = mat[0].length;
        // HashMap<Integer,int[]> map = new HashMap<>();//to store index of each mat element
        // for(int i=0;i<row;i++){
        //     for(int j=0;j<col;j++){
        //         map.put(mat[i][j],new int[]{i,j});
        //     }
        // }

        // //now iterate arr to get the minimum index 
        // for(int i=0;i<arr.length;i++){
        //     int elementRow = map.get(arr[i])[0];
        //     int elementCol = map.get(arr[i])[1];
        //     mat[elementRow][elementCol] = -1;//paint the mat current element cell

        //     //check element of having this row and column is painted completly or not
        //     if(isPainted(mat,elementRow,elementCol,row,col)==true){
        //         return i;
        //     }
        // }

        // return 0;

        //Approach 2: Optimize over brute force
        //T.C : O(row*col) + O(row*col)
        //S.C : O(row*col) +O(row) + O(col)
        // int row = mat.length;
        // int col = mat[0].length;
        // HashMap<Integer,int[]> map = new HashMap<>();// to store each mat element index
        // for(int i=0;i<row;i++){
        //     for(int j=0;j<col;j++){
        //         map.put(mat[i][j],new int[]{i,j});
        //     }
        // }
        // int rowCount[] = new int[row]; //to count how many element painted in each row
        // int colCount[] = new int[col]; //to count how many element painted in each col

        // //iterate to array arr to get the minimum index 
        // int size = row*col;
        // for(int i=0;i<size;i++){
        //     int elementRow = map.get(arr[i])[0];
        //     int elementCol = map.get(arr[i])[1];
        //     rowCount[elementRow]++; //mark painted for the current element row
        //     colCount[elementCol]++; //mark painted for the current element col
        //     //if current element row or column has painted all its element the return index 
        //     if(rowCount[elementRow]==col || colCount[elementCol]==row){
        //         return i;
        //     }
        // }
        //return 0;

        //Approach 3: Optimization of approach 2 with the elemination of extra space for row and col
        //T.C : O(row*col)+O(row*col)+O(row*col)
        //S.C : O(row*col)
        int row = mat.length;
        int col = mat[0].length;
        HashMap<Integer,Integer> map = new HashMap<>();//to store index of each arr eleemnt
        int size = row*col;
        for(int i=0;i<size;i++){
            map.put(arr[i],i);
        }
        int minIndex = Integer.MAX_VALUE;
        //iterate each row of mat and store the index at which each row painted completely
        for(int i=0;i<row;i++){
            int max = 0;
            for(int j=0;j<col;j++){
                max = Math.max(max,map.get(mat[i][j]));//storing the maximum index of arr to paint fullrow
            }
            minIndex = Math.min(minIndex,max);//store the min index of completed painted row
        }
        //iterate each col of mat and store the index at which each col painted completlly
        for(int j=0;j<col;j++){
            int max = 0;
            for(int i=0;i<row;i++){
                max = Math.max(max,map.get(mat[i][j]));
            }
            minIndex = Math.min(minIndex,max);
        }

        return minIndex;

    }

    public boolean isPainted(int mat[][],int elementRow, int elementCol, int row,int col){
        //for row 
        boolean flag = true;
        for(int j = 0;j<col;j++){
            if(mat[elementRow][j]!=-1){
                flag = false;
                break;
            }
        }
        //if current row is found completly painted then return true
        if(flag == true){
            return true;
        }
        //if not fount then check for current col
        flag = true; //resting to true by default
        for(int i = 0;i<row;i++){
            if(mat[i][elementCol]!=-1){
                flag = false;
                break;
            }
        }
        return flag;
    }
}