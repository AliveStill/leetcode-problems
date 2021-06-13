package alivestill.Q692;

import alivestill.utils.Pair.Pair;

import java.util.*;

public class Q692 {
}

class Solution {
    /**
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String ele : words) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        PriorityQueue<Pair<Integer, String>> pq = new PriorityQueue<Pair<Integer, String>>((p1, p2) -> {
            if (p1.getFirst() > p2.getFirst()) {
                return -1;
            } else if (p1.getFirst() < p2.getFirst()) {
                return 1;
            } else {
                return p1.getSecond().compareTo(p2.getSecond());
            }
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(new Pair<Integer, String>(entry.getValue(), entry.getKey()));
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; ++ i) {
            list.add(pq.poll().getSecond());
        }
        return list;
    }
}
