package alivestill.Q838;

public class Q838 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        // Input: dominoes = ".L.R...LR..L.."
        //Output: "LL.RR.LLRRLL.."
        String ret = so.pushDominoes(".L.R...LR..L..");
        System.out.println(ret);
    }
}

class Solution {
    /**
     * naive
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        // prefix & postfix array
        int lens = dominoes.length();
        int[] leftDist = new int[lens]; // distance of the nearest L from it, scan from right to left
        int[] rightDist = new int[lens];// distance of the nearest R from it, scan from left to right
        final int UPPER_BOUND = Integer.MAX_VALUE >> 1; // unreachable
        leftDist[lens - 1] = dominoes.charAt(lens - 1) == 'L' ? 0 : UPPER_BOUND;
        rightDist[0] = dominoes.charAt(0) == 'R' ? 0 : UPPER_BOUND;
        for (int i = 1; i < lens; ++ i) {
            rightDist[i] = dominoes.charAt(i) == 'R' ? 0 : dominoes.charAt(i) == 'L' ? UPPER_BOUND : rightDist[i - 1] + 1;
        }
        for (int i = lens - 2; i >= 0; -- i) {
            leftDist[i] = dominoes.charAt(i) == 'L' ? 0 : dominoes.charAt(i) == 'R' ? UPPER_BOUND : leftDist[i + 1] + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lens; ++ i) {
            sb.append(leftDist[i] >= Integer.MAX_VALUE >> 1 && rightDist[i] >= Integer.MAX_VALUE >> 1 ? "."
                    : leftDist[i] > rightDist[i] ? 'R'
                    : rightDist[i] > leftDist[i] ? 'L'
                    : '.');
        }
        return sb.toString();
    }
}
