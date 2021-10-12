package alivestill.Q858;

public class Q858 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // Input: p = 2, q = 1
        //Output: 2
        System.out.println(so.mirrorReflection(2, 1));
        // Input: p = 3, q = 1
        //Output: 1
        System.out.println(so.mirrorReflection(3, 1));
    }
}

class Solution {
    /**
     * 模拟一个不停向上增长的过程，使用对称性解决问题，当达到二者的最小公倍数时停止，
     * 此时：1。看在左边还是右边，在左边的话一定是到达了左上角（题目说必定正确，排除原路返回的情况）
     * 2。如果是右边的话通过反射次数的奇偶来确定是在右上角还是右下角
     * @param p
     * @param q
     * @return
     */
    public int mirrorReflection(int p, int q) {
        int count = lcm(p, q);
        int pc = count / p;
        int qc = count / q;
        if ((qc & 1) == 0) {    /* qc even */
            return 2;
        }
        if ((pc & 1) == 0) {    /* pc even */
            return 0;
        }
        // else pc is odd
        return 1;
    }

    private int lcm(int m, int n) {
        int num = gcd(m, n);
        return m / num * n;
    }

    private int gcd(int m, int n) {
        if (n == 0) {
            return m;
        }
        return gcd(n, m % n);
    }
}
