package alivestill.Q710;

import java.util.*;
import java.util.stream.Collectors;

public class Q710 {
}

class Solution {

    private Set<Integer> blacklist = new HashSet<>();
    Random random = new Random();
    int threshold;

    public Solution(int n, int[] blacklist) {
        this.blacklist.addAll(Arrays.stream(blacklist).boxed().collect(Collectors.toList()));
        this.threshold = n;
    }

    /**
     * select rejection
     * @return
     */
    public int pick() {
        int ret = -1;
        do {
            ret = random.nextInt(threshold);
        } while (!blacklist.contains(ret));
        return ret;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */