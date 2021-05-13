package alivestill.Q458;

public class Q458 {
}

class Solution {
    /// @brief dp
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;
        } else if (buckets == 2) {
            return 1;
        }
        int count = minutesToTest / minutesToDie;
        if (count == 1) {
            return buckets - 1;
        }
        return 0;
    }
}
