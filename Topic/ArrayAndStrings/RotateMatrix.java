package Topic.ArrayAndStrings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

/**
 * Given an image represented by an N x N matrix,
 * where each pixel in the image is represented by an integer,
 * write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 */

public class RotateMatrix {
    public int[][] rotate(int[][] matrix){
        int n = matrix.length;
        int[][] newMatrix = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                newMatrix[j][n-1-i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    @Test
    public void test1(){
        int[][] matrix   = new int[][]{{1,4,3,2,5},
                                       {3,1,2,2,3},
                                       {4,3,1,0,4},
                                       {3,0,4,2,1},
                                       {5,1,4,4,2}};
        int[][] expected = new int[][]{{5,3,4,3,1},
                                       {1,0,3,1,4},
                                       {4,4,1,2,3},
                                       {4,2,0,2,2},
                                       {2,1,4,3,5}};
        int[][] actual = rotate(matrix);
        for (int i = 0; i < expected.length; i++){
            assertArrayEquals(expected[i], actual[i]);
        }
    }
}
