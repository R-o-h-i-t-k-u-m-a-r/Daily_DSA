package Graph.Bipartite;

import java.util.*;

//Leetcode : 2493. Divide Nodes Into the Maximum Number of Groups

class Solution {
    // Helper function to check if the graph is bipartite
    public boolean DFS(int node, int nodeColor, int color[], HashMap<Integer,ArrayList<Integer>> adjList){
        //mark the current node with nodeColor into color array
        color[node] = nodeColor;

        //explore all the adjacent of current node
        for(int neighbour: adjList.get(node)){
            if(color[neighbour] == -1){
                if(DFS(neighbour, 1-nodeColor, color,adjList) == false){
                    return false;
                }
            }
            else if(color[neighbour] == nodeColor){
                return false;
            }
        }
        return true;
    }
   
    // BFS function to get the maximum level
    public int BFS(int node, HashMap<Integer,ArrayList<Integer>> adjList,int size){
        //create queue for level order traversal
        Queue<Integer> queue = new LinkedList<>();
        //insert the current node into queue
        queue.offer(node);
        //create visited array to keep track of revisiting node
        boolean visited[] = new boolean[size];
        //mark visited to current node
        visited[node] = true;
        //initialize initial level as 0
        int level = 0;

        //start level order traversal with queue
        while(queue.isEmpty() == false){
            int n = queue.size();
            while(n-- >0){
                int curr = queue.poll();
                //explore the adjacent nodes of current node
                for(int neighbour: adjList.get(curr)){
                    if(visited[neighbour] == false){
                        queue.offer(neighbour);
                        visited[neighbour] = true;
                    }
                }
            }
            //increase the level by 1 after exploring all the current level node
            level++;
        }

        return level;
    }
   

    // Helper function to get the maximum group size from each connected component
    public int getMaxLevel(int node, int maxGroupLevel[], HashMap<Integer,ArrayList<Integer>> adjList, boolean visited[]){
        //mark visited of current node
        visited[node] = true;
        int maxLevel = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        while(queue.isEmpty() == false){
            int curr = queue.poll();
            maxLevel = Math.max(maxLevel,maxGroupLevel[curr]);

            //insert all the adjacent node of current node
            for(int neighbour: adjList.get(curr)){
                if(visited[neighbour] == false){
                    queue.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }

        return maxLevel;
    }

    public int magnificentSets(int n, int[][] edges) {
        //Approach : using Bi-partite graph 
        //Intution: we can only split a graph if would be a bi-partite graph
        //to count number of partions of graph we can use level order traversal
        //and to get max number of partions of graph we need to do level order traversal 
        //from each node

        //create adjacency list
        HashMap<Integer,ArrayList<Integer>> adjList = new HashMap<>();
        for(int i=0;i<n;i++){
            adjList.put(i,new ArrayList<>());
        }
        for(int edge[]: edges){
            int u = edge[0] - 1;//subtracting 1 for 0 based graph node
            int v = edge[1] - 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // Bipartite check for each components of graph
        int color[] = new int[n];
        //mark the initial color of all nodes with -1
        Arrays.fill(color,-1);

        for(int i=0;i<n;i++){
            if(color[i]==-1){
                //if graph component is not bi-partite then graph can not be split
                if(DFS(i,1,color,adjList)==false){
                    return -1;
                }
            }
        }
        

        // BFS to find max levels for each node
        int maxGroupLevel[] =  new int[n];
        for(int i=0;i<n;i++){
            maxGroupLevel[i] = BFS(i,adjList, n);
        }
        

        // Sum the max group sizes for each component
        // Graph may contain more than one components , in that case we need to get the sum of 
        //maximum level of each from each component.
       int levelSum = 0;
       boolean visited[] =  new boolean[n];
       for(int i=0;i<n;i++){
        if(visited[i]==false){
            levelSum+=getMaxLevel(i,maxGroupLevel,adjList,visited);
        }
       }

       return levelSum;

        
    }
}