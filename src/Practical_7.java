public class Practical_7 {

    /**
     * Solves the 0/1 Knapsack problem using dynamic programming.
     *
     * @param capacity The maximum weight the knapsack can hold.
     * @param weights  An array of the weights of the items.
     * @param values   An array of the values of the items.
     * @param n        The number of items.
     * @return The maximum value that can be carried in the knapsack.
     */
    public static int knapsack01(int capacity, int[] weights, int[] values, int n) {
        // Create a table to store the maximum values for different capacities and items.
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the table in a bottom-up manner.
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or no capacity
                } else if (weights[i - 1] <= w) {
                    // If the current item's weight is less than or equal to the current capacity,
                    // we have two choices:
                    // 1. Include the item: Add its value to the maximum value obtained with the remaining capacity.
                    // 2. Exclude the item: Take the maximum value obtained without including this item.
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    // If the current item's weight is greater than the current capacity, we cannot include it.
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // The result is stored in the bottom-right corner of the table.
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        // Example usage:
        int capacity = 50;
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int n = weights.length;

        int maxValue = knapsack01(capacity, weights, values, n);
        System.out.println("Maximum value: " + maxValue); // Output: Maximum value: 220

        // Another test case
        capacity = 10;
        int[] weights2 = {5, 4, 6, 3};
        int[] values2 = {10, 40, 30, 50};
        int n2 = weights2.length;
        int maxValue2 = knapsack01(capacity, weights2, values2, n2);
        System.out.println("Maximum value for capacity 10: " + maxValue2); // Output: 90
    }
}
