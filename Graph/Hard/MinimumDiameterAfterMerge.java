//Leetcode : 3203. Find Minimum Diameter After Merging Two Trees

import java.util.*;

class Solution {
    //Method to find adjacency list from given edges
    Map<Integer,List<Integer>> findAdj(int[][] edges){
        Map<Integer,List<Integer>> adjList = new HashMap<>();

        for(int edge[]: edges){
            adjList.computeIfAbsent(edge[0],k->new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1],k->new ArrayList<>()).add(edge[0]);
        }

        return adjList;
    }

    //Method to find diameter of graph from given edge
    public int findDiameter(Map<Integer,List<Integer>> adjList){
        //First BFS to find farthest node from any arbitary node(e.g:0)
        List<Integer> farthestNode = BFS(adjList,0);

        //Second BFS from the farthest node to determine the diameter
        farthestNode = BFS(adjList,farthestNode.get(0));

        return farthestNode.get(1);
    }

    //BFS helper function to find the farthest node and its distance from the source
    public List<Integer> BFS(Map<Integer,List<Integer>> adjList, int sourceNode){
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer,Boolean> visited = new HashMap<>();

        //push source node to queue
        queue.offer(sourceNode);
        visited.put(sourceNode,true);

        int maxDistance = 0, farthestNode = sourceNode;

        //explore neighbours
        while(!queue.isEmpty()){
            int n = queue.size();
            for(int i=0;i<n;i++){
                int currentNode = queue.poll();
                //update the farthestNode 
                farthestNode = currentNode;

                for(int neighbour: adjList.getOrDefault(currentNode,new ArrayList<>())){
                    //explore neighbours
                    if(!visited.getOrDefault(neighbour,false)){
                        visited.put(neighbour,true);
                        queue.offer(neighbour);
                    }
                }
            }

            if(!queue.isEmpty()){
                maxDistance++;
            }
        }

        return Arrays.asList(farthestNode,maxDistance);
    }
    
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        //Approach : Using BFS to find diameter
        Map<Integer,List<Integer>> adj1 = findAdj(edges1);
        Map<Integer,List<Integer>> adj2 = findAdj(edges2);

        //Find diameter of both graph
        int d1 = findDiameter(adj1);
        int d2 = findDiameter(adj2);

        int mergeDiameter = (d1+1)/2 + (d2+1)/2 + 1;

        return Math.max(Math.max(d1,d2),mergeDiameter);
    } 
}