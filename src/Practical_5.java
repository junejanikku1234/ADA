import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value, weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class Practical_5 {

    // Function to get the maximum total value in the knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by decreasing value/weight ratio
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                totalValue += item.value * ((double) capacity / item.weight);
                break;  // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };

        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }


//    Time Complexity:
//    Sorting items = O(n log n)
//    Iterating through items = O(n)
//    Total: O(n log n)

//    Space Complexity:
//    O(1) auxiliary (apart from input array)
}
