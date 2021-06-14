package alivestill.Q762;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Q762 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.countPrimeSetBits(6, 10));
    }

    @Test
    @DisplayName("pressure test")
    public void pressureTest() {
        final int left = 0;
        final int right = Integer.MAX_VALUE >> 5;   // divide by 32
        long begin = System.currentTimeMillis();
        System.out.println(so.countPrimeSetBits(left, right));
        long end = System.currentTimeMillis();
        System.out.println("it takes "  + (end - begin) + "ms");    // 1148ms
    }
}

class Solution {
    /**
     * brute-force bit manipulation
     * @param left
     * @param right
     * @return
     */
    public int countPrimeSetBits(int left, int right) {
        int[] primeArray = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
        Set<Integer> set = Arrays.stream(primeArray).boxed().collect(Collectors.toSet());
        int ret = 0;
        for (int i = left; i <= right; ++ i) {
            int cnt = 0;
            int num = i;
            while (num != 0) {
                num &= (num - 1);
                ++ cnt;
            }
            if (set.contains(cnt)) {
                ++ ret;
            }
        }
        return ret;
    }
}
