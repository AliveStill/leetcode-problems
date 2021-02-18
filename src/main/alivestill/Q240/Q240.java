package alivestill.Q240;

public class Q240 {
    
    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };
        System.out.println(so.searchMatrix(matrix, 26));
    }
}

class Solution {
    /// @brief split and merge
    public boolean searchMatrix(int[][] matrix, int target) {
        return exist(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
    }

    /// @brief search in range from matrix[beginx][beginy] to matrix[endx][endy]
    public boolean exist(int[][] matrix, int beginx, int beginy,
                         int endx, int endy, int target) {
        if (beginx < 0 || beginx > endx || beginy < 0 || beginy > endy) {
            return false;
        }
        if (target < matrix[beginx][beginy] || target > matrix[endx][endy]) {
            return false;
        }
        int xpos = beginx;
        int ypos = beginy;
        while (target > matrix[xpos][ypos]) {
            ++xpos;
            ++ypos;
            xpos = Math.min(xpos, endx);
            ypos = Math.min(ypos, endy);
        }
        if (target == matrix[xpos][ypos]) {
            return true;
        }
        return exist(matrix, beginx, ypos, xpos - 1, endy, target) ||
                exist(matrix, xpos, beginy, endx, ypos - 1, target);
    }
}
