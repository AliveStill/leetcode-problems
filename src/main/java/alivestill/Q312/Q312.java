package alivestill.Q312;

public class Q312 {
}

class Solution {
    public int maxCoins(int[] nums) {
        int lens = nums.length;
        if (lens == 1) {
            return nums[0];
        }
        int[] array = new int[lens + 2];
        System.arraycopy(nums, 0, array, 1, lens);
        int[][] matrix = new int[lens + 2][lens + 2];
        for (int i = 1; i <= lens; ++ i) {
            matrix[i][i] = array[i];
        }
        for (int i = 1; i < lens; ++ i) {
            matrix[i][i + 1] = array[i] * array[i + 1] + Math.max(array[i], array[i + 1]);
        }
        return 0;
    }
}
