package alivestill.Q867;

public class Q867 {
}

class Solution {
    /**
     * naive
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] copy = new int[width][height];
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                copy[j][i] = matrix[i][j];
            }
        }
        return copy;
    }
}
