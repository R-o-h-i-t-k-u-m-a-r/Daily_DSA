package Graph.Easy;

//Leetcode : 2924. Find Champion II

class Solution {
    public int findChampion(int n, int[][] edges) {
        // Appraoch : Observation of in-degree
        int nodeIndegree[] = new int[n];

        //find in-degree of each node
        for(int edge[]: edges){
            nodeIndegree[edge[1]]++;
        }

        //Traverse the in-degree array if it has only node having 0 in-degree then that would be 
        //champion otherwise there will no champion
        int championNode = -1;
        for(int i=0;i<n;i++){
            if(nodeIndegree[i]==0){
                //if there is already champion 
                if(championNode!=-1){
                    return -1;
                }
                championNode = i;
            }
        }

        return championNode;
    }
}