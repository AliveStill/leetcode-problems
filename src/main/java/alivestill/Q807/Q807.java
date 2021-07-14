package alivestill.Q807;

public class Q807 {
}


class Solution {
    /**
     * naive
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[] rowMax = new int[height];
        int[] colMax = new int[height];
        for (int i = 0; i < height; ++ i) {
            int max = 0;
            for (int j = 0; j < width; ++ j) {
                max = Math.max(max, grid[i][j]);
            }
            rowMax[i] = max;
            max = 0;
            for (int j = 0; j < width; ++ j) {
                max = Math.max(max, grid[j][i]);
            }
            colMax[i] = max;
        }
        int ret = 0;
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                int min = Math.min(rowMax[i], colMax[j]);
                if (grid[i][j] < min) {
                    ret += (min - grid[i][j]);
                }
            }
        }
        return ret;
    }
}