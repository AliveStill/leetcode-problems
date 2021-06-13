package alivestill.Q1157;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Q1157 {
}

class MajorityChecker {

    int[] array;
    public MajorityChecker(int[] arr) {
        array = arr;
    }

    /**
     * brute-force
     * @param left
     * @param right
     * @param threshold
     * @return
     */
    public int query(int left, int right, int threshold) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        int value = -1;
        for (int i = left; i <= right; ++ i) {
            int cnt = map.getOrDefault(array[i], 0);
            if (cnt == maxCount) {
                maxCount ++;
                value = array[i];
            }
            map.put(array[i], cnt + 1);
        }
        return maxCount >= threshold ? value : -1;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
