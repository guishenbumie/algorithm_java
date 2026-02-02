package b.j_lru;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
    }

    public static class DoubleNode {
        public int key;
        public int value;
        public DoubleNode prev;
        public DoubleNode next;

        public DoubleNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class DoubleList {
        public DoubleNode head;
        public DoubleNode tail;

        public DoubleList() {
            head = null;
            tail = null;
        }

        public void add(DoubleNode node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        public void moveNodeToTail(DoubleNode node) {
            if (tail == node) {
                return;
            }
            if (head == node) {
                head = node.next;
                head.prev = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node.prev = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        public DoubleNode removeHead() {
            if (head == null) {
                return null;
            }
            DoubleNode node = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = node.next;
                node.next = null;
                head.prev = null;
            }
            return node;
        }
    }

    public static class LRUCache {
        private HashMap<Integer, DoubleNode> keyNodeMap;
        private DoubleList nodeList;
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            keyNodeMap = new HashMap<>();
            nodeList = new DoubleList();
        }

        public int get(int key) {
            if (keyNodeMap.containsKey(key)) {
                DoubleNode node = keyNodeMap.get(key);
                nodeList.moveNodeToTail(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (keyNodeMap.containsKey(key)) {
                DoubleNode node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else {
                if (keyNodeMap.size() == capacity) {
                    keyNodeMap.remove(nodeList.removeHead().key);
                }
                DoubleNode node = new DoubleNode(key, value);
                keyNodeMap.put(key, node);
                nodeList.add(node);
            }
        }
    }
}
