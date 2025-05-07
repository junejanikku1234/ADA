import java.util.Arrays;
import java.util.PriorityQueue;

// Class to represent an item
class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}


// Class to represent a node in the search tree
class Node implements Comparable<Node> {
    int level;
    int profit;
    int weight;
    double bound;

    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(other.bound, this.bound); // Corrected comparison
    }
}

public class Practical_10 {

    /**
     * Calculates the upper bound (promising) for a node.
     *
     * @param u      The node for which to calculate the bound.
     * @param n      The number of items.
     * @param capacity The capacity of the knapsack.
     * @param item2s    The array of items.
     * @return The upper bound for the node.
     */
    static double calculateBound(Node u, int n, int capacity, Item[] item2s) {
        // If the node's weight exceeds the capacity, it's not promising
        if (u.weight >= capacity)
            return 0;

        double bound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        // Greedily add items until the knapsack is full, or we run out of items
        while ((j < n) && (totalWeight + item2s[j].weight <= capacity)) {
            totalWeight += item2s[j].weight;
            bound += item2s[j].value;
            j++;
        }

        // If there's space left, add a fraction of the next item
        if (j < n)
            bound += (double) (capacity - totalWeight) * item2s[j].value / item2s[j].weight;

        return bound;
    }

    /**
     * Solves the 0/1 Knapsack problem using the Branch and Bound approach.
     *
     * @param capacity The capacity of the knapsack.
     * @param item2s    The array of items.
     * @param n        The number of items.
     * @return The maximum profit that can be obtained.
     */
    public static int knapsackBranchAndBound(int capacity, Item[] item2s, int n) {
        // Sort items by their profit-to-weight ratio in descending order
        Arrays.sort(item2s, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        // Use a priority queue to store nodes, ordered by their upper bound (highest bound first)
        PriorityQueue<Node> pq = new PriorityQueue<>(); // No need to pass comparator, Node implements Comparable

        // Create the root node
        Node root = new Node(0, 0, 0, 0);
        root.bound = calculateBound(root, n, capacity, item2s);

        // Initialize the maximum profit
        int maxProfit = 0;
        pq.add(root);

        // Process nodes from the priority queue
        while (!pq.isEmpty()) {
            Node u = pq.poll();

            // If the node's bound is less than or equal to the current maximum profit,
            // there's no point in exploring it further
            if (u.bound <= maxProfit)
                continue;

            // Create the node for the item being included
            int level = u.level + 1;
            Node withItem = new Node(level, u.profit + item2s[u.level].value, u.weight + item2s[u.level].weight, 0);
            withItem.bound = calculateBound(withItem, n, capacity, item2s);

            // If the item can be included, and it leads to a higher profit, update maxProfit
            if (withItem.weight <= capacity && withItem.profit > maxProfit)
                maxProfit = withItem.profit;

            // If the node is still promising, add it to the queue
            if (withItem.bound > maxProfit)
                pq.add(withItem);

            // Create the node for the item being excluded
            Node withoutItem = new Node(level, u.profit, u.weight, 0);
            withoutItem.bound = calculateBound(withoutItem, n, capacity, item2s);

            // If the node is still promising, add it to the queue
            if (withoutItem.bound > maxProfit)
                pq.add(withoutItem);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // Example usage:
        int capacity = 60;
        Item[] item2s = {
                new Item(20, 70),
                new Item(30, 80),
                new Item(40, 90),
                new Item(70, 200)
        };
        int n = item2s.length;

        int maxProfit = knapsackBranchAndBound(capacity, item2s, n);
        System.out.println("Maximum profit: " + maxProfit);
    }
}

