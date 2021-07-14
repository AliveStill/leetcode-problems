package alivestill.Q779;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Q779 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // 1, 1 -> 0
        // 2, 1 -> 0
        // 2, 2 -> 1
        // 3, 1 -> 0
        System.out.println(so.kthGrammar(1, 1));
        System.out.println(so.kthGrammar(2, 1));
        System.out.println(so.kthGrammar(2, 2));
        System.out.println(so.kthGrammar(3, 1));
        System.out.println(so.kthGrammar(3, 2)); // 0
    }

    @Test
    @DisplayName("range test to valid correctness of new algorithm")
    public void test1() {
        SolutionV2 v2 = new SolutionV2();
        Solution v1 = new Solution();
        final int BOUND = 32;
        for (int i = 1; i <= 30; ++ i) {
            int bound = (int)Math.pow(2, i - 1);
            for (int j = 1; j <= bound; ++ j) {
                int given = v1.kthGrammar(i, j);
                int correct = v2.kthGrammar(i, j);
                if (given != correct) {
                    System.out.printf("correct answer for f(%d, %d)= %d\n", i, j, correct);
                    System.out.printf("false result is g(%d, %d)= %d\n", i, j, given);
                    System.exit(-1);
                }
            }
        }
    }
}

class SolutionV2 {
    /**
     * logic & recursion:
     *      +----+----------+-----------------------+-------------+
     *      |    | last-bit | currently-odd-or-even | current bit |
     *      | 00 |     0    |           0           |      0      |
     *      | 01 |     0    |           1           |      1      |
     *      | 10 |     1    |           0           |      1      |
     *      | 11 |     1    |           1           |      0      |
     *      +----+----------+-----------------------+-------------+
     * @param n
     * @param k
     * @return
     */
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k == 1) {
            return 0;
        }
        int lastBit = kthGrammar(n - 1, (k + 1) / 2);
        int oddeven = (k + 1) & 1;  // 1-indexed
        return map.get((lastBit << 1) + oddeven);
    }

    private static Map<Integer, Integer> map = new HashMap<>() {{
        this.put(0, 0);
        this.put(1, 1);
        this.put(2, 1);
        this.put(3, 0);
    }};
}

/// troublesome, I will give up working on that
/// the official answer doesn't give the iteration version, yet.
class Solution {
    /**
     * logic & recursion:
     *      +----+----------+-----------------------+-------------+
     *      |    | last-bit | currently-odd-or-even | current bit |
     *      | 00 |     0    |           0           |      0      |
     *      | 01 |     0    |           1           |      1      |
     *      | 10 |     1    |           0           |      1      |
     *      | 11 |     1    |           1           |      0      |
     *      +----+----------+-----------------------+-------------+
     * @param n
     * @param k
     * @return
     */
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k == 1) {
            return 0;
        }
        int lastBit = 0;
        while (n != 1) {
            int oddeven = (k >> (n - 1)) & 1;
            lastBit = map.get((lastBit << 1) + oddeven);
            -- n;
        }
        return lastBit;
    }

    private static Map<Integer, Integer> map = new HashMap<>() {{
        this.put(0, 0);
        this.put(1, 1);
        this.put(2, 1);
        this.put(3, 0);
    }};
}
