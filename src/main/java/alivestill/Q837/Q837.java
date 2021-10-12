package alivestill.Q837;

import alivestill.utils.Pair.*;
import alivestill.utils.Triplet.Triplet;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Q837 {

    public static void main(String[] args) {
        // Input: n = 21, k = 17, maxPts = 10
        //Output: 0.73278
        double d = new Solution().new21Game(21, 17, 10);
        System.out.println(d);

        new Q837().test();
    }


    @Test
    public void test() {
        double d = new Solution().new21Game(9676,
                8090,
                3056);
        System.out.println(d);
    }
}

/* dfs TLE */
@Deprecated
class Solution {

    // dfs
    public double new21Game(int n, int k, int maxPts) {
        N = n;
        K = k;
        MAXPTS = maxPts;
        return dfs(0);
    }

    private double dfs(int currentSum) {
        // should end
        if (currentSum >= K) {
            return currentSum <= N ? 1 : 0;
        }
        if (currentSum > N) {
            return 0;
        }
        if (map.containsKey(currentSum)) {
            return map.get(currentSum);
        }
        double prob = 0.0;
        for (int i = MAXPTS; i >= 1; -- i) {
            double roundProb = dfs(currentSum + i);
            prob += roundProb;
        }
        double realProb = prob / MAXPTS;
        map.put(currentSum, realProb);
        return realProb;
    }


    // keep track of probability current_sum
    Map<Integer, Double> map = new HashMap<>();
    int K;
    int N;
    int MAXPTS;
}