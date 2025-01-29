package Graph.PrimsAlgo;

import java.util.*;

//GFG : Minimum Spanning Tree

class Solution {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        // Approach : Using Prim's Algorithm
        //create min-heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->x[0]-y[0]);
        //create visited array
        boolean visited[] = new boolean[V];
        
        //add node 0 with 0 weight into pq
        //{wt,node}
        pq.offer(new int[]{0,0});
        int sum = 0;
        
        while(pq.isEmpty()==false){
            int arr[] = pq.poll();
            int wt = arr[0];
            int node =  arr[1];
            
            //if this node is processed before then do not explore this node
            if(visited[node]==true){
                continue;
            }
            //otherwise make this node visited
            visited[node] = true;
            //include this node weight into sum
            sum+=wt;
            
            //explore the node adjacent cell
            
            for(int adjNode[]: adj.get(node)){
                int curr = adjNode[0];
                int currWt = adjNode[1];
                
                if(visited[curr]==false){
                    pq.offer(new int[]{currWt,curr});
                }
            }
        }
        
        return sum;
        
    }
}
