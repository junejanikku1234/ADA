public class Practical_9 {

    /**
     * Solves the N-Queens problem using backtracking.
     *
     * @param n The size of the chessboard (n x n).
     */
    public static void solveNQueens(int n) {
        int[] board = new int[n]; // Array to store the column position of the queen in each row
        if (solveNQueensUtil(board, 0, n)) {
            printSolution(board, n);
        } else {
            System.out.println("No solution exists for " + n + "-Queens problem.");
        }
    }

    /**
     * Recursive utility function to solve the N-Queens problem.
     *
     * @param board The array representing the board.
     * @param row   The current row being considered.
     * @param n     The size of the chessboard.
     * @return true if a solution is found, false otherwise.
     */
    private static boolean solveNQueensUtil(int[] board, int row, int n) {
        // Base case: all queens are placed
        if (row == n) {
            return true;
        }

        // Try placing the queen in each column of the current row
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col; // Place the queen
                // Recursively solve for the next row
                if (solveNQueensUtil(board, row + 1, n)) {
                    return true;
                }
                // If placing the queen here doesn't lead to a solution, backtrack
                board[row] = -1; // Remove the queen (backtrack)
            }
        }

        // If no column in this row leads to a solution, return false
        return false;
    }

    /**
     * Checks if it's safe to place a queen at board[row][col].
     *
     * @param board The array representing the board.
     * @param row   The row to check.
     * @param col   The column to check.
     * @return true if it's safe to place a queen here, false otherwise.
     */
    private static boolean isSafe(int[] board, int row, int col) {
        // Check if there is a queen in the same column
        for (int i = 0; i < row; i++) {
            if (board[i] == col) {
                return false;
            }
        }

        // Check diagonals
        for (int i = 0; i < row; i++) {
            if (Math.abs(board[i] - col) == row - i) {
                return false;
            }
        }

        return true;
    }

    /**
     * Prints the solution (the column position of the queen in each row).
     *
     * @param board The array representing the board.
     * @param n     The size of the chessboard.
     */
    private static void printSolution(int[] board, int n) {
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        int n = 8; // Solve for 8-Queens problem
        solveNQueens(n);

        //Solve for 4-Queens Problem
        int n2 = 4;
        solveNQueens(n2);
    }
}

