package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Обійти матрицю по спіралі і записати всі числа в одмірний масив.
Для матриці
[[1,   2,  3,  4],
 [5,   6,  7,  8]
 [9,  10, 11, 12]
 [13, 14, 15, 16]]
вивести [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]
 */
public class MatrixTraversal {
    private static int getMatrixWidth(int[][] matrix) throws IllegalArgumentException {
        int width = -1;

        for (int[] line: matrix) {
            if (width == -1) {
                width = line.length;
            }
            else {
                if (line.length != width) {
                    throw new IllegalArgumentException(line.length + " vs " + width);
                }
            }
        }

        return width;
    }

    private static int getMatrixHeight(int[][] matrix)  throws IllegalArgumentException  {
        return matrix.length;
    }

    public int[] print(int[][] input) {
        int width = getMatrixWidth(input);
        int height = getMatrixHeight(input);

        int resultSize = width * height;
        int[] result = new int[resultSize];
        int resultArrayIndex = 0;

        int startRow = 0;
        int startColumn = 0;
        while(startRow < width && startColumn < height && resultArrayIndex < resultSize) {
            // Row from start to finish
            for (int i = startColumn; i < width && resultArrayIndex < resultSize; i++) {
                result[resultArrayIndex++] = input[startRow][i];
            }
            // Column from start to finish
            for (int i = startRow + 1; i < height && resultArrayIndex < resultSize; i++) {
                result[resultArrayIndex++] = input[i][width-1];
            }
            // Row from finish to start
            for (int i = width - 2; i >= startColumn && resultArrayIndex < resultSize; i--) {
                result[resultArrayIndex++] = input[height-1][i];
            }
            // Column from finish to start
            for (int i = height - 2; i > startRow && resultArrayIndex < resultSize; i--) {
                result[resultArrayIndex++] = input[i][startColumn];
            }
            // To next loop
            // Row should be shorter
            width--;
            // Column should be shorter
            height--;
            // Start positions
            startRow++;
            startColumn++;
        }

        return result;
    }
}
