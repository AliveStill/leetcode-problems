package alivestill.Q417;

import java.util.ArrayList;
import java.util.List;

public class Q417 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
//        Pacific ~   ~   ~   ~   ~
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
        int[][] matrix = new int[][] {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        for (List<Integer> ele : so.pacificAtlantic(matrix)) {
            for (Integer e : ele) {
                System.out.print(e + ", ");
            }
            System.out.println();
        }
    }
}

class Solution {
    int[][] matrix;
    int height;
    int width;

    /// @brief recursion method to find feasible paths to pacific ocean
    ///         and to Atlantic ocean, and combine the paths together
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        height = matrix.length;
        width = matrix[0].length;
        this.matrix = new int[height + 2][width + 2];
        for (int i = 0; i < height; ++ i) {
            // replace for convenience
            if (width >= 0) System.arraycopy(matrix[i], 0, this.matrix[i + 1], 1, width);
        }
        boolean[][] table1 = new boolean[height + 2][width + 2];
        boolean[][] table2 = new boolean[height + 2][width + 2];
        List<List<Integer>> list = new ArrayList<>();
        // use the left upper corner sub-matrix
        floodFlow(table1, 0, 0, 0, 0, 0, height, width);
        // use the right lower corner sub-matrix
        floodFlow(table2, height + 1, width + 1, 0, 1, 1, height + 1, width + 1);
        for (int i = 1; i <= height; ++ i) {
            for (int j = 1; j <= width; ++ j) {
                if (table1[i][j] && table2[i][j]) {
                    List<Integer> li = new ArrayList<>();
                    li.add(i - 1); li.add(j - 1);
                    list.add(li);
                }
            }
        }
        return list;
    }

    /// @brief floodflow, as it's name, recursion
    /// @details use points (lowx, lowy) and (highx, highy) to confine it's region,
    ///         in case out of boundary
    private void floodFlow(boolean[][] table, int xpos, int ypos, int oldHeight,
        int lowx, int lowy, int highx, int highy) {
        if (xpos < lowx || xpos > highx || ypos < lowy || ypos > highy) {
            return ;
        }
        if (matrix[xpos][ypos] >= oldHeight && !table[xpos][ypos]) {
            table[xpos][ypos] = true;
            floodFlow(table, xpos - 1, ypos, matrix[xpos][ypos], lowx, lowy, highx, highy);
            floodFlow(table, xpos + 1, ypos, matrix[xpos][ypos], lowx, lowy, highx, highy);
            floodFlow(table, xpos, ypos - 1, matrix[xpos][ypos], lowx, lowy, highx, highy);
            floodFlow(table, xpos, ypos + 1, matrix[xpos][ypos], lowx, lowy, highx, highy);
        }
    }
}
