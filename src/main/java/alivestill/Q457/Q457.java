package alivestill.Q457;

import java.util.HashSet;
import java.util.Set;

public class Q457 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[] array = new int[] {2,-1,1,2,2};       // true
        System.out.println(so.circularArrayLoop(array));
        array = new int[] {-1, 2};      // false
        System.out.println(so.circularArrayLoop(array));
        array = new int[] {-2,1,-1,-2,-2};      // false
        System.out.println(so.circularArrayLoop(array));
        array = new int[]{-1, -2, -3, -4, -5};      // false
        System.out.println(so.circularArrayLoop(array));
        // {-2, -3, -9} bullshit testcase
        array = new int[]{-2, -3, -9};      // false
        System.out.println(so.circularArrayLoop(array));
    }
}

class SolutionV2 {
    /// @brief brute-force
    public boolean circularArrayLoop(int[] nums) {
        lens = nums.length;
        if (lens <= 1) {
            return false;
        }
        for (int i = 0; i < lens; ++ i) {
            int pos = i;
            Set<Integer> set = new HashSet<>();
            while (!set.contains(jump(pos, nums[pos]))
                    && !isProductNegative(nums[jump(pos, nums[pos])], nums[pos])) {
                set.add(pos);
                pos = jump(pos, nums[pos]);
            }
            // a self loop is not qualified
            if (set.contains(jump(pos, nums[pos])) && jump(pos, nums[pos]) != pos) {
                return true;
            }
        }
        return false;
    }

    private int lens;

    boolean isProductNegative(int a, int b) {
        return ((a ^ b) & 0x80000000) == 0x80000000;
    }

    int jump(int pos, int gap) {
        return ((pos + gap) % lens + lens) % lens;
    }
}

/// time: 0ms, beats 100.00% with acceleration
/// space: 36.6MB, beats 44.66%
class Solution {
    /// @brief two pointers, O(n) time and O(n) space
    public boolean circularArrayLoop(int[] nums) {
        lens = nums.length;
        if (lens <= 1) {
            return false;
        }
        // record[0] for forward record,
        // record[1] for backward record,
        // if there is no circle for point i, record[index][i] is true
        boolean[][] record = new boolean[2][lens];
        int index, slow, fast;
        for (int i = 0; i < lens; ++ i) {
            index = (nums[i] > 0) ? 0 : 1;
            if (record[index][i]) {
                continue;
            }
            record[index][i] = true;
            slow = fast = i;
            do {
                slow = jump(slow, nums[slow]);
                if (isProductNegative(nums[i], nums[slow])) {
                    break;
                }
                fast = jump(fast, nums[fast]);
                if (isProductNegative(nums[i], nums[fast])) {
                    break;
                }
                record[index][fast] = true;         // acceleration
                fast = jump(fast, nums[fast]);
                if (isProductNegative(nums[i], nums[fast])) {
                    break;
                }
                record[index][fast] = true;         // acceleration
            } while (slow != fast);
            if (slow == fast && jump(slow, nums[slow]) != slow) {
                return true;
            }
        }
        return false;
    }

    private int lens;

    boolean isProductNegative(int a, int b) {
        return ((a ^ b) & 0x80000000) == 0x80000000;
    }

    int jump(int pos, int gap) {
        return ((pos + gap) % lens + lens) % lens;
    }
}