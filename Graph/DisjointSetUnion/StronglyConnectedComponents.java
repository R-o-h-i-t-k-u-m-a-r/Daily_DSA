package Graph.DisjointSetUnion;

import java.util.*;

//GFG : Strongly Connected

class Solution {
    public void timeDFS(int node,ArrayList<ArrayList<Integer>> adj,
    Stack<Integer> stack, boolean visited[]){
        //make visited to current node
        visited[node] = true;
        
        //explore all this adjacent nodes
        for(int curr: adj.get(node)){
            if(visited[curr]==false){
                timeDFS(curr,adj,stack,visited);
            }
        }
        //push this node to stack after exploring all the 
        //adjacent nodes
        stack.push(node);
        return;
    }
    
    private void DFS(int node,ArrayList<ArrayList<Integer>> adj,
    boolean visited[]){
        //make visit to current node
        visited[node] = true;
        
        //explore the adjacent nodes
        for(int curr: adj.get(node)){
            if(visited[curr]==false){
                DFS(curr,adj,visited);
            }
        }
        
        return;
    }
    // Function to find number of strongly connected components in the graph.
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        // Approach : Using Kosaraju's Algorithm
        //T.C : O(V+E)
        
        //Step 1: Find finishing time of each node
        int n = adj.size();
        boolean visited[] = new boolean[n];
        //taking a stack to keep nodes according to their finishing time
        //a node is assumed to finish if it visits all its adjacent nodes
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            if(visited[i]==false){
                timeDFS(i,adj,stack,visited);
            }
        }
        //Step 2: Reverse the adjaceny list
        ArrayList<ArrayList<Integer>> reverseAdj = new ArrayList<>();
        for(int i=0;i<n;i++){
            reverseAdj.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            int u = i;
            for(int v: adj.get(i)){
                reverseAdj.get(v).add(u);
            }
        }
        
        //Step 3: do DFS traversal on reversestack with the help of stack
        int count = 0;//for count of strongly connected componets
        visited = new boolean[n];
        while(stack.isEmpty()==false){
            int node = stack.pop();
            if(visited[node]==false){
                count++;
                DFS(node,reverseAdj,visited);
            }
        }
        
        return count;
    }
}
