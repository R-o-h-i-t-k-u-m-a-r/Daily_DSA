package Graph.DisjointSetUnion;

//GFG : Number Of Islands

import java.util.*;

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
        if(node == parent[node]){
            return node;
        }
        int ultimate_parent = findParent(parent[node]);
        parent[node] = ultimate_parent; //path compresion
        
        return ultimate_parent;
    }
    
    public void union(int u,int v){
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);
        
        if(ulp_u == ulp_v) return;
        
        if(size[ulp_u]< size[ulp_v]){
            parent[ulp_u] = ulp_v;
            size[ulp_v]+=size[ulp_u];
        }
        else{
            parent[ulp_v] = ulp_u;
            size[ulp_u]+=size[ulp_v];
        }
        return;
    }
}
class Solution {
    
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        // Approach : Using Disjoint Set Union
        int nodes = rows*cols;
        //create grid of rows and cols size
        int grid[][] = new int[rows][cols];
        
        //create disjoint set of nodes
        DSU dsu = new DSU(nodes);
        //create visited array to excape duplicate cell
        boolean visited[][] = new boolean[rows][cols];
        int islandCount = 0;
        int n = operators.length;
        List<Integer> result = new ArrayList<>();
        
        //create direction array for all four direction {row,col}
        int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}}; //up right down left
        
        for(int i=0;i<n;i++){
            int cell_row = operators[i][0];
            int cell_col = operators[i][1];
            
            //if this cell is already visited then no need to count extra isLand
            if(visited[cell_row][cell_col]==true){
                result.add(islandCount);
                continue;
            }
            //increase the islandCount and mark the grid with island
            grid[cell_row][cell_col] = 1;
            islandCount+=1;
            //mark visited to this cell
            visited[cell_row][cell_col] = true;
            
            //explore the current cell all four direction whether any island 
            //is connect or not
            for(int dir[]: directions){
                int new_row = cell_row + dir[0];
                int new_col = cell_col + dir[1];
                
                //check the boundry condition and island is present or not
                if(new_row>=0 && new_row<rows && new_col>=0 && new_col<cols
                && grid[new_row][new_col]==1){
                    //find the node value of parent cell
                    int u = cols*cell_row + cell_col;
                    //find the node value of current neighbour cell
                    int v = cols*new_row + new_col;
                    //check if both u and v has diffent parent node then merge them
                    if(dsu.findParent(u)!=dsu.findParent(v)){
                        //decrease the island count by one
                        islandCount-=1;
                        //merge node  u and v
                        dsu.union(u,v);
                    }
                }
            }
            
            //add the island count to the result after exploring all the 
            //four adjacent cell
            result.add(islandCount);
        }
        
        return result;
    }
    
}
