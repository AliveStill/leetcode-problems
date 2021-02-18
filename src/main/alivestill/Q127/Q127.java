package alivestill.Q127;

import java.util.*;

public class Q127 {
    static Solution so = new Solution();
    public static void main(String[] args) {
        String[] arr = new String[] {
           "hot","dot","dog","lot","log","cog"
        };
        ArrayList<String> ls = new ArrayList<>();
        Collections.addAll(ls, arr);
        System.out.println(so.ladderLength("hit", "cog", ls));
        arr = new String[] {
                "hot","dot","dog","lot","log"
        };
        ls.clear();
        Collections.addAll(ls, arr);
        System.out.println(so.ladderLength("hit", "cog", ls));
    }
}

class Solution {
    /// @brief brute-force preprocess and Dijkstra
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // step1, preprocess
        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (String ele : wordList) {
            map.put(ele, idx);
            ++ idx;
        }
        if (null == map.get(endWord)) { // end word doesn't exist
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (null == map.get(beginWord)) { // begin word doesn't duplicate
            wordList.add(beginWord);
            map.put(beginWord, idx);
        }
        ArrayList<ArrayList<Integer>> llist = new ArrayList<>();
        int size = wordList.size();
        for (int i = 0; i < size; ++ i) {
            llist.add(new ArrayList<>());
        }

        // step2, construct word graph
        for (int i = 0; i < size; ++ i) {
            for (int j = i + 1; j < size; ++ j) {
                if (1 == calcStringDistance(wordList.get(i), wordList.get(j))) {
                    llist.get(i).add(j);
                    llist.get(j).add(i);
                }
            }
        }

        // step3, Dijkstra
        int[] dists = new int[size];
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return dists[o1] - dists[o2];
                    }
                }
        );  // FIXMED, declare in a proper way, less is higher

        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; ++ i) {
            dists[i] = Integer.MAX_VALUE;
        }
        int beginIndex = map.get(beginWord);
        dists[beginIndex] = 0;
        pq.offer(beginIndex);
        while (!pq.isEmpty()) {
            int index = pq.poll();
            if (visited[index]) {
                continue;
            }
            visited[index] = true;
            ArrayList<Integer> array = llist.get(index);
            for (Integer integer : array) {
                if (dists[index] != Integer.MAX_VALUE && dists[integer] > dists[index] + 1) {
                    dists[integer] = dists[index] + 1;
                }
                pq.offer(integer);
            }
        }
        int ret = dists[map.get(endWord)];
        return  ret == Integer.MAX_VALUE ? 0 : ret + 1;
    }

    /// @note length of str1 must equal length of str2
    private int calcStringDistance(String str1, String str2) {
        int lens = str1.length();
        int cnt = 0;
        for (int i = 0; i < lens; ++ i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                ++ cnt;
            }
        }
        return cnt;
    }
}

// solution v2.0, virtual nodes, speed up graph construction

// solution v3.0, bidirectional BFS