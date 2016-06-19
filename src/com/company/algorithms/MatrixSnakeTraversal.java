package com.company.algorithms;

/**
 * Created by Yevhen on 19.06.2016.
 */
/*
Обійти матрицю "змійкою" і повернути всі числа в одномірному масиві.
Наприклад:
Для
[[ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]]
повернути
[1, 4, 7, 8, 5, 2, 3, 6, 9]
 */
public class MatrixSnakeTraversal {
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
        int[] result = new int[width * height];

        int k = 0;
        int direct = 1;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[k++] = input[(direct > 0) ? j : height-j-1][i];
            }
            direct *=-1;
        }

        return result;
    }
}
