package alivestill.Q688;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Q688 {
}

class Solution {

    Map<Integer, Double> memorization = new HashMap<>();

    /**
     * memorization dp, TLE
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability(int n, int k, int row, int column) {
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0.0d;
        } else if (k == 0) {
            return 1.0d;    // must not out of boundary
        }
        Integer key = toKey(k, row, column);
        if (memorization.containsKey(key)) {
            return memorization.get(key);
        }
        int[][]  directions = new int[][] {
                {-1, -2}, {-2, -1},
                {-2, 1}, {-1, 2},
                {1, 2}, {2, 1},
                {2, -1}, {1, -2}
        };
        double ret = 0.0d;
        for (int[] direction : directions) {
            int x = row + direction[0];
            int y = column + direction[1];
            double prob = knightProbability(n, k - 1, x, y);
            if (Math.abs(prob) < 1e-10) {   // not out of boundary
                // use symmetry as much as possible
                // TODO, 1/8 symmetry
            }
            ret += prob / 8;
        }
        return ret;
    }

    private Integer toKey(int k, int x, int y) {
        int i = 0;
        i |= (k << 16);
        i |= (x << 8);
        i |= y;
        return Integer.valueOf(i);
    }

    private int getX(Integer i) {
        return bitwise(i, 8);
    }

    private int getY(Integer i) {
        return bitwise(i, 0);
    }

    private int getK(Integer i) {
        return bitwise(i, 16);
    }

    private int bitwise(Integer i, int shift) {
        int num = i.intValue();
        return (num >> shift) & 0xff;
    }
}
