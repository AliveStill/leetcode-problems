package alivestill.Q432;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q432 {

    static AllOne ao = new AllOne();

    public static void main(String[] args) {
        ao.inc("hello");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
    }

    @Test
    @DisplayName("test1")
    public void test1() {
        ao = new AllOne();
        ao.inc("a"); ao.inc("b"); ao.inc("c"); ao.inc("d");
        ao.inc("a"); ao.inc("b"); ao.inc("c"); ao.inc("d");
        ao.inc("c"); ao.inc("d"); ao.inc("d"); ao.inc("a");
        // a:3 b:2 c:3 d:4
        System.out.println(ao.getMinKey());
        System.out.println(ao.getMaxKey());
    }

    @Test
    @DisplayName("test2")
    public void test2() {
        ao = new AllOne();
        ao.inc("hello");
        ao.inc("hello");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
        ao.inc("leet");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
    }
}

class AllOne {

    class ListNode {
        Set<String> keys;   // keys
        Long value;         // frequency
        ListNode prev;
        ListNode next;

        public ListNode(Set<String> keys, Long value, ListNode prev, ListNode next) {
            this.keys = keys;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public ListNode(Long value) {
            this.keys = new HashSet<>();
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        String extractKey(String key) {
            keys.remove(key);
            return key;
        }

        void addKey(String key) {
            keys.add(key);
        }

        /// @brief for test usage only
        String getAnyKey() {
            String[] array = keys.toArray(new String[0]);
            return array.length == 0 ? "empty" : array[0];
        }

        int getSize() {
            return keys.size();
        }

        boolean isEmpty() {
            return 0 == getSize();
        }

        public Set<String> getKeys() {
            return keys;
        }

        public void setKeys(Set<String> keys) {
            this.keys = keys;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        public ListNode getPrev() {
            return prev;
        }

        public void setPrev(ListNode prev) {
            this.prev = prev;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    class DoubleLinkedList {
        public ListNode getHead() {
            return head;
        }

        public ListNode getTail() {
            return tail;
        }

        public long getSize() {
            return size;
        }

        long size = 0;
        ListNode head = new ListNode(Long.MAX_VALUE);   // head sentinel
        ListNode tail = new ListNode(-1L);              // tail sentinel
        {
            head.next = tail;
            tail.prev = head;
        }
        ListNode remove(ListNode node) {
            -- size;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
        /// @brief insert (target) after (node)
        void insertAfter(ListNode target, ListNode node) {
            ++ size;
            target.next = node.next;
            node.next.prev = target;
            node.next = target;
            target.prev = node;
        }
        /// @brief insert (target) before (node)
        void insertBefore(ListNode target, ListNode node) {
            ++ size;
            target.prev = node.prev;
            node.prev.next = target;
            node.prev = target;
            target.next = node;
        }
        ListNode getPreviousNode(ListNode node) {
            return node.getPrev();
        }
        ListNode getNextNode(ListNode node) {
            return node.getNext();
        }
    }

    Map<String, ListNode> map = new HashMap<>();
    DoubleLinkedList list = new DoubleLinkedList();

    /** Initialize your data structure here. */
    public AllOne() {

    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        ListNode node = map.get(key);
        if (node == null) {
            // check if there is any node whose value is 1
            if (list.getTail().getPrev().getValue() == 1) {
                // if there is
                map.put(key, list.getTail().getPrev());
                list.getTail().getPrev().addKey(key);
            } else {
                // otherwise
                ListNode newNode = new ListNode(1L);
                newNode.addKey(key);
                map.put(key, newNode);
                list.insertBefore(newNode, list.getTail());
            }
        } else {
            if (list.getPreviousNode(node).getValue() == node.getValue() + 1) {
                node.extractKey(key);
                list.getPreviousNode(node).addKey(key);
                map.put(key, list.getPreviousNode(node));
                if (node.isEmpty()) {
                    list.remove(node);
                }
            } else {
                // construct a new node whose value is node.getValue() + 1
                ListNode newNode = new ListNode(node.getValue() + 1);
                list.insertBefore(newNode, node);
                node.extractKey(key);
                newNode.addKey(key);
                map.put(key, newNode);
                if (node.isEmpty()) {
                    list.remove(node);
                }
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        ListNode node = map.get(key);
        if (node != null) {
            if (node.getValue() == 1) {
                // size == 0, remove
                map.remove(key);
                node.extractKey(key);
                if (node.isEmpty()) {
                    list.remove(node);
                }
            } else {
                // target node is already exist
                if (node.getValue() - 1 == list.getNextNode(node).getValue()) {
                    node.extractKey(key);
                    list.getNextNode(node).addKey(key);
                    map.put(key, list.getNextNode(node));
                    if (node.isEmpty()) {
                        list.remove(node);
                    }
                } else {
                    // otherwise
                    // construct a new node
                    ListNode newNode = new ListNode(node.getValue() - 1);
                    node.extractKey(key);
                    newNode.addKey(key);
                    map.put(key, newNode);
                    list.insertAfter(newNode, node);
                    if (node.isEmpty()) {
                        list.remove(node);
                    }
                }
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        // use iterator() slow down the efficiency of this func,
        // however using TreeMap instead of HashMap in the ListNode class
        // may slow down the extract process. Make Ends Meet.
        return list.getSize() == 0L ? "" : list.getHead().getNext().getKeys().iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return list.getSize() == 0L ? "" : list.getTail().getPrev().getKeys().iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
