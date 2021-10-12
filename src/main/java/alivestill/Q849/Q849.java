package alivestill.Q849;

public class Q849 {
}

class Solution {
    /**
     * naive, logic
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats) {
        int lens = seats.length;
        int left = 0;
        // must stop at any pos
        while (left < lens && seats[left] == 0) {
            ++ left;
        }
        int ret = left;
        int right;
        do {
            right = left + 1;
            while (right < lens && seats[right] == 0) {
                ++ right;
            }
            if (right == lens) {
                ret = Math.max(ret, right - left - 1);
            } else {
                ret = Math.max(ret, right - left);
                left = right;
            }
        } while (right < lens);
        return ret;
    }
}
