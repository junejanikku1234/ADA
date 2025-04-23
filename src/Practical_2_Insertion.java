public class Practical_2_Insertion {
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        insertionSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    //Insertion Sort Complexity:
    //Best Case (Already sorted): O(n)
    //Average/Worst Case: O(n²)
    //Space Complexity: O(1) (in-place)
}
