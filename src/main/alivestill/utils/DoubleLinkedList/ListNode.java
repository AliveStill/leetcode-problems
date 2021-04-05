package alivestill.utils.DoubleLinkedList;

public class ListNode<T> {
    T value;
    ListNode<T> prev;
    ListNode<T> next;

    public ListNode(T value) {
        this.value = value;
        prev = next = null;
    }

    public ListNode() {
        // value is not initialized
        prev = next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}
