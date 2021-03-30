package alivestill.Q565;

public class Q565 {
}


class Solution {
    /// @brief naive
    public int arrayNesting(int[] nums) {
        int max = 1;    // at least 1
        int lens = nums.length;
        boolean[] visited = new boolean[lens];
        for (int i = 0; i < lens; ++ i) {
            if (!visited[i]) {
                int pos = i;
                int cnt = 0;
                do {
                    visited[pos] = true;
                    pos = nums[pos];
                    ++ cnt;
                } while (pos != i);
                max = Math.max(max, cnt);
            }
        }
        return max;
    }
}