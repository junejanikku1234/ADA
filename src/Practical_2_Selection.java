public class Practical_2_Selection {
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Move boundary of unsorted subarray one by one
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the minimum element in unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    // Selection Sort Complexity:
    //Best/Average/Worst Case: O(n²)
    //Space Complexity: O(1) (in-place)
}
