package alivestill.Q179;

import java.util.Arrays;
import java.util.Comparator;

public class Q179 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[] ints = new int[] {
                3,30,34,5,9
        };
        System.out.println(so.largestNumber(ints));
        ints = new int[] {
                43243, 432
        };
        System.out.println(so.largestNumber(ints));
    }
}

class Solution {
    /// @brief greedy, sort and link
    public String largestNumber(int[] nums) {
        int lens = nums.length;
        String[] ins = new String[lens];
        for (int i = 0; i < lens; ++ i) {
            ins[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(ins, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String str : ins) {
            sb.append(str);
        }
        String ret = sb.toString();
        // get rid of leading zeros;
        int idx = 0;
        while (idx != ret.length() && '0' == ret.charAt(idx)) {
            ++ idx;
        }
        return (idx == ret.length()) ? "0" : ret.substring(idx);
    }
}
