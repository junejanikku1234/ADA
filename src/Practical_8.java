import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Practical_8 {

    private static final int INF = Integer.MAX_VALUE;

    /**
     * Solves the Traveling Salesman Problem using dynamic programming.
     *
     * @param graph The cost matrix representing the distances between cities.
     * graph[i][j] is the distance from city i to city j.
     * @param start The starting city (index).
     * @return The minimum cost of a tour.
     */
    public static int tsp(int[][] graph, int start) {
        int n = graph.length;
        // memoization table
        Map<Integer, Integer> memo = new HashMap<>();

        // All cities except the starting city
        int allCities = (1 << n) - 1;
        allCities &= ~(1 << start);

        return tspRecursive(graph, start, allCities, memo);
    }

    /**
     * Recursive helper function for TSP.
     *
     * @param graph     The cost matrix.
     * @param current   The current city.
     * @param remaining Cities yet to be visited, represented as a bitmask.
     * @param memo      Memoization table.
     * @return The minimum cost to complete a tour from the current city.
     */
    private static int tspRecursive(int[][] graph, int current, int remaining, Map<Integer, Integer> memo) {
        // Base case: all cities visited
        if (remaining == 0) {
            return graph[current][0]; // Return to the starting city
        }

        // Check if the result is already memoized
        int memoKey = (current << 16) | remaining; // Create a unique key
        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }

        int minCost = INF;
        // Try visiting each remaining city
        for (int nextCity = 0; nextCity < graph.length; nextCity++) {
            if ((remaining & (1 << nextCity)) != 0) { // If nextCity is in remaining
                int newRemaining = remaining & ~(1 << nextCity); // Remove nextCity
                int cost = graph[current][nextCity] + tspRecursive(graph, nextCity, newRemaining, memo);
                minCost = Math.min(minCost, cost);
            }
        }

        memo.put(memoKey, minCost); // Memoize the result
        return minCost;
    }

    public static void main(String[] args) {
        // Example usage:
        int[][] graph = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int startCity = 0; // Starting city is 0

        int minCost = tsp(graph, startCity);
        System.out.println("Minimum cost of the tour: " + minCost); // Output: 80

        // Example with different graph
        int[][] graph2 = {
                {0, 2, 3, 20, 15},
                {2, 0, 15, 2, 23},
                {3, 15, 0, 19, 11},
                {20, 2, 19, 0, 4},
                {15, 23, 11, 4, 0}
        };
        int startCity2 = 1;
        int minCost2 = tsp(graph2, startCity2);
        System.out.println("Minimum cost of the tour: " + minCost2);
    }
}

