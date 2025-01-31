package Graph.Bipartite;

//GFG : Bipartite Graph

import java.util.*;

class Solution {
    public boolean BFS(ArrayList<ArrayList<Integer>> adj){
        int n = adj.size();
        int color[] = new int[n];
        //initially mark all the node color with -1
        Arrays.fill(color,-1);
        
        //creating queue for BFS traversal of adj list
        Queue<Integer> queue = new LinkedList<>();
        //insert the first node into queue
        queue.offer(0);
        //mark node 0 with color 1
        color[0] = 1;
        
        //start bfs traversal
        while(queue.isEmpty()==false){
            int curr = queue.poll();
            
            //explore all the adjacent neighbour of curr node
            for(int neighbour: adj.get(curr)){
                //if this neighbour node is not colured yet then color this 
                //node with opposite of currr node color and push into queue
                if(color[neighbour]==-1){
                    color[neighbour] = 1 - color[curr];//this will flip 0 to 1 and 1 to 0
                    queue.offer(neighbour);
                }
                else{
                    //if this neighbour node is already colored then if curr
                    //node color is same with neighbour node then there would
                    //be conflict with color and will not satisfy bi-partitie condition
                    if(color[neighbour] == color[curr]){
                        return false;
                    }
                }
            }
        }
        
        //after color all the nodes if there is no confict 
        //then this graph will be bi-partitate graph
        return true;
    }
    
    public boolean DFS(int node,int nodeColor,  int color[], ArrayList<ArrayList<Integer>> adj){
        //mark the curr node with color 
        color[node] = nodeColor;
        
        //explore all the adjacent node of curr node
        for(int neighbour: adj.get(node)){
            if(color[neighbour]==-1){
                DFS(neighbour,1-nodeColor,color,adj);
            }
            else if(color[neighbour] == nodeColor){
                return false;
            }
        }
        
        return true;
    }
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        //Approach 1: using BFS traversal 
        //T.C : O(V+E)
        //return BFS(adj);
        
        //Approach 2: using DFS traversal
        int n = adj.size();
        int color[] = new int[n];
        Arrays.fill(color,-1);
        
        //return DFS(0,1,color,adj);
        
        //for multiple graph components
        for(int i=0;i<n;i++){
            if(color[i]==-1){
                if(DFS(i,1,color,adj)==false){
                    return false;
                }
            }
        }
        
        return true;
    }
}
