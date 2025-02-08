package Graph.Targans_Algo;

import java.util.*;

//Leetcode : 1192. Critical Connections in a Network

class Solution {
    int timer = 1;

    public void DFS(int node, int parent_node, ArrayList<Integer> adjList[], int insertTime[],
            int lowestInsertionTime[], boolean visited[], List<List<Integer>> bridge) {
        // mark visited
        visited[node] = true;
        // put the reached time for the current node
        insertTime[node] = timer;
        // put the lowest reached time for the current node
        lowestInsertionTime[node] = timer;
        timer += 1; // increase the timer by one for next vertex

        // explore all the adjacent nodes
        for (int neighbour : adjList[node]) {
            if (neighbour == parent_node) {
                continue;
            }
            if (visited[neighbour] == false) {
                DFS(neighbour, node, adjList, insertTime, lowestInsertionTime, visited, bridge);
                // update the node lowest time with minimum of neighbour lowest time
                lowestInsertionTime[node] = Math.min(lowestInsertionTime[node], lowestInsertionTime[neighbour]);
                // check for bridge connection between current node and neighbour node
                if (lowestInsertionTime[neighbour] > insertTime[node]) {
                    bridge.add(Arrays.asList(node, neighbour));
                }
            } else {
                // if neighbour node is already visited then store the minimum lowest time
                // between
                // neighbour node and current node
                lowestInsertionTime[node] = Math.min(lowestInsertionTime[node], lowestInsertionTime[neighbour]);
            }
        }

        return;
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Approach : Using Tarjan's Algorithm to print all the graph bridges
        // T.C : O(v+2E)
        // create adjacency list
        ArrayList<Integer> adjList[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        // populate the edges between vertecies from the given connections
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            adjList[u].add(v);
            adjList[v].add(u);
        }
        // array for time of insertion of each vertex
        int insertTime[] = new int[n];
        // array for lowest time of insertion of each vertex
        int lowestInsertionTime[] = new int[n];
        // visited for keep track of re-visting vertex
        boolean visited[] = new boolean[n];
        List<List<Integer>> bridge = new ArrayList<>();// to store all the bridges

        DFS(0, -1, adjList, insertTime, lowestInsertionTime, visited, bridge);

        return bridge;
    }
}
