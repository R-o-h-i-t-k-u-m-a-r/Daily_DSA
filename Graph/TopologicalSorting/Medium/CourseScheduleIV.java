package Graph.TopologicalSorting.Medium;

//Leetcode : 1462. Course Schedule IV

import java.util.*;

class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
       //Approach 1 : Graph DFS Traversal
       //create adjacency list
    //    ArrayList<Integer> adjList[] = new ArrayList[numCourses]; O(V+E)
    //    for(int i=0;i<numCourses;i++){
    //     adjList[i] = new ArrayList<>();
    //    }
    //    for(int pre[]: prerequisites){ O(E)
    //         int precourse = pre[0];
    //         int course = pre[1];
    //         adjList[precourse].add(course);
    //     }
    

    //    List<Boolean> result = new ArrayList<>();
       
    //    for(int query[]: queries){O(Q)
    //     int precourse = query[0];
    //     int course =  query[1];
    //     boolean visited[] = new boolean[numCourses];
    //     result.add(DFS(precourse,course,adjList,visited)); O(V+E)
    //    }

    //    return result;

    //Approach 2: Using Topological Sort
    //T.C : O(V^2 + V + E) -> Processing Nodes (each node pushed once in queue) = O(V),
    //Processing edges = O(E), Inserting prerequisites (each node can have ~V prerequisites in worst
    //case): O(V^2)
    //S.C : O(V+E)
    // Create adjacency list and indegree array
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            indegree[v]++;
        }

        // Initialize queue with nodes having indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Map from node to set of prerequisite nodes
        Map<Integer, Set<Integer>> prereqMap = new HashMap<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                // Add current node and its prerequisites to the neighbor's prerequisites
                prereqMap.computeIfAbsent(neighbor, k -> new HashSet<>()).add(node);
                prereqMap.get(neighbor).addAll(prereqMap.getOrDefault(node, new HashSet<>()));

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Process each query
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int src = query[0];
            int dest = query[1];
            result.add(prereqMap.getOrDefault(dest, new HashSet<>()).contains(src));
        }

        return result;
    }

    public boolean DFS(int precourse, int course, ArrayList<Integer> adjList[], boolean visited[]){
        //base case
        if(precourse == course){
            return true;
        }
        visited[precourse] = true;

        for(int neighbour: adjList[precourse]){
            if(visited[neighbour] == false){
                if(DFS(neighbour,course,adjList,visited)==true){
                    return true;
                }
            }
        }

        return false;
    }
}