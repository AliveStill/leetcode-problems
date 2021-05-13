package alivestill.Q529;

public class Q529 {

    public static void main(String[] args) {
        Solution so = new Solution();
        char[][] matrix = new char[][] {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        for (char[] chars : so.updateBoard(matrix, new int[]{3, 0})) {
            for (char ch : chars) {
                System.out.print(ch + ", ");
            }
            System.out.println();
        }
    }
}

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        this.board = board;
        height = board.length;
        width = board[0].length;
        countMatrix = new int[height][width];
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        int count = countNeighboringMines(x, y);
        if (count == 0) {
            dfs(x, y);
            for (int i = 0; i < height; ++ i) {
                for (int j = 0; j < width; ++ j) {
                    if (countMatrix[i][j] == -1) {
                        board[i][j] = 'B';
                    } else if (countMatrix[i][j] > 0) {
                        board[i][j] = (char)('0' + countMatrix[i][j]);
                    }
                }
            }
        } else {
            board[x][y] = (char)('0' + count);
        }
        return board;
    }

    private char[][] board;
    // using values other than 0 to represent "visited status"
    private int[][] countMatrix;
    private int height;
    private int width;
    private static int[][] direction= new int[][] {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, /* neglect */ {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    private void dfs(int x, int y) {
        if (!withinBoundary(x, y) || board[x][y] != 'E' || countMatrix[x][y] != 0) {
            return;
        }
        int count = countNeighboringMines(x, y);
        if (count == 0) {
            countMatrix[x][y] = -1;
            for (int[] dir : direction) {
                int xpos = dir[0] + x;
                int ypos = dir[1] + y;
                dfs(xpos, ypos);
            }
        } else {
            countMatrix[x][y] = count;
        }
    }

    private int countNeighboringMines(int x, int y) {
        int count = 0;
        for (int[] dir : direction) {
            int xpos = x + dir[0];
            int ypos = y + dir[1];
            if (withinBoundary(xpos, ypos) && board[xpos][ypos] == 'M') {
                ++ count;
            }
        }
        return count;
    }

    private boolean withinBoundary(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }
}
