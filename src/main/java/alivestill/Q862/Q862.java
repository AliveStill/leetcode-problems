package alivestill.Q862;

public class Q862 {
}

class Solution {
    // slide window, seems infeasible
    // prefix sum array, recommended

    /**
     * O(n*n) time complexity
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubarray(int[] nums, int k) {
        int lens = nums.length;
        long[] prefixSumArray = new long[lens];
        prefixSumArray[0] = nums[0];
        for (int i = 1; i < lens; ++ i) {
            prefixSumArray[i] = prefixSumArray[i - 1] + nums[i];
        }
        int ret = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < lens; ++ i) {
            for (int j = i + 1; j < lens; ++ j) {
                if (prefixSumArray[j] - prefixSumArray[i] >= k) {
                    ret = Math.min(ret, j - i);
                }
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
