package alivestill.Q853;

import java.util.HashMap;
import java.util.TreeMap;

public class Q853 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // 12
        //[10,8,0,5,3]
        //[2,4,1,1,3]
        int count = so.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3});
        System.out.println(count);
    }
}


class Solution {
    /**
     * naive
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet(int target, int[] position, int[] speed) {
        // mapping of <pos, time-to-take>
        TreeMap<Integer, Double> map = new TreeMap<>();
        int lens = position.length;
        for (int i = 0; i < lens; ++ i) {
            map.put(position[i], ((double)target - position[i]) / speed[i]);
        }
        int groupCount = 1;
        double lastTimeToTake = map.remove(map.lastKey());
        while (!map.isEmpty()) {
            double time = map.remove(map.lastKey());
            double diff;
            if ((diff = time - lastTimeToTake) > 1e-8 ) {    /* time larger than lastTimeToTake */
                ++ groupCount;
                lastTimeToTake = time;
            }
        }
        return groupCount;
    }
}