package alivestill.Q212;

import java.util.ArrayList;
import java.util.List;

public class Q212 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        System.out.println(so.findWords(board, new String[] {
                "oath", "eat", "pea", "rain"
        }));
    }
}

class Solution {
    /// @brief copied and modified from Q79.Solution
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                list.add(word);
            }
        }
        return list;
    }

    /// @brief dfs with memorization
    public boolean exist(char[][] board, String word) {
        int height = board.length;
        int width = board[0].length;
        int[][] visited = new int[height][width];
        for (int i = 0; i < height; ++ i) {
            for (int j = 0; j < width; ++ j) {
                if (board[i][j] == word.charAt(0) &&
                        validPath(board, visited, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /// @brief find a valid path
    private boolean validPath(char[][] matrix, int[][] visited,
                              int i, int j, String str, int pos) {
        // put all the constraints on the frontier of the func body
        // may be efficient and clean.
        if (str.charAt(pos) != matrix[i][j]) {
            return false;
        }
        if (str.length() == pos + 1) {
            return true;
        }
        boolean res = false;
        visited[i][j] = 1;
        if (!res && i - 1 >= 0 && visited[i - 1][j] == 0 &&
                validPath(matrix, visited, i - 1, j , str, pos + 1)) {
            res = true;
        }
        if (!res && j + 1 != matrix[0].length && visited[i][j + 1] == 0 &&
                validPath(matrix, visited, i, j + 1, str, pos + 1)) {
            res = true;
        }
        if (!res && i + 1 != matrix.length && visited[i + 1][j] == 0 &&
                validPath(matrix, visited, i + 1, j , str, pos + 1)) {
            res = true;
        }
        if (!res && j - 1 >= 0 && visited[i][j - 1] == 0 &&
                validPath(matrix, visited, i, j - 1, str, pos + 1)) {
            res = true;
        }
        visited[i][j] = 0;
        return res;
    }
}
