package alivestill.Q801;

import org.junit.jupiter.api.Test;

public class Q801 {

    public static void main(String[] args) {
        Solution so = new Solution();
        // nums1 = [1,3,5,4], nums2 = [1,2,3,7], return 1
        System.out.println(
                so.minSwap(new int[]{1, 3, 5, 4},
                        new int[]{1, 2, 3, 7}));
    }

    @Test
    public void test1() {
        long begin = System.currentTimeMillis();
        Solution so = new Solution();
        System.out.println(
                so.minSwap(new int[]{4, 10, 13, 14, 17, 19, 21, 24, 26,
                                27, 28, 29, 34, 37, 38, 42, 44, 46, 48,
                                51, 52, 53, 54, 57, 58, 59, 64, 65, 66,
                                67, 71, 73, 75, 76, 80, 81, 82, 83, 86,
                                88, 89, 90, 95, 97, 98, 99, 101, 105,
                                106, 108, 109, 110, 111, 112, 115,
                                119, 121, 122, 123, 124, 125, 126,
                                127, 128, 129, 130, 131, 133, 136,
                                138, 143, 145, 147, 149, 150, 153,
                                158, 160, 163, 164, 165, 167, 168, 169,
                                172, 173, 174, 176, 178, 179, 183, 184,
                                186, 188, 189, 192, 193, 194, 198, 200},
                        new int[]{0, 1, 3, 5, 6, 7, 11, 13, 15, 16, 17,
                                21, 37, 39, 41, 42, 43, 45, 47, 50, 53,
                                55, 56, 57, 64, 66, 67, 68, 69, 70, 71,
                                72, 74, 75, 76, 77, 79, 80, 87, 88, 89,
                                95, 96, 97, 98, 100, 101, 105, 106, 107,
                                108, 112, 113, 115, 116, 118, 119, 122,
                                124, 125, 126, 127, 128, 131, 135, 136,
                                137, 138, 139, 140, 144, 145, 148, 150,
                                151, 154, 159, 160, 161, 162, 163, 167,
                                168, 170, 171, 174, 176, 178, 179, 180,
                                181, 185, 187, 189, 190, 191, 192, 198,
                                199, 200}));
        long end = System.currentTimeMillis();
        System.out.println("it takes " + (end - begin) + "ms");
    }
}

class Solution {

    private int ret = Integer.MAX_VALUE;

    /**
     * dfs
     * @param nums1
     * @param nums2
     * @return
     */
    public int minSwap(int[] nums1, int[] nums2) {
        int lens = nums1.length;
        dfs( nums1, nums2, 0, 0);
        return ret;
    }

    public void dfs(int[] nums1, int[] nums2, int pos, int count) {
//        System.out.println("pos = " + pos);
        if (pos == nums1.length - 1) {
            ret = Math.min(ret, count);
            return;
        }
        if (Math.max(nums1[pos], nums2[pos]) < Math.min(nums1[pos + 1], nums2[pos + 1])) {
            // non-change or exchange both ok, better not
            dfs(nums1, nums2, pos + 1, count);
        }
        else if (nums1[pos] < nums1[pos + 1] && nums2[pos] < nums2[pos + 1]) {
            // non-change
            dfs(nums1, nums2, pos + 1, count);
        } else if (nums1[pos] < nums2[pos + 1] && nums2[pos] < nums1[pos + 1]) {
            // exchange
            dfs(nums1, nums2, pos + 1, count + 1);
        }
        // or illegal input, never happen
    }
}

// the old way, time limit exceed.
@Deprecated
class SolutionV0 {

    private int ret = Integer.MAX_VALUE;

    /**
     * dfs, TLE
     * @param nums1
     * @param nums2
     * @return
     */
    public int minSwap(int[] nums1, int[] nums2) {
        int lens = nums1.length;
        dfs(nums1, nums2, -1, -1, 0, 0);
        return ret;
    }

    public void dfs(int[] nums1, int[] nums2, int atail, int btail, int pos, int count) {
        if (pos == nums1.length) {
            ret = Math.min(ret, count);
            return ;
        }
        if (atail < nums1[pos] && btail < nums2[pos]) {
            // non-change
            dfs(nums1, nums2, nums1[pos], nums2[pos], pos + 1, count);
        }
        if (atail < nums2[pos] && btail < nums1[pos]) {
            // ex-change
            dfs(nums1, nums2, nums2[pos], nums1[pos], pos + 1, count + 1);
        }
    }
}
