package alivestill.Q289;

public class Q289 {
}

/// details: live represented by 1 and dead represented by 0
///          1. Any live cell with fewer than two live neighbors dies as if caused by under-population.
///          2. Any live cell with two or three live neighbors lives on to the next generation.
///          3. Any live cell with more than three live neighbors dies, as if by over-population.
///          4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
///  so, for live cells:
///          0~1 living cells around: -> dead
///          2~3 living cells around: -> stay live
///          4~8 living cells around: -> dead
///     for dead cells:
///          3 living cells around: -> live
///          others: -> stay dead
/// FIXME, for this specific problem, why cells alter their status bases on its neighbors' original status rather than their status after dynamical alteration?
///     and then, if the problem goes like what I say, how to solve this problem?
class Solution {

    /// @brief alter status based on the original status of neighbors of each cell, not dynamical.
    public void gameOfLife(int[][] board) {
        int height = board.length;
        int width = board[0].length;

        // direction array
        int[][] direction = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, /*{0, 0},*/ {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        int[][] matrix = new int[height][width];    // count of alive neighbors

        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                if (board[i][j] == 1) {
                    for (int[] array : direction) {
                        int x = i + array[0];
                        int y = j + array[1];
                        if (x >= 0 && x < height && y >= 0 && y < width && matrix[x][y] == 1) {
                            ++ matrix[i][j];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                if (board[i][j] == 1) { // alive
                    if (matrix[i][j] < 2 || matrix[i][j] > 3) {
                        board[i][j] = 0;    // kill
                    }
                } else {    // dead
                    if (matrix[i][j] == 3) {
                        board[i][j] = 1;    // back to life
                    }
                }
            }
        }
    }
}
