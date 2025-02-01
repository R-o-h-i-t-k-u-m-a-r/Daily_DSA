package Graph.DisjointSetUnion;

import java.util.*;

//GFG : Account Merge

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
        return parent[node];
    }
    
    public void union(int u,int v){
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);
        
        if(ulp_u == ulp_v) return;
        
        if(size[ulp_u]<size[ulp_v]){
            parent[ulp_u] = ulp_v;
            size[ulp_v]+=size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u]+=size[ulp_v];
        }
        return;
    }
}

class Solution {
  static List<List<String>> accountsMerge(List<List<String>> accounts) {
    // Approach : Using Disjoint Set Union
    int n = accounts.size();
    //create DSU of n vertices
    DSU dsu = new DSU(n);
    HashMap<String,Integer> map = new HashMap<>();
    
    for(int i=0;i<n;i++){
        for(int j = 1;j<accounts.get(i).size();j++){
            String email = accounts.get(i).get(j);
            if(map.containsKey(email)==false){
                map.put(email,i);
            }
            else{
                dsu.union(map.get(email),i);
            }
        }
    }
    ArrayList<String> mergedEmail[] = new ArrayList[n];
    for(int i=0;i<n;i++){
       mergedEmail[i] = new ArrayList<>();
    }
    
    for(Map.Entry<String,Integer> set : map.entrySet()){
        String email = set.getKey();
        int ultimate_parent = dsu.findParent(map.get(email));
        mergedEmail[ultimate_parent].add(email);
    }
    List<List<String>> ans = new ArrayList<>();
    for(int i=0;i<n;i++){
        if(mergedEmail[i].size()==0) continue;
        
        //sort the emails
        Collections.sort(mergedEmail[i]);
        List<String> temp = new ArrayList<>();
        temp.add(accounts.get(i).get(0));
        
        for(String email: mergedEmail[i]){
            temp.add(email);
        }
        ans.add(temp);
        
    }
    
    return ans;
  }
}
