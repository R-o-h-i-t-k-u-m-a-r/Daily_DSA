package Graph.Bipartite;

import java.util.*;

//Leetcode : 785. Is Graph Bipartite?


class Solution {
    public boolean DFS(int node, int nodeColor, int color[], int graph[][]){
        //mark the current node with nodecolor 
        color[node] = nodeColor;

        //explore the adjacent neighbour of current node
        for(int neighbour: graph[node]){
            if(color[neighbour]==-1){
                if(DFS(neighbour, 1-nodeColor, color, graph)==false){
                    return false;
                }
            }
            else if(color[neighbour] == nodeColor){
                return false;
            }
        }

        return true;
    }

    public boolean BFS(int node, int nodeColor, int color[], int graph[][]){
        //mark the node with current color
        color[node] = nodeColor;
        //create queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        //insert the current node into queue
        queue.offer(node);

        //start bfs traversal into queue
        while(queue.isEmpty()==false){
            int curr = queue.poll();

            //explore all the neighbours of curr node
            for(int neighbour: graph[curr]){
                //if neighbour node is not colored yet then color it with opposite color of 
                //current node color and insert it into queue
                if(color[neighbour] == -1){
                    color[neighbour] = 1 - color[curr];
                    queue.offer(neighbour);
                }
                else if(color[neighbour] == color[curr]){
                    //if neigbhour node is colored with same color as current node color 
                    //then this is not satisfying bipartite graph condition
                    return false;
                }
            }
        }

        return true;
    }
    public boolean isBipartite(int[][] graph) {
        //Approach 1: DFS/BFS traversal using coloring technique
        //T.C : O(V+E)
        int n = graph.length;
        int color[] = new int[n];
        //initially mark all the node color with -1
        Arrays.fill(color,-1);

        //Do DFS traversal for each component of graph
        for(int i=0;i<n;i++){
            if(color[i] == -1){
                //if this component of graph is found not bi-partite then no need to check further
                //components of graph
                // if(DFS(i,1,color,graph)==false){
                //     return false;
                // }
                if(BFS(i,1,color,graph)==false){
                    return false;
                }
            }
        }
        return true;
    }
}