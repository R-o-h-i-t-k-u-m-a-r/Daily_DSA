package Graph.DisjointSetUnion;

//GFG : Connecting the graph

class DSU {
    int size[];
    int parent[];
    
    DSU(int n){
        size = new int[n+1];
        parent = new int[n+1];
        
        for(int i=0;i<=n;i++){
            size[i] = 1;
            parent[i] = i ;
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
        int ulp_u = findParent(u);//ultimate parent of u
        int ulp_v = findParent(v); //ultimate parent of v
        
        if(ulp_u == ulp_v) return;
        if(size[ulp_u]<size[ulp_v]){
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

    public int Solve(int n, int[][] edge) {
        // Approach : Using DSU
        //T.C : O(4alpha)
        //Intution: the connect the n components the minimum
        //number of edges we need is n-1, if somehow we figureout
        //total number of components in the graph then we could 
        //easily find out minimum number of edges to connect them.
        //so with the help of DSU we can easily figure out the number
        //of components in the graph
        
        //Create disjoint set of n vertices
        
        //if number of edges is less than than n-1 then can not connected
        if(edge.length<n-1){
            return -1;
        }
        
        DSU dsu = new DSU(n);
        
        for(int arr[]: edge){
            int u = arr[0];
            int v = arr[1];
            
            //if both u and v have same parent then no need to add this
            if(dsu.findParent(u)==dsu.findParent(v)){
                continue;
            }
            else{
                dsu.union(u,v);
            }
        }
        
        //now findout how many different component we have
        int component = 0;
        for(int i=0;i<n;i++){
            if(dsu.findParent(i)==i){
                component++;
            }
        }
       return component - 1;
    }
}
