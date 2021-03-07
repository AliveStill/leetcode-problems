package alivestill.Q495;

public class Q495 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        System.out.println(so.findPoisonedDuration(new int[]{1, 2, 3, 4}, 3));
    }

}

class Solution {
    /// @brief naive
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int lens = timeSeries.length;
        if (lens == 0) {
            return 0;
        }
        int hit = 0;
        int expire = 0;
        for (int i = 0; i < lens; ++ i) {
            if (timeSeries[i] >= expire) {
                hit += duration;
                expire = timeSeries[i] + duration;  // logical separation
            } else {
                hit -= expire - timeSeries[i];
                hit += duration;
                expire = timeSeries[i] + duration;
            }
        }
        return hit;
    }
}
