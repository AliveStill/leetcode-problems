package alivestill.Q547;

import alivestill.utils.UnionFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q547 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(so.findCircleNum(matrix));
        matrix = new int[][] {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(so.findCircleNum(matrix));
    }
}


class Solution {
    /// @brief union find
    public int findCircleNum(int[][] isConnected) {
        int lens = isConnected.length;
        UnionFind uf = new UnionFind(lens);
        for (int i = 0; i < lens; ++ i) {
            for (int j = i + 1; j < lens; ++ j) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < lens; ++ i) {
            set.add(uf.find(i));
        }
        return set.size();
    }
}