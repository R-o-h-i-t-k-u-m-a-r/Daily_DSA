package Graph.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//Leetcode : 3243. Shortest Distance After Road Addition Queries I

class Solution {
    
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
       // Appraoch : Using BFS Level order traversal
       // T.C : O(q*(v+E))

       //create adjacency list
       ArrayList<Integer> adj[] = new ArrayList[n];
       for(int i=0;i<n;i++){
           adj[i] = new ArrayList<>();
       }
       for(int i=0;i<n-1;i++){
           adj[i].add(i+1);
       }

       int result[] = new int[queries.length];
       for(int i=0;i<queries.length;i++){
           //add the road
           int to = queries[i][0];
           int from = queries[i][1];
           adj[to].add(from);
           result[i] = bfs(n,adj);
       }
       return result;
   }

   public int bfs(int n, ArrayList<Integer> adj[]){

       //create visited arrray to 0 to n-1
       boolean visited[] = new boolean[n];
       //mark source node visited
       visited[0] = true;
   
       //create queue for bfs traversal
       Queue<Integer> queue = new LinkedList<>();
       //initialize queue with source node
       queue.add(0);

       //mark initial level as 0
       int level = 0;
       

       //Apply BFS Traversal 
       while(!queue.isEmpty()){
           int length = queue.size();

           //process all the current level nodes
           for(int i=0;i<length;i++){
              int node = queue.poll();

              //if we reach to last node it means we have reached to distanation node
              if(node == n-1){
               return level;
              }
              //explore all the neighbour of current node
              for(int neighbour: adj[node]){
               if(visited[neighbour]==false){
                   queue.offer(neighbour);
                   visited[neighbour] = true;
               }
           
              } 
              
           }

           //increase the level by 1 after processing the current level nodes
           level++;
       }

       return -1;
   }

   
}