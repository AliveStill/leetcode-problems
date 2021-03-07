package alivestill.Q456;

import java.util.*;

public class Q456 {

    public static Solution so = new Solution();

    public static void main(String[] args) {
        int[] array = new int[] {
                -1,3,2,0
        };
        System.out.println(so.find132pattern(array));
        array = new int[] {
                10,12,6,8,3,11
        };
        System.out.println(so.find132pattern(array));
    }
}

class SolutionV2 {
    /// @brief monotonic stack, find 321 pattern backwards
    /// FIXMED, INVALID algorithm
    public boolean find132pattern(int[] nums) {
        int lens = nums.length;
        if (lens < 3) {
            return false;
        }
        // elements in stack are in ascending order
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addLast(nums[lens - 1]);
        for (int i = lens - 2; i >= 0; -- i) {
            if (nums[i] > stack.peekLast()) {
                stack.addLast(nums[i]);
            } else if (nums[i] < stack.peekLast()) {
                int cnt = 0;
                while (!stack.isEmpty() && nums[i] < stack.peekLast()) {
                    stack.removeLast();
                    ++ cnt;
                }
                if (cnt >= 2) {
                    return true;
                }
                if (!stack.isEmpty() && stack.peekLast() != nums[i]) {
                    stack.addLast(nums[i]);
                }
            }
        }
        return false;
    }
}

class SolutionV3 {
    /// FIXMED, invalid algorithm
    /// @brief one(two) pass, O(n) time and O(1) extra space
    ///     find 231 pattern backwards, first 23 then 1
    /// @details first find a valid 23's position where nums[_2]
    ///     is as large as possible, then find position of 1 from
    ///     back to front, modify values of _2,_3 accordingly.
    ///     during the process, we may have some potential positions
    ///     for _2 such that nums[_2] is as large as possible and
    ///     there is also _3 ahead so that nums[_3] > nums[_2] holds.
    ///     Hint: we make nums[_2] as large as possible so that
    ///     the value range of _1, that is, (-infinite, nums[_1]) is
    ///     as large as possible
    public boolean find132pattern(int[] nums) {
        int lens = nums.length;
        if (lens < 3) {
            return false;
        }
        int[] array = new int[lens + 1];
        System.arraycopy(nums, 0, array, 0, lens);
        array[lens] = Integer.MAX_VALUE;
        nums = array;   // expand one unit as sentinel
        int _3, _2 = lens - 1;  // as is, pos of 3,2
        while (_2 != 1 && nums[_2] >= nums[_2 - 1]) {
            -- _2;
        }
        if (_2 == 1) {
            return false;
        }
        _3 = _2 - 1;
        int _2cp = _2;
        while (_2cp < lens && nums[_3] > nums[_2cp]) {
            if (nums[_2] < nums[_2cp]) {
                _2 = _2cp;
            }
            ++ _2cp;
        }
        int potential = lens;
        for (int i = _3 - 1; i >= 0; -- i) {
            if (nums[i] > nums[potential]) {
                _2 = potential;
                _3 = i;
                potential = lens;   // clear potential
            } else {
                if (nums[i] < nums[_2]) {
                    return true;
                } else if (nums[i] > nums[_2] && nums[i] < nums[_3]) {
                    // FIXME, invalid partially
                    potential = i;
                } else if (nums[i] > nums[_3]) {
                    _2 = _3;
                    _3 = i;
                }
            }
        }
        return false;
    }
}

class Solution {
    /// @brief O(n^3), brute force, TLE
    public boolean find132pattern(int[] nums) {
        int lens = nums.length;
        if (lens < 3) {
            return false;
        }
        Map<Integer, Set<Integer>> forward = new HashMap<>();
        Map<Integer, Set<Integer>> backward = new HashMap<>();
        for (int i = 0; i < lens; ++ i) {
            for (int j = i + 1; j < lens; ++ j) {
                if (nums[i] < nums[j]) {
                    if (forward.get(i) == null) {
                        Set<Integer> set = new HashSet<>();
                        forward.put(i, set);
                    }
                    forward.get(i).add(j);
                } else if (nums[i] > nums[j]) {
                    if (backward.get(j) == null) {
                        Set<Integer> set = new HashSet<>();
                        backward.put(j, set);
                    }
                    backward.get(j).add(i);
                }
            }
        }
        // O(n^3) in tuition, O(n^2) in fact
        for (int i = 0; i < lens; ++ i) {
            Set<Integer> set = forward.get(i);
            if (set != null) {
                Integer[] array = set.toArray(new Integer[0]);
                for (Integer j : array) {
                    Set<Integer> set2 = backward.get(j);
                    if (set2 != null) {
                        Integer[] array2 = set2.toArray(new Integer[0]);
                        for (Integer k : array2) {
                            if (k > i) {
                                return true;
                            }
                        }
                    }
                    backward.remove(j); // acceleration
                }
            }
        }
        return false;
    }
}
