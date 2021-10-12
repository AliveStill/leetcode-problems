package alivestill.Q802;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q802 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // [[1,2],[2,3],[5],[0],[5],[],[]]
        // return: [2, 4, 5, 6]
        int[][] graph = new int[][] {
                {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
        };
        for (Integer ele : so.eventualSafeNodes(graph)) {
            System.out.println(ele);
        }
    }

    @Test
    public void test1() {
        // [[0],[2,3,4],[3,4],[0,4],[]]
        // return: [4]
        int[][] graph = new int[][] {
                {0}, {2, 3, 4}, {3, 4}, {0, 4}, {}
        };
        for (Integer ele : so.eventualSafeNodes(graph)) {
            System.out.println(ele);
        }
    }

    @Test
    public void test2() {
        int[][] graph = new int[][] {
                {1}, {2}, {1}
        };
        for (Integer ele : so.eventualSafeNodes(graph)) {
            System.out.println(ele);
        }
    }

    @Test
    public void test3() {
        // [[9,11,19],[3,5,8,12,18],[0,5,11,14,15],[7,14,18,19],[1,6,8,12,13,15],[7,10,11,15,18,19],[],[11,18],[],[10,11,12,15,17],[5,17],[8,13,14,15],[11,13,16,19],[14,15,16,18,19],[18,19],[17,18],[17,18,19],[18,19],[19],[]]
        // return: [3,6,7,8,11,12,13,14,15,16,17,18,19]
        int[][] graph = new int[][] {
                {9,11,19},{3,5,8,12,18},
                {0,5,11,14,15},{7,14,18,19},
                {1,6,8,12,13,15},{7,10,11,15,18,19},
                {},{11,18},{},{10,11,12,15,17},
                {5,17},{8,13,14,15},{11,13,16,19},
                {14,15,16,18,19},{18,19},{17,18},
                {17,18,19},{18,19},{19},{}};
        for (Integer ele : so.eventualSafeNodes(graph)) {
            System.out.println(ele);
        }
    }
}

@Deprecated /* answer not correct */
class SolutionV0 {
    /**
     * naive
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int lens = graph.length;
        boolean[] knockOut = new boolean[lens];
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < lens; ++ i) {
            list.add(new HashSet<>());
        }
        for (int i = 0; i < lens; ++ i) {
            for (int out : graph[i]) {
                // there's a circle, mark
                if (list.get(i).contains(out)
                    || out == i /* self loop */
                    || knockOut[out] /* out edge knock out */) {
                    Set<Integer> set = list.get(i);
                    for (Integer ele : set) {
                        knockOut[ele] = true;
                    }
                    knockOut[out] = true;
                    knockOut[i] = true;
                }
                list.get(out).add(i);
                list.get(out).addAll(list.get(i));
            }
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < lens; ++ i) {
            if (!knockOut[i]) {
                ret.add(i);
            }
        }
        return ret;
    }
}

class Solution {

    enum Status {
        NOTVISITED, SAFE, UNSAFE
    }
    private Status[] status;

    /**
     * dfs
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int lens = graph.length;
        status = new Status[lens];
        for (int i = 0; i < lens; ++ i) {
            status[i] = Status.NOTVISITED;
        }
        for (int i = 0; i < lens; i++) {
            wrap(graph, i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lens; ++ i) {
            if (status[i] == Status.SAFE) {
                list.add(i);
            }
        }
        return list;
    }

    private void wrap(int[][] graph, int begin) {
        if (status[begin] != Status.NOTVISITED) {
            return;
        }
        Set<Integer> path = new HashSet<>();
        dfs(graph, path, begin);
    }

    /**
     * if there is any circle, return true, else return false
     * @param path
     * @param pos
     * @return
     */
    private boolean dfs(int[][] graph, Set<Integer> path, int pos) {
        if (status[pos] == Status.UNSAFE) {
            return true;
        } else if (status[pos] == Status.SAFE) {
            return false;
        }
        boolean ret = false;
        if (path.contains(pos)) {
            status[pos] = Status.UNSAFE;
            ret = true;
        }
        path.add(pos);
        for (int outPoint : graph[pos]) {
            // acyclic
            if (outPoint == pos) {
                status[pos] = Status.UNSAFE;
                ret = true;
            } else if (status[outPoint] == Status.UNSAFE) {
                status[pos] = Status.UNSAFE;
                ret = true;
            } else if (status[outPoint] == Status.NOTVISITED
                    && dfs(graph, path, outPoint)) {
                status[pos] = Status.UNSAFE;
                ret = true;
            }
        }
        if (status[pos] != Status.UNSAFE) {
            status[pos] = Status.SAFE;
        }
        path.remove(pos);
        return ret;
    }
}
