package Graph.KruskalsAlgo;

import java.util.*;

//GFG : Minimum Spanning Tree


class DSU {
    int size[];
    int parent[];
    
    DSU(int n){
        size = new int[n+1];
        parent = new int[n+1];
        
        for(int i=0;i<=n;i++){
            size[i] = 1;
            parent[i] = i;
        }
    }
    
    public int findParent(int node){
        if(parent[node] == node){
            return node;
        }
        int ultimate_parent = findParent(parent[node]);
        parent[node] = ultimate_parent; //path compresion
        return parent[node];
    }
    
    public void join(int u,int v){
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);
        
        if(ulp_u == ulp_v) return;
        
        if(size[ulp_u]<size[ulp_v]){
            parent[ulp_u] = ulp_v;
            size[ulp_v]+=size[ulp_u];
        }
        else{
            parent[ulp_v] =ulp_u;
            size[ulp_u]+=size[ulp_v];
        }
        return;
    }
}

class Solution {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        // Approach : Using Prim's Algorithm
        //create min-heap
        // PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->x[0]-y[0]);
        // //create visited array
        // boolean visited[] = new boolean[V];
        
        // //add node 0 with 0 weight into pq
        // //{wt,node}
        // pq.offer(new int[]{0,0});
        // int sum = 0;
        
        // while(pq.isEmpty()==false){
        //     int arr[] = pq.poll();
        //     int wt = arr[0];
        //     int node =  arr[1];
            
        //     //if this node is processed before then do not explore this node
        //     if(visited[node]==true){
        //         continue;
        //     }
        //     //otherwise make this node visited
        //     visited[node] = true;
        //     //include this node weight into sum
        //     sum+=wt;
            
        //     //explore the node adjacent cell
            
        //     for(int adjNode[]: adj.get(node)){
        //         int curr = adjNode[0];
        //         int currWt = adjNode[1];
                
        //         if(visited[curr]==false){
        //             pq.offer(new int[]{currWt,curr});
        //         }
        //     }
        // }
        
        // return sum;
        
        //Approach : Using Kruskal's Algorithm
        ArrayList<int[]> adjList = new ArrayList<>();
        for(int i=0;i<V;i++){
            for(int edge[]: adj.get(i)){
                int v = edge[0];
                int wt = edge[1];
                int u = i;
                adjList.add(new int[]{wt,u,v});
            }
        }
        //sort the adjList with respect to weight
        Collections.sort(adjList,(a,b)->a[0]-b[0]);
        
        //Create Disjoint set of V node
        DSU dsu = new DSU(V);
        int result = 0;
        for(int edge[]: adjList){
            int wt = edge[0];
            int u = edge[1];
            int v = edge[2];
            if(dsu.findParent(u)!=dsu.findParent(v)){
                dsu.join(u,v);
                result+=wt;
            }
        }
        
        return result;
        
    }
}