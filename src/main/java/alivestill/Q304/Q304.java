package alivestill.Q304;

public class Q304 {

    public static NumMatrix numMatrix;

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));    // 8
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));    // 11
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));    // 12

    }
}


class NumMatrix {

    int [][] board;     ///  prefix-sum-array
    int height;
    int width;

    // board[i + 1][j + 1] = sum of rectangle starting from matrix[0][0] to matrix[i][j]
    public NumMatrix(int[][] matrix) {
        height = matrix.length;
        width = matrix[0].length;
        board = new int[height + 1][width + 1];
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                board[i + 1][j + 1] = matrix[i][j] + board[i + 1][j] + board[i][j + 1] - board[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return board[row2 + 1][col2 + 1] + board[row1][col1] - board[row2 + 1][col1] - board[row1][col2 + 1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
