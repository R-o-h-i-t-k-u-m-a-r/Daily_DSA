package Graph.TopologicalSorting.Medium;

import java.util.*;

//Leetcode : 802. Find Eventual Safe States


class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //Approach : Brute Force
        // int n = graph.length;
        // // ArrayList<Integer>[] adjList = new int[n];
        // // for(int i=0;i<n;i++){
        // //     adjList[i] = new ArrayList<>();
        // // }
        // int outDegree[] = new int[n];
        // for(int i=0;i<n;i++){
        //     for(int node: graph[i]){
        //         //adjList[i].add(node);
        //         outDegree[i]++;
        //     }
        // }
        // ArrayList<Integer> result = new ArrayList<>();
        // int safeNode[] = new int[n];
        // Arrays.fill(safeNode,-1);
        // for(int i=0;i<n;i++){
        //     //if current node is terminal node then it will be safe node it self
        //     if(outDegree[i]==0){
        //         result.add(i);
        //         safeNode[i] = 1;
        //         continue;
        //     }
        //     //if it is not terminall node then check every path from this code lead to 
        //     //terminal node or not
        //     boolean flag = true;
        //     for(int node: graph[i]){
        //         boolean visited[] = new boolean[n];
        //         //make root node visited 
        //         visited[i] = true;
        //         if(outDegree[node]==0 || safeNode[node]==1){
        //             continue;
        //         }
        //         if(isSafe(node,graph,outDegree,visited,safeNode)==false){
        //             flag = false;
        //             break;
        //         }
        //     }
        //     //if node is found safe node then add it to result
        //     if(flag==true){
        //         safeNode[i] = 1;
        //         result.add(i);
        //     }
        //     else{
        //         safeNode[i] = 0;
        //     }
        // }

        // return result;

        //Approach 2: Using Topological sort
        //Intution: here we can observe only those are not safe state nodes that leads to cycle path
        // and for cycle detection we can use topological sort 
        //as topological sort works with in-degree wo se will reverse the edges of graph so after 
        //reversing node with in-degree 0 will become our terminal node and by topological sort 
        //algorithm we will only get those nodes which are not part of cyle path.

        int n = graph.length;

        //step 1: reverse the graph edges 
        List<List<Integer>> reverseGraph = new ArrayList<>();

        for(int i=0;i<n;i++){
            reverseGraph.add(new ArrayList<>());
        }
        int inDegree[] = new int[n];
        for(int i=0;i<n;i++){
            for(int neighbour: graph[i]){
                reverseGraph.get(neighbour).add(i);
                inDegree[i]++;
            }
        }

        // Step 2: Initialize the queue with nodes having in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(inDegree[i]==0){
                queue.offer(i);
            }
        }

        // Step 3: Process the nodes in the queue
        boolean safe[] = new boolean[n];
        
        while(queue.isEmpty()==false){
            int node = queue.poll();
            safe[node] = true; //mark the node as safe

            //reduce all the node neighbour in-degree by 1
            for(int neighbour: reverseGraph.get(node)){
                inDegree[neighbour]--;
                if(inDegree[neighbour]==0){
                    queue.offer(neighbour);
                }
            }
        }

        // Step 4: Collect all safe nodes
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(safe[i]==true){
                result.add(i);
            }
        }

        // Step 5: Return the sorted result
        return result;
    }
    public boolean isSafe(int curr, int graph[][], int outDegree[], boolean visited[],int safeNode[]){
        //if current node is a terminal node then return true
        if(outDegree[curr]==0){
            return true;
        }
        //if current node is visited before it means there is loop cycle 
        //and it can not be safe node
        if(visited[curr]==true){
            return false;
        }

        //if it is already been identified unsafe node before, then return false from here
        if(safeNode[curr]==0){
            return false;
        }

        //make current node visited
        visited[curr] = true;
        boolean result = true;//assuming this node will safe node

        //explore all the adjacent node of current node
        for(int adj: graph[curr]){
            //if it is not been idetified safe or un-safe node earlier then only check 
            if(safeNode[adj]==-1){
                safeNode[adj] = isSafe(adj,graph,outDegree,visited,safeNode)==true?1:0;
                //result = result && safeNode[adj];
            }
            if(safeNode[adj]==0){
                result = result && false;
            }
            else{
                result = result && true;
            }
        }

        return result;
    }
}