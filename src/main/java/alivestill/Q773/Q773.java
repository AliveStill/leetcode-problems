package alivestill.Q773;

import java.util.*;

public class Q773 {

    public static void main(String[] args) {
        Solution so;
        // [[1,2,3],[4,0,5]], 1
        // [[1,2,3],[5,4,0]], -1 no way to go
        // [[4,1,2],[5,0,3]], 5
        // [[3,2,4],[1,5,0]], 14
        so = new Solution(); System.out.println(so.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        so = new Solution(); System.out.println(so.slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        so = new Solution(); System.out.println(so.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
        so = new Solution(); System.out.println(so.slidingPuzzle(new int[][]{{3, 2, 4}, {1, 5, 0}}));
    }
}

class Solution {

    private static final int HEIGHT = 2;
    private static final int WIDTH = 3;

    // Robin Karp, keep records of state of the board
    Map<Integer, Integer>  price = new HashMap<>();
    Set<Integer> recordStates = new HashSet<>();

    /**
     * Dijkstra (dfs)
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        Deque<Integer> queue = new ArrayDeque<>();
        int initState = (board[0][0]) + (board[0][1] << 3) + (board[0][2] << 6) +
                (board[1][0] << 9) + (board[1][1] << 12) + (board[1][2] << 15);
        price.put(initState, 0);
        queue.addLast(initState);
        while (!queue.isEmpty()) {
            int state = queue.removeFirst();
            if (recordStates.contains(state)) {
                continue;
            }
            shuffleAndCalculate(state, price.get(state), queue);   // price.get(state) never returns null
            recordStates.add(state);
        }
        return price.getOrDefault((1) + (2 << 3) + (3 << 6) + (4 << 9) + (5 << 12), -1);
    }

    private void shuffleAndCalculate(int initState, int dist, Deque<Integer> queue) {
        int[] board = new int[6];
        board[0] = initState & 0x7;
        board[1] = (initState >> 3) & 0x7;
        board[2] = (initState >> 6) & 0x7;
        board[3] = (initState >> 9) & 0x7;
        board[4] = (initState >> 12) & 0x7;
        board[5] = (initState >> 15) & 0x7;
        int[][] swapPos = new int[][] {
                /*{0, 0}, /* stay-original transformation */
                {0, 1}, {1, 2}, {3, 4}, {4, 5}, /* horizontal */
                {0, 3}, {1, 4}, {2, 5} /* vertical */
        };
        for (int[] pos : swapPos) {
            int i = pos[0];
            int j = pos[1];
            if (board[i] != 0 && board[j] != 0) {   /* check value of 0 */
                continue;
            }
            int temp = board[i];   // exchange
            board[i] = board[j];
            board[j] = temp;

            int state = (board[0]) + (board[1] << 3) + (board[2] << 6) +
                    (board[3] << 9) + (board[4] << 12) + (board[5] << 15);
            price.put(state, Math.min(
                    price.getOrDefault(state, Integer.MAX_VALUE), dist + 1));
            queue.addLast(state);

            temp = board[i];   // change back
            board[i] = board[j];
            board[j] = temp;
        }
    }
}