package src.Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

/**
 * Write an algorithm such that if an element in an M x N matrix is 0,
 * its entire row and column are set to 0.
 */

public class ZeroMatrix{
    public static void setZeros(int[][] matrix){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    setLine(matrix, i, j, -1);
                }
            }
        }
        flip(matrix, -1, 0);
    }

    private static void flip(int[][] matrix, int src, int target){
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == src)
                    matrix[i][j] = target;
    }

    private static void setLine(int[][] matrix, int row, int col, int target){
        for (int i = 0; i < matrix[row].length; i++){
            if (matrix[row][i] != 0) matrix[row][i] = target;
        }
        for (int j = 0; j < matrix.length; j++){
            if (matrix[j][col] != 0) matrix[j][col] = target;
        }
    }

    /** best space complexity solution */
    public static void setZeros2(int[][] matrix){
        boolean rowHasZero = false;
        boolean colHasZero = false;

        // check if ther first row and column has a zero
        for (int i = 0; i < matrix[0].length; i++)
            if (matrix[0][i] == 0){
                rowHasZero = true;
                break;
            }
        for (int i = 0; i < matrix.length; i++){
            if (matrix[i][0] == 0){
                colHasZero = true;
                break;
            }
        }

        // check for zeros in the rest of the array
        for (int i = 1; i < matrix.length; i++){
            for (int j = 1; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Nullify based on first column and row
        for (int i = 1; i < matrix.length; i++){
            if (matrix[i][0] == 0){
                for (int j = 1; j < matrix[i].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++){
            if (matrix[0][j] == 0){
                for (int i = 1; i < matrix.length; i++){
                    matrix[i][j] = 0;
                }
            }
        }

        // Nullify first column and row
        if (rowHasZero){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }
        if (colHasZero){
            for (int i = 0; i < matrix.length; i++){
                matrix[i][0] = 0;
            }
        }

    }

    @Test
    public void test1(){
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] expected = new int[][]{{1,0,1},{0,0,0},{1,0,1}};
        setZeros2(matrix);
        for (int i = 0; i < matrix.length; i++){
            assertArrayEquals(matrix, expected);
        }
    }
    @Test
    public void test2(){
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][] expected = new int[][]{{0,0,0,0},{0,4,5,0},{0,3,1,0}};
        setZeros2(matrix);
        for (int i = 0; i < matrix.length; i++){
            assertArrayEquals(matrix, expected);
        }
    }
}