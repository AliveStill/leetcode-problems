package alivestill.Q539;

import java.util.Arrays;
import java.util.List;

public class Q539 {
}

class Solution {
    /// @brief sort and calculate distance of neighbors
    public int findMinDifference(List<String> timePoints) {
        int[] array = new int[timePoints.size()];
        int size = 0;
        for (String s : timePoints) {
            String[] sarr = s.split(":");
            array[size] = Integer.parseInt(sarr[0]) * 60 + Integer.parseInt(sarr[1]);
            ++size;
        }
        Arrays.sort(array);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size - 1; ++ i) {
            int gap = array[i + 1] - array[i];
            int ckgap = 24 * 60 - gap;
            min = Math.min(min, Math.min(gap, ckgap));
        }
        // cyclic
        min = Math.min(min, Math.min(array[size - 1] - array[0],
                24 * 60 - (array[size - 1] - array[0])));
        return min;
    }
}
