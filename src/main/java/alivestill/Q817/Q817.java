package alivestill.Q817;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Q817 {
}

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


class Solution {
    /**
     * naive
     * @param head
     * @param nums
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        ListNode fast = head;
        int ret = 0;
        while (fast != null) {
            if (!set.contains(fast.val)) {
                fast = fast.next;
            } else {
                while (fast != null && set.contains(fast.val)) {
                    fast = fast.next;
                }
                ++ ret;
            }
        }
        return ret;
    }
}
