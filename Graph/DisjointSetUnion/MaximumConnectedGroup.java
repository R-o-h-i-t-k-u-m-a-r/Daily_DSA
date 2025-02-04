package Graph.DisjointSetUnion;

import java.util.*;

//GFG : Maximum Connected Group

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
    
    public int getSize(int node){
        return size[node];
    }
    
    public int findParent(int node){
        if(node == parent[node]){
            return node;
        }
        int ultimate_parent = findParent(parent[node]);
        parent[node] = ultimate_parent; //path compresion
        return ultimate_parent;
    }
    
    public void union(int  u,int v){
        int ulp_u = findParent(u);
        int  ulp_v = findParent(v);
        
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
    //{row,col}--> top right bottom left
    int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    
    public int DFS(int i,int j,int id, int n,int grid[][]){
        //base case
        if(i<0 || i>=n || j<0 || j>=n || grid[i][j]!=1){
            return 0;
        }
        //mark the current cell with id
        grid[i][j] = id;
        //mark current cell value of 1
        int sum = 1;
        
        //explore the all four adjacent cell of current cell
        for(int dir[]: directions){
            int new_i = i + dir[0];
            int new_j = j+ dir[1];
            sum+=DFS(new_i,new_j,id,n,grid);
        }
        return sum;
    }
    public int MaxConnection(int grid[][]) {
        // Approach 1: using DFS traversal
        // T.C : O(n^2)
        // int n = grid.length;
        // HashMap<Integer,Integer> map = new HashMap<>();
        // int result = 0;
        // boolean hasZero = false;
        
        // //find largest connected group without changing any 0 to 1
        // int id = 2; // to assign each connected group same id
        // //O(n^2)
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(grid[i][j]==1){
        //             int group = DFS(i,j,id,n,grid);//O(unvisted 1's)
        //             //store this id of group in map
        //             map.put(id,group);
        //             //increase the id by 1 for next group
        //             id+=1;
        //             //get the maximum between result and group value
        //             result = Math.max(result,group);
        //         }
        //         else{
        //             hasZero = true;
        //         }
        //     }
        // }
        
        // // If there is no `0`, return the largest found component
        // if(!hasZero) return result;
        
        // //now check for largest value by changing each 0 to 1
        // //O(n^2)
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(grid[i][j]==0){
        //             int sum = 1; //value for current cell after updating 0 to 1
        //             //explore all for directions
        //             HashSet<Integer> set = new HashSet<>();
        //             for(int dir[]: directions){
        //                 int new_i = i + dir[0];
        //                 int new_j = j + dir[1];
                        
        //                 //check boundry cases
        //                 if(new_i>=0 && new_i<n && new_j>=0 && new_j<n
        //                 && grid[new_i][new_j]!=0){
        //                     set.add(grid[new_i][new_j]);
        //                 }
        //             }
        //             //get all the unique group id value from the map using set
        //             for(int num: set){
        //                 sum+=map.get(num);
        //             }
        //             result = Math.max(result,sum);
        //         }
        //     }
        // }
        
        // return result;
        
        //Approach 2: using Disjoint Set Union
        int n = grid.length;
        //create disjoin node of n*n size
        DSU dsu = new DSU(n*n);
        
        int result = 0;
        boolean isZero = false;
        
        //group all the possible sets of grid without changing any 0 to 1
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    int curr_node = (n*i)+j;
                    int curr_parent = dsu.findParent(curr_node);
                    
                    //explore all the four adjacent cell
                    for(int dir[]: directions){
                        int new_i = i+dir[0];
                        int new_j = j+dir[1];
                        //check boundry conditions
                        if(new_i>=0 && new_i<n && new_j>=0 && new_j<n
                        && grid[new_i][new_j]==1){
                            int new_node = (n*new_i) + new_j;
                            //if both node has different ultimate parent
                            //then union these two node
                            if(curr_parent!=dsu.findParent(new_node)){
                                dsu.union(curr_node,new_node);
                            }
                        }
                    }
                    
                    //parent might be change after union of adjacent node
                    // to current node
                    curr_parent = dsu.findParent(curr_node);
                    //get the size of curr node parent
                    result = Math.max(result,dsu.getSize(curr_parent));
                }
                else{
                    isZero = true;
                }
            }
        }
        
        // If there is no `0`, return the largest found component
        if(!isZero) return result;
        
        //now check of maximum connected group after changing 0 to 1 
        //each each time
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    int sum = 1;
                    HashSet<Integer> set = new HashSet<>();
                    
                    for(int dir[]: directions){
                        int new_i = i + dir[0];
                        int new_j = j + dir[1];
                        
                        //boundry conditions
                        if(new_i>=0 && new_i<n && new_j>=0 && new_j<n
                        && grid[new_i][new_j]==1){
                            int new_node = (n*new_i)+new_j;
                            set.add(dsu.findParent(new_node));
                        }
                    }
                    
                    for(int parent: set){
                        sum+=dsu.getSize(parent);
                    }
                    
                    result = Math.max(result,sum);
                }
            }
        }
        
        return result;
        
    }
}