package alivestill.Q778;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Q778 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        // [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
        // 16
        int[][] array = new int[][] {
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        System.out.println(so.swimInWater(array));
    }

    @Test
    @DisplayName("presssure test")
    public void pressureTest() {
        Random random = new Random();
        int[][] array = new int[50][50];
        for (int i = 0; i < 50 * 50; ++ i) {
            int row = i / 50;
            int col = i % 50;
            array[row][col] = i;
        }
        // shuffle
        for (int i = 0; i < 50 * 50; ++ i) {
            int x = random.nextInt(50 * 50 - i) + i;
            int row1 = i / 50;
            int col1 = i % 50;
            int row2 = x / 50;
            int col2 = x % 50;
            int tmp = array[row1][col1];
            array[row1][col1] = array[row2][col2];
            array[row2][col2] = tmp;
        }
        long begin = System.currentTimeMillis();
        int result = new Solution().swimInWater(array);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("it takes " + (end - begin) + "ms"); // 17 ms
    }
}

/// time: 33ms, beats 15.03%
/// space: 39.3MB, beats 31.15%

class Solution {

    /**
     * first intuition: dp or bidirectional dp
     * second thought: dfs + binary search
     * third thought: dfs, seems infeasible
     */

    /**
     * dfs + binary search
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int lens = grid.length;
        int low = lens, high = lens * lens - 1;
        int mid = -1;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (new inner().isFeasible(grid, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    class inner {
        boolean done = false;
        Set<Integer> visited = new HashSet<>();

        public boolean isFeasible(int[][] grid, int x) {
            dfs(grid, 0, 0, grid.length - 1, grid.length - 1, x);
            return done;
        }

        public void dfs(int[][] grid, int srcx, int srcy, int destx, int desty, int val) {
            if (done) {
                return ;
            }
            int state = (srcx << 16) + srcy;
            if (visited.contains(state)) {
                return ;
            }
            if (srcx < 0 || srcx > destx || srcy < 0 || srcy > desty) {
                return ;
            }
            visited.add(state);
            if (val >= grid[srcx][srcy]) {
                if (srcx == destx && srcy == desty) {
                    done = true;
                }
                // flood fill
                dfs(grid, srcx - 1, srcy, destx, desty, val);
                dfs(grid, srcx + 1, srcy, destx, desty, val);
                dfs(grid, srcx, srcy - 1, destx, desty, val);
                dfs(grid, srcx, srcy + 1, destx, desty, val);
            }
        }
    }
}