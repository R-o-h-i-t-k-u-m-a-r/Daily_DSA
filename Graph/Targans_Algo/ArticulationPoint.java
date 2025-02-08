package Graph.Targans_Algo;

import java.util.ArrayList;

//GFG : Articulation Point

class Solution
{
    int timer = 1;
    public void DFS(int node,int parent, ArrayList<ArrayList<Integer>> adj,
    int timeOfInsertion[], int lowestTimeOfInsertion[], boolean visited[], int mark[]){
        //mark visited
        visited[node] = true;
        //assign the insertion and lowest reaching time of node
        timeOfInsertion[node]=timer;
        lowestTimeOfInsertion[node] = timer;
        //increase timer by one for next node
        timer+=1;
        int child = 0;
        
        //explore the adjacent node of current node
        for(int neighbour: adj.get(node)){
            
            if(neighbour==parent) continue;
            if(visited[neighbour]==false){
                DFS(neighbour,node,adj,timeOfInsertion,lowestTimeOfInsertion,
                visited,mark);
                //update the lowest reachable time of current node
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node],
                lowestTimeOfInsertion[neighbour]);
                
                //check for articulation point
                if(lowestTimeOfInsertion[neighbour]>=timeOfInsertion[node] && 
                parent!=-1){
                    mark[node] = 1;
                }
                child+=1;
            }
            else{
                //if neighbour is already visited then update the lowest time
                //of current node with neighbour time of insertion time
                lowestTimeOfInsertion[node] = Math.min(lowestTimeOfInsertion[node],
                timeOfInsertion[neighbour]);
            }
        }
        
        //check aticulation point for root node having parent -1
        if(child>1 && parent == -1){
            mark[node] = 1;
        }
    }
    //Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> articulationPoints(int V,ArrayList<ArrayList<Integer>> adj)
    {
        //Approach : Targan's Algorithm
        //T.C : O(V+2E)
        //S.C : O(3V)
        
        boolean visited[] = new boolean[V];
        int timeOfInsertion[] = new int[V];
        int lowestTimeOfInsertion[] = new int[V];
        int mark[] = new int[V];//to mark the articulation point
        
        for(int i=0;i<V;i++){
            if(visited[i]==false){
                DFS(i,-1,adj,timeOfInsertion,lowestTimeOfInsertion,visited,mark);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<V;i++){
            if(mark[i]==1){
                result.add(i);
            }
        }
        if(result.size()==0){
            result.add(-1);
            return result;
        }
        
        return result;
    }
}
