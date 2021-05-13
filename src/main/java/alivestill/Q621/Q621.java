package alivestill.Q621;

import alivestill.utils.Pair.Pair;

import java.util.*;

public class Q621 {
}

class Solution {
    /// @brief greedy
    /// @details get most n frequent tasks, if the number of elements k is
    ///     less than n, plus k, else plus n in every round. Here "most frequent"
    ///     means the task that has the most remaining count in the remaining
    ///     tasks
    public int leastInterval(char[] tasks, int n) {
        int lens = tasks.length;
        if (lens <= 1 || n == 0) {
            return lens;
        }
        PriorityQueue<Pair<Character, Integer>> pQueue = new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override   /* descending order */
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                return o2.getSecond() - o1.getSecond();
            }
        });
        List<Pair<Character, Integer>> tempContainer = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (Character key : map.keySet()) {
            pQueue.add(new Pair<>(key, map.get(key)));
        }
        int ret = 0;
        while (!pQueue.isEmpty()) {
            Pair<Character, Integer> pair = pQueue.poll();
            pair.setSecond(pair.getSecond() - 1);
            tempContainer.add(pair);
            ++ ret;
            int count = 0;
            while (!pQueue.isEmpty() && count != n) {
                pair = pQueue.poll();
                pair.setSecond(pair.getSecond() - 1);
                tempContainer.add(pair);
                ++ count;
            }
            ret += count; // plus idle and processing clock cycle
            for (Pair<Character, Integer> pr : tempContainer) {
                if (pr.getSecond() != 0) {
                    pQueue.add(pr);
                }
            }
            tempContainer.clear();
            if (!pQueue.isEmpty()) {
                ret += (n - count);
            }
        }
        return ret;
    }
}
