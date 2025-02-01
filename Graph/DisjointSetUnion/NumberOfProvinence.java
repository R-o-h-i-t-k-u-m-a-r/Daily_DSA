package Graph.DisjointSetUnion;

import java.util.ArrayList;

//GFG : Number of Provinces


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
    
    public void union(int u,int v){
        int ulp_u = findParent(u); //ultimate parent of u
        int ulp_v = findParent(v); //ultimate parent of v
        
        if(ulp_u == ulp_v){
            return;
        }
        if(size[ulp_u]<size[ulp_v]){
            parent[ulp_u] = ulp_v;
            size[ulp_v]+=size[ulp_u];
        }
        else{
            parent[ulp_v] = ulp_u;
            size[ulp_u]+=size[ulp_v];
        }
    }
}

class Solution {
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        //Approach 1: Using Disjoint Set Union(DSU)
        //T.C : O(4alpha)
        //Intution: we will create disjoint set of V nodes and will union 
        //node to their adjacency node, this will ensure the parent of all
        //the connected adjacency nodes it means the number of parent node
        //in dsu will be work as number of provinces.
        
        //create DSU of V node
        DSU dsu = new DSU(V);
        int row = adj.size();
        int col = adj.get(0).size();
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i!=j && adj.get(i).get(j)==1){
                    dsu.union(i,j);
                }
            }
        }
        
        int count = 0;
        for(int i=0;i<V;i++){
            if(dsu.findParent(i)==i){
                count++;
            }
        }
        
        return count;
    }
};