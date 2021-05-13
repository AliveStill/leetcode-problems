package alivestill.Q1143;

public class Q1143 {
}

class Solution {

    /**
     * naive dp
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int lens1 = text1.length();
        int lens2 = text2.length();
        int[][] matrix = new int[lens1 + 1][lens2 + 1];
        for (int i = 1; i <= lens1; ++ i) {
            for (int j = 1; j <= lens2; ++ j) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;    // or pick max?
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j - 1],
                            Math.max(matrix[i - 1][j], matrix[i][j - 1]));
                }
            }
        }
        return matrix[lens1][lens2];
    }
}