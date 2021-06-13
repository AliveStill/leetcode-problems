package alivestill.Q684;

import alivestill.utils.UnionFind;

public class Q684 {
}

class Solution {
    /**
     * unifon-find
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int lens = edges.length;
        UnionFind uf = new UnionFind(lens);
        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return edge;
            }
            uf.union(edge[0], edge[1]);
        }
        return new int[2];  // never occur
    }
}
