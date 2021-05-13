package alivestill.Q220;

public class Q220 {

    public static Solution so = new Solution();
    public static SolutionV2 sov2 = new SolutionV2();

    public static void main(String[] args) {
        System.out.println(so.containsNearbyAlmostDuplicate(new int[] {1, 2, 3, 1}, 3, 0));
        System.out.println(so.containsNearbyAlmostDuplicate(new int[] {1,5,9,1,5,9}, 2, 3));
        System.out.println(sov2.containsNearbyAlmostDuplicate(new int[] {1, 2, 3, 1}, 3, 0));
        System.out.println(sov2.containsNearbyAlmostDuplicate(new int[] {1,5,9,1,5,9}, 2, 3));

    }
}


class Solution {
    /// @brief brute-force, however TLE
    /// @return true if there are distinct indices i and j such that the absolute
    ///     difference between nums[i] and nums[j] is at most t and
    ///     the absolute difference between i and j is at most k
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }
        int lens = nums.length;
        if (lens < 2) {
            return false;
        }
        for (int i = 0; i < lens; ++ i) {
            for (int j = i + 1; j <= i + k && j < lens; ++ j) {
                if (Math.abs(nums[j] - nums[i]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}

class SolutionV2 {
    /// @brief range buffer
    /// @return true if there are distinct indices i and j such that the absolute
    ///     difference between nums[i] and nums[j] is at most t and
    ///     the absolute difference between i and j is at most k
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }
        int lens = nums.length;
        if (lens < 2) {
            return false;
        }
        int peek = nums[0];
        int bottom = nums[0];
        for (int i = 1; i <= k && i < lens; ++ i) {
            peek = Math.max(nums[i], peek);
            bottom = Math.min(nums[i], bottom);
        }
        for (int i = 0; i + t < lens; ++ i) {
            if (Math.abs((long)nums[i] - peek) <= k ||
                    Math.abs((long)nums[i] - bottom) <= k) {
                return true;
            }
            peek = Math.max(nums[i + t], peek);
            bottom = Math.min(nums[i + t], bottom);
        }
        return false;
    }
}