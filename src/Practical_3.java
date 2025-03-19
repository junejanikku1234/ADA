import java.util.Arrays;

public class Practical_3 {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);  // Sort left part
            quickSort(arr, pivotIndex + 1, high); // Sort right part
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];   // First element as pivot
        int p = low + 1;         // Start partitioning from the next element
        int q = high;            // Last element

        while (p <= q) {
            // Move p right while arr[p] < pivot
            while (p <= q && arr[p] < pivot) {
                p++;
            }
            // Move q left while arr[q] > pivot
            while (p <= q && arr[q] > pivot) {
                q--;
            }

            // Swap arr[p] and arr[q] if p <= q
            if (p <= q) {
                int temp = arr[p];
                arr[p] = arr[q];
                arr[q] = temp;
                p++;
                q--;
            }
        }

        // Swap pivot with arr[q] (final pivot position)
        int temp = arr[low];
        arr[low] = arr[q];
        arr[q] = temp;

        return q;  // Return the partition index
    }

    public static void main(String[] args) {
        int[] arr = {34, 7, 23, 32, 5, 62};
        System.out.println("Original Array: " + Arrays.toString(arr));

        long start1 = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long end1 = System.nanoTime();

        System.out.println("Sorted Array: " + Arrays.toString(arr));
        System.out.println("Elapsed Time: " + (end1 - start1)+" ns");
    }
}
