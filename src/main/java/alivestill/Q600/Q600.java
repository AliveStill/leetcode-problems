package alivestill.Q600;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Q600 {

    @Test(timeout = 200) /* 200 ms for pressure test */
    @DisplayName("pressure test")
    public void pressureTest() {        // failed, dump overflow
        Solution so = new Solution();
        for (int i = 0; i < 1000000000; ++ i) {
            so.findIntegers(i);
        }
    }
}

class Solution {

    static int[] array = new int[1000000000 + 1];

    static {
        for (int i = 1; i <= 1000000000; ++ i) {
            array[i] = array[i - 1] + (isThereWithoutConsecutiveOnes(i) ? 1 : 0);
        }
    }

    /// @brief brute force
    public static int findIntegers(int num) {
        return array[num];
    }

    private static boolean isThereWithoutConsecutiveOnes(int x) {
        int bits = 0x3;
        int count = 30;
        while (count-- != 0) {
            if ((x & bits) == bits) {
                return false;
            }
            bits <<= 1;
        }
        return true;
    }
}