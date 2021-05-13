package alivestill.Q279;

public class Q279 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        for (int i = 1; i <= 10000; ++ i) {
            System.out.print(i + " : " + so.numSquares(i) + ", ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }
}

class Solution {
    /// @brief dp
    /// @param n 1 <= n <= 10^4
    public int numSquares(int n) {
        if (n == 1) {
            return 1;
        }
        int[] array = new int[n + 1];
        int num = 1;
        while (num * num <= n) {
            array[num * num] = 1;
            ++ num;
        }
        for (int i = 2; i <= n; ++ i) {
            // if not perfect square
            if (array[i] != 1) {
                // dp
                array[i] = array[1] + array[i - 1];
                num = 2;
                while (num * num <= i) {
                    int res = array[num * num] + array[i - num * num];
                    array[i] = Math.min(res, array[i]);
                    ++ num;
                }
            }
        }
        return array[n];
    }
}