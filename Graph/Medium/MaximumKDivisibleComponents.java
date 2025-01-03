package Graph.Medium;

import java.util.*;

//Leetcode : 2872. Maximum Number of K-Divisible Components


class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        //Approach : DFS Traveral
        //T.C: O(n)

        // adjacency list from edges
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }
        //visited array to keep track of re-visiting node
        //boolean visited[] = new boolean[n];
        int result[] = new int[1];
        //DFS(0,adjList,values,visited,result,k);
        int parentNode = -1; //instead of creating visited array we can also use parentNode variable
        DFS(0,adjList,values,parentNode,result,k);

        return result[0];
    }

    public long DFS(int node,List<Integer>[] adjList, int values[],int parentNode,int result[], int k){
        //make current node visited
        //visited[node] = true;

        //explore the neighbours of current node if it not leaf node
        long sum = 0;
        for(int curr: adjList[node]){
            // if(visited[curr]==false){
            //     sum+=DFS(curr,adjList,values,visited,result,k);
            // }
            if(parentNode!=curr){
                sum+=DFS(curr,adjList,values,node,result,k);
            }
        }

        sum+=values[node];
        if(sum%k==0){
            result[0]++;
            return 0;
        }
        return sum;
    }

    

    
}