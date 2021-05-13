package alivestill.Q329;

public class Q329 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        System.out.println(so.longestIncreasingPath(matrix));
    }
}

class Solution {
    int[][] pathRecord;
    int height;
    int width;

    /// @brief dfs, however TLE
    public int longestIncreasingPath(int[][] matrix) {
        height = matrix.length;
        width = matrix[0].length;
        pathRecord = new int[height][width];
        int res = 1;
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                if (pathRecord[i][j] == 0) {
                    dfs(matrix, i, j);
                }
                res = Math.max(res, pathRecord[i][j]);
            }
        }
        return res;
    }

    public int dfs(int[][] matrix, int x, int y) {
        int[] path = new int[4];
        path[0] = (x - 1 >= 0 && matrix[x - 1][y] < matrix[x][y]) ? dfs(matrix, x - 1, y) : 0;
        path[1] = (y - 1 >= 0 && matrix[x][y - 1] < matrix[x][y]) ? dfs(matrix, x, y - 1) : 0;
        path[2] = (y + 1 < width && matrix[x][y + 1] < matrix[x][y]) ? dfs(matrix, x, y + 1) : 0;
        path[3] = (x + 1 < height && matrix[x + 1][y] < matrix[x][y]) ? dfs(matrix, x + 1, y) : 0;
        pathRecord[x][y] = 1 + Math.max(Math.max(path[0], path[1]), Math.max(path[2], path[3]));
        return pathRecord[x][y];
    }
}
