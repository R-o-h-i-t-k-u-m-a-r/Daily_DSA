package Graph.FloydWarshallAlgo;

import java.util.Arrays;

//GFG : City With the Smallest Number of Neighbors at a Threshold Distance

class Solution {
    int findCity(int n, int m, int[][] edges,int distanceThreshold)
    {
        //Approach : Using Flyod Warshall Algorithm
        //Intution: Flyod warshall helps us to find minimum distance of 
        //node from each node
        
        //create distance matrix
        int dist[][] = new int[n][n];
        //fill dist array with large value
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        
        //fill all the distance from edges into matrix
        for(int edge[]: edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            dist[u][v] = wt;
            dist[v][u] = wt;
        }
        
        //distance from self node to self node will be 0
        for(int i=0;i<n;i++){
            dist[i][i] = 0;
        }
        
        //implement floyd warshell algo O(n^3)
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]==Integer.MAX_VALUE || dist[k][j]==Integer.MAX_VALUE){
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                }
            }
        }
        
        int countCity = n;
        int city = -1;
        
        //iterate the distance matrix to find the smallest number of cities
        for(int i=0;i<n;i++){
            int count = 0;
            for(int j=0;j<n;j++){
                if(dist[i][j]<=distanceThreshold){
                    count++;
                }
            }
            if(countCity>=count){
                countCity = count;
                city = i;
            }
        }
        
        return city;
    }
}