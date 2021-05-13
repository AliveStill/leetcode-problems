package alivestill.Q421;

public class Q421 {

}

class SolutionV2 {
    /// @obsolete invalid algorithm
    /// @brief find the maximum element and XOR with any other
    ///     element, find their maximum result and return
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int ele : nums) {
            max = Math.max(max, ele);
        }
        int ret = max;
        for (int ele : nums) {
            ret = Math.max(ret, max ^ ele);
        }
        return ret;
    }
}

class Solution {
    /// @brief brute-force
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int lens = nums.length;
        for (int i = 0; i < lens; ++ i) {
            for (int j = i + 1; j < lens; ++ j) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }
}


