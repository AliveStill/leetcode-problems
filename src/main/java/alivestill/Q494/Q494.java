package alivestill.Q494;

import java.util.HashMap;
import java.util.Map;

public class Q494 {
}

class SolutionV2 {
    /// @brief dfs
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }
        requestSum = S;
        dfs(nums, 0, 0);
        return res;
    }

    int res = 0;
    int requestSum;

    private void dfs(int[] array, int sum, int depth) {
        if (depth == array.length) {
            if (sum == requestSum) {
                ++ res;
            }
            return ;
        }
        dfs(array, sum + array[depth], depth + 1);
        dfs(array, sum - array[depth], depth + 1);
    }
}


class Solution {
    /// @brief bidirectional dfs
    public int findTargetSumWays(int[] nums, int S) {
        int lens = nums.length;
        if (lens == 0) {
            return 0;
        } else if (lens == 1) {
            if (S == nums[0]) {
                return 1 + (S == 0 ? 1 : 0);
            }
            return 0;
        }
        int middle = lens / 2;
        Map<Integer, Integer> lHalf = new HashMap<>();  // left part
        Map<Integer, Integer> rHalf = new HashMap<>();  // right part
        dfs(nums, 0, middle, 0, lHalf);
        dfs(nums, middle, lens, 0, rHalf);
        int ret = 0;
        for (Integer key : lHalf.keySet()) {
            Integer count = rHalf.get(S - key);
            if (count != null) {
                ret += lHalf.get(key) * count;
            }
        }
        return ret;
    }

    private void dfs(int[] array, int low, int high,
                     int sum, Map<Integer, Integer> map) {
        if (low == high) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return ;
        }
        dfs(array, low + 1, high, sum + array[low], map);
        dfs(array, low + 1, high, sum - array[low], map);
    }
}