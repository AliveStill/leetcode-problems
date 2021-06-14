package alivestill.Q739;

public class Q739 {
}

class Solution {
    /**
     * naive linear search
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int lens = temperatures.length;
        int[] array = new int[lens];    // least index of next Greater Number
        int pos = 1;
        while (pos < lens && temperatures[0] >= temperatures[pos]) {
            ++ pos;
        }
        array[0] = pos;
        for (int i = 1; i < lens; ++ i) {
            if (temperatures[i] >= temperatures[i - 1]) {
                pos = array[i - 1]; // search from here
            } else {
                pos = i + 1;    // search end at array[i - 1], at most
            }
            while (pos < lens && temperatures[i] >= temperatures[pos]) {
                ++ pos;
            }
            array[i] = pos;
        }
        for (int i = 0; i < lens; ++ i) {
            array[i] = (array[i] == lens) ? lens : array[i] - i;
        }
        return array;
    }
}
