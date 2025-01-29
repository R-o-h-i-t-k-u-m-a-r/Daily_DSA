package Graph.DisjointSetUnion;

import java.util.*;

//Leetcode : 684. Redundant Connection


class DSU {
    int parent[];
    int rank[];

    DSU(int n){
        parent = new int[n+1];
        rank  = new int[n+1];
        for(int i= 1;i<n+1;i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }
        public int find(int x){
            if(x!=parent[x]){
                parent[x] = find(parent[x]); //path compresion
            }
            return parent[x];
        }

       public void union(int x, int y) {
        int x_parent = find(x);
        int y_parent = find(y);
        
        if (x_parent == y_parent) return;
        
        if (rank[x_parent] > rank[y_parent]) {
            parent[y_parent] = x_parent;
        } else if (rank[y_parent] > rank[x_parent]) {
            parent[x_parent] = y_parent;
        } else {
            parent[y_parent] = x_parent;
            rank[x_parent]++;
        }
    }
    
}
class Solution {
    public boolean DFS(int source, int dest, HashMap<Integer,ArrayList<Integer>> adjList,boolean visited[]){
        if(source==dest){
            return true;
        }
        visited[source] = true;

        for(int adj: adjList.get(source)){
            if(!visited[adj] && DFS(adj,dest,adjList,visited)){
                return true;
            }
        }
        return false;
    }
    public int[] findRedundantConnection(int[][] edges) {
        //Approach : DFS traversal
        // HashMap<Integer,ArrayList<Integer>> adjList = new HashMap<>();
        // int n = edges.length + 1;
        // for(int edge[]: edges){
        //     int u = edge[0];
        //     int v = edge[1];
        //     boolean visited[] = new boolean[n];

        //     if(adjList.containsKey(u) && adjList.containsKey(v) && DFS(u,v,adjList,visited)){
        //         return new int[]{u,v};
        //     }
        //     adjList.putIfAbsent(u,new ArrayList<>());
        //     adjList.get(u).add(v);
        //     adjList.putIfAbsent(v,new ArrayList<>());
        //     adjList.get(v).add(u);
        // }

        // return new int[]{};

        // Approach : (DSU - Union Find with Path compression)
        int n = edges.length;
        DSU dsu = new DSU(n);

        for(int edge[]: edges){
            int u = edge[0];
            int v = edge[1];

            if(dsu.find(u) == dsu.find(v)){
                return edge;
            }
            dsu.union(u,v);
        }

        return new int[0];
    }
}