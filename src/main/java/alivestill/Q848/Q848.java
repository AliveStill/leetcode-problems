package alivestill.Q848;

public class Q848 {

    public static final Solution so = new Solution();

    public static void main(String[] args) {
        String ret = so.shiftingLetters("abc", new int[]{3, 5, 9});
        System.out.println(ret);
    }
}

class Solution {
    /**
     * postfix sum array
     * @param s
     * @param shifts
     * @return
     */
    public String shiftingLetters(String s, int[] shifts) {
        int shift = 0;
        int lens = s.length();
        int[] array = new int[lens];
        for (int i = lens - 1; i >= 0; -- i) {
            shift += shifts[i];
            array[i] = s.charAt(i) + shift;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lens; ++ i) {
            sb.append((char)((array[i] - 'a') % 26 + 'a'));
        }
        return sb.toString();
    }
}
