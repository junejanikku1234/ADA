import java.util.Scanner;

public class Practical_1 {

    //Factorial function
    public static long factorialRecursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    //Fibonacci function
    public static int fibRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        // Measure time for factorial
        long start1 = System.nanoTime();
        long resultRecursive = factorialRecursive(n);
        long end1 = System.nanoTime();

        System.out.println("\nFactorial (Recursive): " + resultRecursive + " | Time: " + (end1 - start1) + " ns");

        System.out.print("Enter number of terms: ");
        int x = sc.nextInt();

        // Measure time for fibonacci
        long start3 = System.nanoTime();
        System.out.print("\nFibonacci Series (Recursive): ");
        for (int i = 0; i < x; i++) {
            System.out.print(fibRecursive(i) + " ");
        }
        long end3 = System.nanoTime();
        System.out.println("\nTime: " + (end3 - start3) + " ns");

        sc.close();
    }

}
