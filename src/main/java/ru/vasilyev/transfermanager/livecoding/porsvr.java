package ru.vasilyev.transfermanager.livecoding;
import java.util.Arrays;

 class ReachabilityMatrixCalculator {

    private int[][] adjacencyMatrix;
    private int[][] reachabilityMatrix;

    public ReachabilityMatrixCalculator(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.reachabilityMatrix = calculateReachabilityMatrix();
    }

    private int[][] calculateReachabilityMatrix() {
        int n = adjacencyMatrix.length;
        reachabilityMatrix = new int[n][n];

        // Инициализация матрицы достижимости
        Arrays.fill(reachabilityMatrix[0], 1);

        // Вычисление матрицы достижимости
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    boolean pathExists = adjacencyMatrix[i][k] > 0 && reachabilityMatrix[k][j] == 1;
                    reachabilityMatrix[i][j] = pathExists ? 1 : 0;
                }
            }
        }

        return reachabilityMatrix;
    }

    public void printReachabilityMatrix() {
        System.out.println("Матрица достижимости:");
        for (int[] row : reachabilityMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int [][] adjacencyMatrix = {
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

        };




        ReachabilityMatrixCalculator calculator = new ReachabilityMatrixCalculator(adjacencyMatrix);
        calculator.printReachabilityMatrix();
    }
}
