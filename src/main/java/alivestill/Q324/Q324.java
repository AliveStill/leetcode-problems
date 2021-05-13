package alivestill.Q324;

import java.util.Arrays;

public class Q324 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[] array = new int[] {
                1,5,1,1,6,4
        };
        so.wiggleSort(array);
        for (int i : array) {
            System.out.print(i + ", ");
        }
    }
}

class Solution {
    /// @brief sort and reorder
    public void wiggleSort(int[] nums) {
        int lens = nums.length;
        if (lens == 1) {
            return ;
        }
        int[] array = new int[lens];
        System.arraycopy(nums, 0, array, 0, lens);
        Arrays.sort(array);
        int[] subArray1 = new int[(lens + 1) >> 1];
        int[] subArray2 = new int[lens - subArray1.length];
        System.arraycopy(array, 0, subArray1, 0, subArray1.length);
        System.arraycopy(array, subArray1.length, subArray2, 0, subArray2.length);
        for (int i = 0; i < subArray1.length; ++ i) {
            nums[i << 1] = subArray1[i];
            nums[(i << 1) + 1] = subArray2[i];
        }
        if ((lens & 1) == 1) {
            nums[lens - 1] = subArray1[subArray1.length - 1];
        }
    }
}
