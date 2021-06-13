package alivestill.Q1144;

import static org.junit.Assert.assertEquals;

public class Q1144 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3};
        assertEquals(2, so.movesToMakeZigzag(array));
        array = new int[]{9, 6, 1, 6, 2};
        assertEquals(4, so.movesToMakeZigzag(array));
    }
}

class Solution {
    /**
     * naive
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        int lens = nums.length;
        if (lens < 2) {
            return 0;
        }
        int sum1 = 0;
        for (int i = 0; i < lens; i += 2) { // decrease odd index number
            if (i == 0) {
                if (nums[0] >= nums[1]) {
                    sum1 += nums[0] - nums[1] + 1;
                }
            } else if (i == lens - 1) {
                if (nums[lens - 1] >= nums[lens - 2]) {
                    sum1 += nums[lens - 1] - nums[lens - 2] + 1;
                }
            } else {
                if (nums[i] >= nums[i - 1] || nums[i] >= nums[i + 1]) {
                    sum1 += nums[i] - Math.min(nums[i - 1], nums[i + 1]) + 1;
                }
            }
        }
        int sum2 = 0;
        for (int i = 1; i < lens; i += 2) { // the other way
            if (i == lens - 1) {
                if (nums[lens - 1] >= nums[lens - 2]) {
                    sum2 += nums[lens - 1] - nums[lens - 2] + 1;
                }
            } else {
                if (nums[i] >= nums[i - 1] || nums[i] >= nums[i + 1]) {
                    sum2 += nums[i] - Math.min(nums[i - 1], nums[i + 1]) + 1;
                }
            }
        }
        return Math.min(sum1, sum2);
    }
}
