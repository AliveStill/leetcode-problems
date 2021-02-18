package alivestill.Q419;

public class Q419 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
        };
        System.out.println(so.countBattleships(board));

        board = new char[][] {{'X'}};
        System.out.println(so.countBattleships(board));

    }
}

/// ok, I misunderstood the meaning of the problem, it always give a valid board, we just counting
class SolutionV1 {
    char[][] matrix;
    int height;
    int width;

    /// @brief one-pass, O(m * n) extra space, may be O(1) space but for simplicity
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }
        height = board.length;
        width = board[0].length;
        matrix = new char[height + 2][width + 2];   // use another 2D matrix to simplify boundary check
        for (int i = 0; i < height; ++ i) {
            matrix[i][0] = '.'; matrix[i][width + 1] = '.';
            System.arraycopy(board[i], 0, matrix[i + 1], 1, width);
        }
        for (int i = 0; i < width + 2; ++ i) {
            matrix[0][i] = '.';
            matrix[height + 1][i] = '.';
        }
        int ret = 0;
        for (int i = 1; i <= height; ++ i) {
            for (int j = 1; j <= width; ++ j) {
                if (matrix[i][j] == 'X') {
                    if (checkAroundInvalid(i, j)) {
                        return 0;
                    }
                    else if (matrix[i][j - 1] == '.' && matrix[i - 1][j] == '.') {
                        ++ ret;
                    }
                }
            }
        }
        return ret;
    }

    /// @brief check if there is any pattern like:
    ///         XX  or  XX  or  .X  or  X.
    ///         X.      .X      XX      XX
    ///         since we only get into this because (xpos, ypos) is an X, we won't check it
    /// @return true, if there is any pattern above
    ///         false, otherwise
    private boolean checkAroundInvalid(int xpos, int ypos) {
        return (matrix[xpos - 1][ypos] == 'X' || matrix[xpos + 1][ypos] == 'X')
                && (matrix[xpos][ypos - 1] == 'X' || matrix[xpos][ypos + 1] == 'X');
    }

}

class Solution {
    char[][] matrix;
    int height;
    int width;

    /// @brief one-pass, O(m * n) extra space, may be O(1) space but for simplicity
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }
        height = board.length;
        width = board[0].length;
        matrix = new char[height + 2][width + 2];   // use another 2D matrix to simplify boundary check
        for (int i = 0; i < height; ++ i) {
            matrix[i + 1][0] = '.'; matrix[i + 1][width + 1] = '.';
            System.arraycopy(board[i], 0, matrix[i + 1], 1, width);
        }
        for (int i = 0; i < width + 2; ++ i) {
            matrix[0][i] = '.';
            matrix[height + 1][i] = '.';
        }
        int ret = 0;
        for (int i = 1; i <= height; ++ i) {
            for (int j = 1; j <= width; ++ j) {
                if (matrix[i][j] == 'X'
                        && matrix[i][j - 1] == '.'
                        && matrix[i - 1][j] == '.') {
                    ++ ret;
                }
            }
        }
        return ret;
    }
}
