package alivestill.Q785;

import alivestill.utils.UnionFind;

import java.util.*;

public class Q785 {
}

// wrong algorithm
@Deprecated
class SolutionV2 {
    /**
     * incompatible-sets for binary split:
     *      first we have a set which holds all the elements, first
     *      we choose a number randomly as pivot of the first set then
     *      we move the incompatible elements into another set, repeat
     *      the procedure until we can't split another set anymore.
     *      In this particular problem, we repeat two times to check
     *      if it's bipartite
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Set<Integer>> incompatibleMap = new HashMap<>();
        int lens = graph.length;
        for (int i = 0; i < lens; ++ i) {
            for (int j = 0; j < graph[i].length; ++ j) {
                if (!incompatibleMap.containsKey(i)) {
                    incompatibleMap.put(i, new HashSet<>());
                }
                if (!incompatibleMap.containsKey(graph[i][j])) {
                    incompatibleMap.put(graph[i][j], new HashSet<>());
                }
                incompatibleMap.get(i).add(graph[i][j]);
                incompatibleMap.get(graph[i][j]).add(i);    // bidirectional
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < lens; ++ i) {
            if (set.contains(i)) {
                continue;
            }
            Set<Integer> incompSet = incompatibleMap.get(i);
            for (int j = i + 1; j < lens; ++ j) {
                if (incompSet != null && incompSet.contains(j)) {
                    set.add(j);
                }
            }
        }
        if (set.isEmpty()) {
            return false;
        }
        int[] array = set.stream().mapToInt(i -> i).toArray();
        for (int i = 0; i < array.length; ++ i) {
            for (int j = i + 1; j < array.length; ++ j) {
                Set<Integer> incompSet = incompatibleMap.get(array[i]);
                if (incompSet != null && incompSet.contains(array[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}


class Solution {
    /**
     * union-find
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int lens = graph.length;
        if (lens == 1) {
            return false;
        }
        UnionFind uf = new UnionFind(lens);
        for (int i = 0; i < lens; ++ i) {
            for (int j = 0; j < graph[i].length; ++ j) {
                if (uf.find(i) == uf.find(graph[i][j])) {
                    return false;
                }
            }
            for (int j = 0; j < graph[i].length - 1; ++ j) {
                uf.union(graph[i][j], graph[i][j + 1]);
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < lens; ++ i) {
            set.add(uf.find(i));
        }
        return set.size() == 2;
    }
}

