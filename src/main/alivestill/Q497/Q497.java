package alivestill.Q497;

import java.util.Arrays;
import java.util.Random;

public class Q497 {
}

class Solution {

    int[][] rects;
    int[] pointsSumPrefix;

    public Solution(int[][] rects) {
        this.rects = rects;
        pointsSumPrefix = new int[rects.length];
        pointsSumPrefix[0] = calculatePointsSum(rects[0]);
        for (int i = 1; i < rects.length; ++ i) {
            pointsSumPrefix[i] = pointsSumPrefix[i - 1] + calculatePointsSum(rects[i]);
        }
    }

    /// @brief average possibility based on area
    public int[] pick() {
        Random random = new Random();
        int randomNum = random.nextInt(pointsSumPrefix[pointsSumPrefix.length - 1]) + 1;
        int index = Arrays.binarySearch(pointsSumPrefix, randomNum);
        if (index < 0) {
            index = -(index + 1);
        }
        return randomPointInRectangle(rects[index]);
    }

    private int[] randomPointInRectangle(int[] rect) {
        int x1 = rect[0];
        int y1 = rect[1];
        int x2 = rect[2];
        int y2 = rect[3];
        Random random = new Random();
        int x = random.nextInt(x2 - x1 + 1) + x1;
        int y = random.nextInt(y2 - y1 + 1) + y1;
        return new int[]{x, y};
    }

    private int calculatePointsSum(int[] array) {
        return (array[2] - array[0] + 1) * (array[3] - array[1] + 1);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
