package alivestill.Q797;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q797 {

    public static void main(String[] args) {
        Solution so = new Solution();
        // [[4,3,1],[3,2,4],[3],[4],[]]
        List<List<Integer>> list = so.allPathsSourceTarget(new int[][] {
                {4, 3, 1}, {3, 2, 4}, {3}, {4}, {}
        });
        for (List<Integer> ele : list) {
            for (Integer item : ele) {
                System.out.print(item + ", ");
            }
            System.out.println();
        }
    }
}


class Solution {

    List<List<Integer>> llist = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();

    /**
     * dfs
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new ArrayList<>();
        dfs(graph, path, 0, graph.length - 1);
        return llist;
    }

    private void dfs(int[][] graph, List<Integer> path, int beginPos, int endPos) {
        if (visited.contains(beginPos)) {
            return ;    // visited
        }
        if (beginPos == endPos) {
            path.add(beginPos);
            llist.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return ;
        }
        // or dfs
        path.add(beginPos);
        visited.add(beginPos);
        int[] adjacent = graph[beginPos];
        for (int i = 0; i < adjacent.length; ++ i) {
            dfs(graph, path, adjacent[i], endPos);
        }
        visited.remove(beginPos);
        path.remove(path.size() - 1);
    }
}