package alivestill.Q460;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;

public class Q460 {

    public static void main(String[] args) {
        // ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
        //  [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));   // 1
        cache.put(3, 3);                    // invalidate 2
        System.out.println(cache.get(2));   // -1
        System.out.println(cache.get(3));   // 3
        cache.put(4, 4);                    // invalidate 1
        System.out.println(cache.get(1));   // -1
        System.out.println(cache.get(3));   // 3
        System.out.println(cache.get(4));   // 4
    }

    @Test
    @DisplayName("test1")
    public void test1() {
        // Input
        //["LFUCache","put","put","get","get","put","get","get","get"]
        //[[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
        LFUCache cache = new LFUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        System.out.println(cache.get(3));   // 2
        System.out.println(cache.get(2));   // 1
        cache.put(4, 3);    // invalidate 3
        System.out.println(cache.get(2));   // 1
        System.out.println(cache.get(3));   // -1
        System.out.println(cache.get(4));   // 3
    }
}

/// Most of the code here is copied from Q432 class AllOne
class LFUCacheV1 {
    
    class Data implements Comparable{
        Integer key;
        Integer value;
        long timestamp;

        public Data(Integer key, Integer value, long timestamp) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof Data) && (this.hashCode() == ((Data)o).hashCode());
        }

        @Override
        public int compareTo(Object o) {
            return (int)(this.timestamp - ((Data)o).timestamp);
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }
    
    class ListNode {

        TreeSet<Data> set = new TreeSet<>();
        Long frequency;                 // frequency, also use count
        ListNode prev;
        ListNode next;

        public ListNode(Long frequency) {
            this.frequency = frequency;
            this.prev = null;
            this.next = null;
        }

        Data extractData(Integer key) {
            Data data = set.floor(new Data(key, 0, 0L));
            if (!key.equals(data.getKey())) {
                System.err.println("error in extractData()");
            }
            set.remove(data);
            return data;
        }

        void addEntry(Integer key, int value) {
            set.add(new Data(key, value, System.currentTimeMillis()));
        }

        void updateEntry(Integer key, int value) {
            addEntry(key, value);
        }

        Data getEntry(Integer key) {
            Data data = set.floor(new Data(key, 0, 0L));  // or ceiling()
            if (!key.equals(data.getKey())) {
                System.err.println("error in getEntry()");
            }
            return data;
        }

        // this is a fraud, use key as Data's value and value as it's timestamp
        // Because Java is lack of native class like Tuple
        Data removeLeastRecentlyVisitedEntry() {
            Data data = set.first();
            set.remove(data);
            return data;
        }

        int getSize() {
            return set.size();
        }

        boolean isEmpty() {
            return 0 == getSize();
        }

        public Long getFrequency() {
            return frequency;
        }

        public void setFrequency(Long frequency) {
            this.frequency = frequency;
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

    Map<Integer, ListNode> map = new HashMap<>();
    DoubleLinkedList list = new DoubleLinkedList();
    int capacity;
    int currentSize = 0;

    /** Initialize your data structure here. */
    public LFUCacheV1(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        ListNode node = map.get(key);
        if (node != null) {
            int value = node.getEntry(key).getValue();
            // remove it from node and put it into its left neighbor
            if (list.getPreviousNode(node).getFrequency() == node.getFrequency() + 1) {
                Data data = node.extractData(key);
                list.getPreviousNode(node).addEntry(key, data.getValue());
                map.put(key, list.getPreviousNode(node));
                if (node.isEmpty()) {
                    list.remove(node);
                }
            } else {
                // construct a new node whose frequency is node.getValue() + 1
                ListNode newNode = new ListNode(node.getFrequency() + 1);
                list.insertBefore(newNode, node);
                Data data = node.extractData(key);
                newNode.addEntry(key, data.getValue());
                map.put(key, newNode);
                if (node.isEmpty()) {
                    list.remove(node);
                }
            }
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return ;
        }
        ListNode node = map.get(key);
        // the node exists
        if (node != null) {
            node.updateEntry(key, value);
            get(key);   // increment operation
        } else {
            // if current size reaches limit
            if (currentSize == capacity) {
                // otherwise, find the node with frequency == 1
                node = list.getTail().getPrev();
                if (node.getFrequency() == 1) {
                    node.addEntry(key, value);
                    Data data = node.removeLeastRecentlyVisitedEntry();
                    map.remove(data.getValue());
                    map.put(key, node);
                } else {
                    Data data = list.getTail().getPrev().removeLeastRecentlyVisitedEntry();
                    map.remove(data.getValue());
                    if (list.getTail().getPrev().isEmpty()) {
                        list.remove(list.getTail().getPrev());
                    }
                    node = new ListNode(1L);
                    node.addEntry(key, value);
                    list.insertBefore(node, list.getTail());
                    map.put(key, node);
                }
            } else {
                ++ currentSize;
                node = list.getTail().getPrev();
                if (node.getFrequency() == 1) {
                    node.addEntry(key, value);
                    map.put(key, node);
                } else {
                    node = new ListNode(1L);
                    node.addEntry(key, value);
                    list.insertBefore(node, list.getTail());
                    map.put(key, node);
                }
            }
        }
    }

//    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
//    public void inc(String key) {
//        ListNode node = map.get(key);
//        if (node == null) {
//            // check if there is any node whose value is 1
//            if (list.getTail().getPrev().getValue() == 1) {
//                // if there is
//                map.put(key, list.getTail().getPrev());
//                list.getTail().getPrev().addKey(key);
//            } else {
//                // otherwise
//                ListNode newNode = new ListNode(1L);
//                newNode.addKey(key);
//                map.put(key, newNode);
//                list.insertBefore(newNode, list.getTail());
//            }
//        } else {
//            if (list.getPreviousNode(node).getValue() == node.getValue() + 1) {
//                node.extractKey(key);
//                list.getPreviousNode(node).addKey(key);
//                map.put(key, list.getPreviousNode(node));
//                if (node.isEmpty()) {
//                    list.remove(node);
//                }
//            } else {
//                // construct a new node whose value is node.getValue() + 1
//                ListNode newNode = new ListNode(node.getValue() + 1);
//                list.insertBefore(newNode, node);
//                node.extractKey(key);
//                newNode.addKey(key);
//                map.put(key, newNode);
//                if (node.isEmpty()) {
//                    list.remove(node);
//                }
//            }
//        }
//    }
//
//    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
//    public void dec(String key) {
//        ListNode node = map.get(key);
//        if (node != null) {
//            if (node.getValue() == 1) {
//                // size == 0, remove
//                map.remove(key);
//                node.extractKey(key);
//                if (node.isEmpty()) {
//                    list.remove(node);
//                }
//            } else {
//                // target node is already exist
//                if (node.getValue() - 1 == list.getNextNode(node).getValue()) {
//                    node.extractKey(key);
//                    list.getNextNode(node).addKey(key);
//                    map.put(key, list.getNextNode(node));
//                    if (node.isEmpty()) {
//                        list.remove(node);
//                    }
//                } else {
//                    // otherwise
//                    // construct a new node
//                    ListNode newNode = new ListNode(node.getValue() - 1);
//                    node.extractKey(key);
//                    newNode.addKey(key);
//                    map.put(key, newNode);
//                    list.insertAfter(newNode, node);
//                    if (node.isEmpty()) {
//                        list.remove(node);
//                    }
//                }
//            }
//        }
//    }
//
//    /** Returns one of the keys with maximal value. */
//    public String getMaxKey() {
//        // use iterator() slow down the efficiency of this func,
//        // however using TreeMap instead of HashMap in the ListNode class
//        // may slow down the extract process. Make Ends Meet.
//        return list.getSize() == 0L ? "" : list.getHead().getNext().getKeys().iterator().next();
//    }
//
//    /** Returns one of the keys with Minimal value. */
//    public String getMinKey() {
//        return list.getSize() == 0L ? "" : list.getTail().getPrev().getKeys().iterator().next();
//    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LFUCache {

    class ListNode {
        ListNode next;
        ListNode prev;
        int key;
        int value;
        long frequency;      // naturally ordered bases on frequency
        // FIXME, this field may not need in the process.
        long timestamp;

        public ListNode(long frequency) {
            this.frequency = frequency;
            key = value = 0;
            timestamp = System.currentTimeMillis();
            next = prev = null;
        }

        public ListNode(int key, int value, long frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            timestamp = System.currentTimeMillis();
            next = prev = null;
        }

        public void inc() {
            ++ frequency;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode getPrev() {
            return prev;
        }

        public void setPrev(ListNode prev) {
            this.prev = prev;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public long getFrequency() {
            return frequency;
        }

        public void setFrequency(long frequency) {
            this.frequency = frequency;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
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

    int capacity;
    int size = 0;
    Map<Integer, ListNode> map = new HashMap<>();
    DoubleLinkedList list = new DoubleLinkedList();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        ListNode node = map.get(key);
        if (node != null) {
            ListNode before = node.getPrev();
            node.inc();
            while (node.getFrequency() >= before.getFrequency()) {
                before = before.getPrev();
            }
            list.remove(node);
            list.insertAfter(node, before);
            return node.getValue();
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return ;
        }
        ListNode node = map.get(key);
        if (node == null) { // doesn't exist
            if (size == capacity) { // full, replace one
                node = list.remove(list.getTail().getPrev());
                map.remove(node.getKey());
            } else {
                ++ size;
            } // after merge
            node = new ListNode(key, value, 1L);
            ListNode before = list.getTail().getPrev();
            while (node.getFrequency() >= before.getFrequency()) {
                before = before.getPrev();
            }
            list.insertAfter(node, before);
            map.put(key, node);
        } else {    // exist, inc frequency and move forward
            node.setValue(value);
            ListNode before = node.getPrev();
            node.inc();
            while (node.getFrequency() >= before.getFrequency()) {
                before = before.getPrev();
            }
            list.remove(node);
            list.insertAfter(node, before);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
