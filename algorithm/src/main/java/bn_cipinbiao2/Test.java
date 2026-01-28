package bn_cipinbiao2;

import java.util.HashMap;
import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
    }

    public static class Bucket {
        public HashSet<String> set;
        public int cnt;
        public Bucket prev;
        public Bucket next;

        public Bucket(String s, int c) {
            set = new HashSet<>();
            set.add(s);
            cnt = c;
        }
    }

    /**
     * 获取词频表里最大出现的词频和最小出现的词频
     */
    public static class AllOne {
        Bucket head;
        Bucket tail;
        HashMap<String, Bucket> map;

        public AllOne() {
            head = new Bucket("head", 0);
            tail = new Bucket("tail", Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
            map = new HashMap<>();
        }

        private void insert(Bucket cur, Bucket pos) {
            cur.next.prev = pos;
            pos.next = cur.next;
            cur.next = pos;
            pos.prev = cur;
        }

        public void remove(Bucket cur) {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        public void inc(String key) {
            if (!map.containsKey(key)) {
                if (head.next.cnt == 1) {
                    map.put(key, head.next);
                    head.next.set.add(key);
                } else {
                    Bucket b = new Bucket(key, 1);
                    map.put(key, b);
                    insert(head, b);
                }
            } else {
                Bucket b = map.get(key);
                if (b.next.cnt == b.cnt + 1) {
                    map.put(key, b.next);
                    b.next.set.add(key);
                } else {
                    Bucket newB = new Bucket(key, b.cnt + 1);
                    map.put(key, newB);
                    insert(b, newB);
                }
                b.set.remove(key);
                if (b.set.isEmpty()) {
                    remove(b);
                }
            }
        }

        public void dec(String key) {
            Bucket bucket = map.get(key);
            if (bucket.cnt == 1) {
                map.remove(key);
            } else {
                if (bucket.prev.cnt == bucket.cnt - 1) {
                    map.put(key, bucket.prev);
                    bucket.prev.set.add(key);
                } else {
                    Bucket newBucket = new Bucket(key, bucket.cnt - 1);
                    map.put(key, newBucket);
                    insert(bucket.prev, newBucket);
                }
            }
            bucket.set.remove(key);
            if (bucket.set.isEmpty()) {
                remove(bucket);
            }
        }

        public String getMaxKey() {
            return tail.prev.set.iterator().next();
        }

        public String getMinKey() {
            return head.next.set.iterator().next();
        }
    }
}
