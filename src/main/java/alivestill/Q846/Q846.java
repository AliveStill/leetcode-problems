package alivestill.Q846;

import java.util.TreeMap;

public class Q846 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
        // true
        int[] array = new int[] {
                1, 2, 3, 6, 2, 3, 4, 7, 8
        };
        boolean ret = so.isNStraightHand(array, 3);
        System.out.println(ret);
    }
}

class Solution {
    /**
     * naive
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int lens = hand.length;
        if (groupSize == 1) {
            return true;
        }
        if (lens % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();    // count map
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int round = lens / groupSize;
        for (int i = 0; i < round; ++ i) {
            int smallest = map.firstKey();
            for (int j = 0; j < groupSize; ++ j) {
                int key = smallest + j;
                if (!map.containsKey(key)) {
                    return false;
                }
                int count = map.get(key);
                if (count == 1) {
                    map.remove(key);
                } else {
                    map.put(key, count - 1);
                }
            }
        }
        return true;
    }
}
