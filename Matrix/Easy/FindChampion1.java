// Leetcode : 2923. Find Champion I

class Solution {
    public int findChampion(int[][] grid) {
        // Appraoch : Observation
        //grid[i][j]=1 -->  team i is winner
        // to win the tourment winner team will have to defeat all the other teams
        int n = grid.length;
        
        //T.C : O(row*col)
        for(int i=0;i<n;i++){
            boolean champ = true;
            //if team i is winner then all the other teams shuld have value 1
            for(int j=0;j<n;j++){
                if(i!=j && grid[i][j]==0){
                    champ = false;
                    break;
                }
            }
            if(champ) return i;
        }

        return -1;
    }
}