package alivestill.Q725;

public class Q725 {
}


//Definition for singly-linked list.
  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class Solution {
    /**
     * niave
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        int size = 0;
        ListNode fake = root;
        while (fake != null) {
            fake = fake.next;
            ++ size;
        }
        int share = size / k;
        int remain = size % k;
        ListNode[] list = new ListNode[k];
        fake = root;
        for (int i = 0; i < k; ++ i) {
            ListNode head = null, node = null;
            if (remain != 0) {
                head = node = fake;
                fake = fake.next;
                -- remain;
            }
            int share_cp = share;
            while (share_cp != 0) {
                -- share_cp;
                if (head != null) {
                    node = fake;
                } else {
                    head = node = fake;
                }
                fake = fake.next;
            }
            if (node != null) {
                node.next = null;
            }
            list[i] = head;
        }
        return list;
    }
}
