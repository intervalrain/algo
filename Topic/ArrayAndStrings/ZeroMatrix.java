package Topic.ArrayAndStrings;

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

    @Test
    public void test1(){
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] expected = new int[][]{{1,0,1},{0,0,0},{1,0,1}};
        setZeros(matrix);
        for (int i = 0; i < matrix.length; i++){
            assertArrayEquals(matrix, expected);
        }
    }
    @Test
    public void test2(){
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][] expected = new int[][]{{0,0,0,0},{0,4,5,0},{0,3,1,0}};
        setZeros(matrix);
        for (int i = 0; i < matrix.length; i++){
            assertArrayEquals(matrix, expected);
        }
    }
}