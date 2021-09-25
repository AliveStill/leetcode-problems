package alivestill.Q834;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q834 {
}


class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; ++ i) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        // choose a pivot as root, here we pick 0
        Map<Integer, Integer> map = new HashMap<>();
        //dfs(list, map, 0);
        return null;
    }

//    private void dfs(List<List<Integer>> list, Map<Integer, Integer> map, int root) {
//        if ()
//    }
}