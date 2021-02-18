package alivestill.Q201;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;

public class Q201 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(so.rangeBitwiseAnd(5, 7)));
        System.out.println(Integer.toBinaryString(so.rangeBitwiseAnd(0, 1)));
//        11 1111,1111 1111,1111 1111,1111 1110

    }

    @Test
    @DisplayName("error test")
    public void TestSolution() {
        assertEquals(Integer.MAX_VALUE - 1, so.rangeBitwiseAnd(Integer.MAX_VALUE - 1, Integer.MAX_VALUE));
    }
}

class Solution {
    /// @brief bit manipulation
    public int rangeBitwiseAnd(int m, int n) {
        int gap = n - m + 1;
        long ret = 0L;
        for (int i = 1; i <= 31; ++ i) {
            long pow2 = 1L << i;        // NOTE, 1L used, not 1I,
            if (gap < pow2 && (m & (pow2 - 1)) + gap <= pow2 &&
                    (m & (pow2 >> 1)) != 0 && (n & (pow2 >> 1)) != 0) {
                ret |= (pow2 >> 1);
            }
        }
        return (int)ret;
    }
}
