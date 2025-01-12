package Graph.Medium;

import java.util.*;

//Leetcode : 3419. Minimize the Maximum Edge Weight of Graph

class Solution {
    private static class Pair{
        int node;
        int weight;
        Pair(int node,int weight){
            this.node = node;
            this.weight = weight;
        }
    }

    public void BFS(HashMap<Integer,ArrayList<Pair>> adjList,int weight,boolean visited[]){
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(Pair p: adjList.getOrDefault(node,new ArrayList<>())){
                int neighbour = p.node;
                int w = p.weight;
                if(w<=weight && visited[neighbour]==false){
                    visited[neighbour] = true;
                    queue.offer(neighbour);
                }
            }
        }
    }

    public void DFS(int node,HashMap<Integer,ArrayList<Pair>> adjList,int weight,boolean visited[]){
        //mark visited current node
        visited[node] = true;

        //explore all the neighbour nodes of current node
        for(Pair p: adjList.getOrDefault(node,new ArrayList<>())){
            int curr = p.node;
            int w = p.weight;

            //explore if the weight is less than the node weight and not visited earlier
            if(w<=weight && visited[curr]==false){
                DFS(curr,adjList,weight,visited);
            }
        }
    }

    public boolean canAllReachZero(HashMap<Integer,ArrayList<Pair>> adjList,int weight,int n){
        
        boolean visited[] =  new boolean[n];
        //Using BFS traversal
        //BFS(adjList,weight,visited);
        //Using DFS Traversal
        DFS(0,adjList,weight,visited);

        for(int i=0;i<n;i++){
            if(visited[i]==false){
                return false;
            }
        }
        return true;
    }
    public int minMaxWeight(int n, int[][] edges, int threshold) {
       //Approach : Using Binary Search on Answer and Graph Traversal 
       //to find wheater all node is  reachable to node 0 we need to reverse all the edges to optimize
       //the complexity
       HashMap<Integer,ArrayList<Pair>> adjList = new HashMap<>();
       int maxWeight = Integer.MIN_VALUE;

       //create adjaceny list with reverse edge direction
       for(int edge[]: edges){
        int u = edge[0];
        int v = edge[1];
        int w = edge[2];

        adjList.computeIfAbsent(v,k->new ArrayList<>()).add(new Pair(u,w)); //reverse edge direction
        maxWeight = Math.max(maxWeight,w);
       } 

       //Now apply binary search on answer(weight)
       int l = 0;
       int r = maxWeight;
       int result = -1;
       while(l<=r){
        int mid = l+(r-l)/2;

        if(canAllReachZero(adjList,mid,n)==true){
            result = mid;
            r = mid -1;
        }
        else{
            l = mid+1;
        }
       }

       return result;
    }
}
