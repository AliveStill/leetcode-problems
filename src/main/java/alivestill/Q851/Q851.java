package alivestill.Q851;

import java.util.ArrayList;
import java.util.List;

public class Q851 {

    public static void main(String[] args) {
        // [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]
        //[3,2,5,4,6,1,7,0]
        // return [5,5,2,5,4,5,6,7]
        Solution so = new Solution();
        int[][] matrix = new int[][] {
                {1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}
        };
        int[] array = new int[] {
                3,2,5,4,6,1,7,0
        };
        for (int ret : so.loudAndRich(matrix, array)) {
            System.out.print(ret);
            System.out.print(",");
        }
    }
}

class Solution {

    /**
     * dfs
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int lens = quiet.length;
        ret = new int[lens];
        List<List<Integer>> llist = new ArrayList<>();
        for (int i = 0; i < lens; ++ i) {
            llist.add(new ArrayList<>());
            ret[i] = -1;    // unreachable
        }
        for (int[] pair : richer) {
            int from = pair[1];
            int to = pair[0];
            llist.get(from).add(to);
        }
        for (int i = 0; i < lens; ++ i) {
            dfs(llist, quiet, i);
        }
        return ret.clone();
    }

    private int dfs(List<List<Integer>> llist, int[] quiet, int cur) {
        if (ret[cur] != -1) {
            return ret[cur];
        }
        int least = cur;
        for (int to : llist.get(cur)) {
            int index = dfs(llist, quiet, to);
            least = quiet[least] < quiet[index] ? least : index;
        }
        return ret[cur] = least;
    }

    // record the person, not the quietness
    int[] ret;

}
