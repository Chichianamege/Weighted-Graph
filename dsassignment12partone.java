// Name: Chidera Anamege
// Class: CS 3305 W01
// Term: Spring 2024
// Instructor: Carla McManus
// Assignment: 12-Part-1-Prims

import java.util.Arrays;

public class dsassignment12partone {
    private static final int V = 8; // number of vertices

    // Find the vertex with the minimum key value that is not included in MST
    public static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        // Loop through all vertices
        for (int v = 0; v < V; v++) {
            // Check if vertex 'v' is not yet included in MST and its key value is smaller than current minimum
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Print the edges and weights of the Minimum Spanning Tree
    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("Minimum Spanning Tree:");
        System.out.println("Edge \tWeight");
        // Iterate over each vertex (starting from 1 since parent[0] is -1)
        for (int i = 1; i < V; i++) {
            // Convert vertex indices to characters ('A' = 0, 'B' = 1, ..., 'H' = 7)
            char src = (char) (parent[i] + 'A');
            char dest = (char) (i + 'A');
            // Print the edge and its weight using parent and current vertex index
            System.out.println(src + " - " + dest + "\t" + graph[i][parent[i]]);
        }
    }

    // Perform Prim's algorithm to find Minimum Spanning Tree
    public static void primMST(int[][] graph) {
        int[] parent = new int[V]; // Store the parent of each vertex in MST
        int[] key = new int[V];    // Key values used to determine the minimum weight edge
        boolean[] mstSet = new boolean[V]; // Track whether a vertex is included in MST

        // Initialize key values to maximum (infinity)
        Arrays.fill(key, Integer.MAX_VALUE);
        // Initialize all vertices as not included in MST
        Arrays.fill(mstSet, false);

        key[0] = 0; // Start with the first vertex
        parent[0] = -1; // First vertex has no parent

        // Loop to construct the MST with V-1 edges
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet); // Find the vertex with the minimum key value
            mstSet[u] = true; // Include the vertex in MST

            // Update key values of adjacent vertices if they are smaller than the current key
            for (int v = 0; v < V; v++) {
                // Check if there's an edge from 'u' to 'v', 'v' is not in MST, and new key value is smaller
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u; // Update parent of vertex 'v'
                    key[v] = graph[u][v]; // Update key value of vertex 'v'
                }
            }
        }

        printMST(parent, graph); // Print the constructed MST
    }

    public static void main(String[] args) {
        // Adjacency matrix representation of the graph
        int[][] graph = {
                {0, Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, Integer.MAX_VALUE, Integer.MAX_VALUE, 3},
                {4, Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE, 2, 9, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 0, 3, Integer.MAX_VALUE, 7, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 9, Integer.MAX_VALUE, 3, 0, Integer.MAX_VALUE, 2, 7},
                {7, Integer.MAX_VALUE, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 8, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 9, 7, 2, 8, 0, 3},
                {Integer.MAX_VALUE, 3, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, Integer.MAX_VALUE, 3, 0}
        };

        primMST(graph); // Find and print the MST using Prim's algorithm
    }
}
