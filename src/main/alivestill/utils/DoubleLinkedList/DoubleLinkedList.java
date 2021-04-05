package alivestill.utils.DoubleLinkedList;


public class DoubleLinkedList<T> {

    long size = 0;
    ListNode<T> head = new  ListNode<T>();  // head sentinel
    ListNode<T> tail = new  ListNode<T>();  // tail sentinel
    {
        head.next = tail;
        tail.prev = head;
    }
    public  ListNode<T> getHead() {
        return head;
    }

    public  ListNode<T> getTail() {
        return tail;
    }

    public long getSize() {
        return size;
    }

    public ListNode<T> remove(ListNode<T> node) {
        -- size;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
    /// @brief insert (target) after (node)
    public void insertAfter( ListNode<T> target,  ListNode<T> node) {
        ++ size;
        target.next = node.next;
        node.next.prev = target;
        node.next = target;
        target.prev = node;
    }
    /// @brief insert (target) before (node)
    public void insertBefore( ListNode<T> target,  ListNode<T> node) {
        ++ size;
        target.prev = node.prev;
        node.prev.next = target;
        node.prev = target;
        target.next = node;
    }

    public ListNode<T> getPreviousNode( ListNode<T> node) {
        return node.getPrev();
    }
    public ListNode<T> getNextNode( ListNode<T> node) {
        return node.getNext();
    }
}
