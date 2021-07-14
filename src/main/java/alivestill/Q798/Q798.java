package alivestill.Q798;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Q798 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // return 3, [2, 3, 3, 4, 3]
        System.out.println(so.bestRotation(new int[]{2, 3, 1, 4, 0}));
    }


    @Test
    @DisplayName("random generated 10000 nums to see the answer pattern")
    public void test1() {
        final int COUNT = 20000;
        int[] array = new int[COUNT];
        Random random = new Random();
        for (int i = 0; i < COUNT; ++ i) {
            array[i] = random.nextInt(COUNT);
        }
        long begin = System.currentTimeMillis();
        System.out.println("Answer: ");
        System.out.println(new Solution().bestRotation(array));
        long end = System.currentTimeMillis();
        System.out.println("it takes " + (end - begin) + "ms");     // time without debug information output
                                                                    // 1187 ms

        // neighboring difference at most 2. all in [-2, 2]
    }
}

class SolutionV2 {

    /**
     * brute-force
     * @param nums
     * @return
     */
    public int bestRotation(int[] nums) {
        int lens = nums.length;
        if (lens <= 0) {
            return -1;
        }
        TreeMap<Integer, Integer> price = new TreeMap<>();
        for (int i = 0; i < lens; ++ i) {
            int score = score(nums, i);
            if (!price.containsKey(score)) {
                price.put(score, i);
            }
            System.out.println("i = " + i + ", score = " + score);  // note, test
        }
        return price.lastEntry().getValue();
    }

    // calculate the score after shift
    private int score(int[] nums, int shift) {
        int score = 0;
        int lens = nums.length;
        for (int i = 0; i < lens; ++ i) {
            if (nums[(shift + i) % lens] <= i) {
                ++ score;
            }
        }
        return score;
    }
}

class Solution {

    /**
     * reverse procedure, originally we want f: index i that makes maximum
     *      sum of all  1, if A[j] <= j for every in [0, n-1], while A is a permutation of origin array
     *                 {
     *                  0, otherwise
     *  now we want g: (just makes A[j] > j in last statements and change maximum to minimum)
     *  and notice that f equals to g
     * @param nums
     * @return
     */
    public int bestRotation(int[] nums) {
        int lens = nums.length;
        if (lens == 0) {
            return -1;
        }
        if (lens == 1) {
            return 0;
        }
        int min = score(nums, 0);
        int index = 0;
        int cost = min;      // score of n items
        for (int i = 1; i < lens; ++ i) {
            // cost of first n - 1 items after rotation, based on cost of n items of last rotation
            int n1cost;
            if (nums[i - 1] > 0) {
                n1cost = cost - 1;
            } else {
                n1cost = cost;
            }
            // cost of current iteration
            if (nums[i - 1] > lens - 1) {
                cost = n1cost + 1;
            } else {
                cost = n1cost;
            }
            if (cost < min) {
                min = cost;
                index = i;
            }
        }
        return index;
    }

    // calculate the score after shift
    private int score(int[] nums, int shift) {
        int score = 0;
        int lens = nums.length;
        for (int i = 0; i < lens; ++ i) {
            if (nums[(shift + i) % lens] > i) {
                ++ score;
            }
        }
        return score;
    }
}
