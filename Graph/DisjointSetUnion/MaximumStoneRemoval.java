package Graph.DisjointSetUnion;

import java.util.*;

//GFG : Maximum Stone Removal

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

    int maxRemove(int[][] stones, int n) {
        //Approach : DSU
        //Intution: we can make a group by connecting of all possible nodes
        //which shares same row and col with other nodes. In this group the
        //number of stones will be remove of total number of node in the group
        //minus one. and if count total number of connected components and 
        //substract them to n then we will get total number of stone removed.
        int maxrow = 0;
        int maxcol = 0;
        
        //finding size for row and col
        for(int stone[]: stones){
            maxrow = Math.max(maxrow,stone[0]);
            maxcol = Math.max(maxcol,stone[1]);
        }
        
        //create disjoint set 
        DSU dsu = new DSU(maxrow+maxcol+1);
        //creating a map to store all the possible disjoint set vertices
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for(int stone[]: stones){
            int nodeRow = stone[0];
            int nodeCol = stone[1] + maxrow + 1;
            dsu.union(nodeRow,nodeCol);
            map.put(nodeRow,1);
            map.put(nodeCol,1);
        }
        int count = 0;
        for(Map.Entry<Integer,Integer> set: map.entrySet()){
            int key = set.getKey();
            int value = set.getValue();
            //if this is a single component then 
            if(dsu.findParent(key)==key){
                count++;
            }
        }
        
        return n - count;//total  number of removed stone
    }
};
