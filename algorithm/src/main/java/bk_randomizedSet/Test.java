package bk_randomizedSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 插入、删除、获取随机元素的时间复杂度都是O(1)（不允许有重复数字）
     */
    public static class RandomizedSet {
        public HashMap<Integer, Integer> map;
        public ArrayList<Integer> arr;

        public RandomizedSet() {
            map = new HashMap<>();
            arr = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int index = map.get(val);
            int endValue = arr.get(arr.size() - 1);
            map.put(endValue, index);
            arr.set(index, endValue);
            map.remove(val);
            arr.remove(arr.size() - 1);
            return true;
        }

        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }

    /**
     * 插入、删除、获取随机元素的时间复杂度都是O(1)（允许有重复数字）
     */
    public static class RandomizedSet2 {
        public HashMap<Integer, HashSet<Integer>> map;
        public ArrayList<Integer> arr;

        public RandomizedSet2() {
            map = new HashMap<>();
            arr = new ArrayList<>();
        }

        public boolean insert(int val) {
            arr.add(val);
            HashSet<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(arr.size() - 1);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            HashSet<Integer> valSet = map.get(val);
            int valAnyIndex = valSet.iterator().next();
            int endValue = arr.get(arr.size() - 1);
            if (val == endValue) {
                valSet.remove(arr.size() - 1);
            } else {
                HashSet<Integer> endValueSet = map.get(endValue);
                endValueSet.add(valAnyIndex);
                arr.set(valAnyIndex, endValue);
                endValueSet.remove(arr.size() - 1);
                valSet.remove(valAnyIndex);
            }
            arr.remove(arr.size() - 1);
            if (valSet.isEmpty()) {
                map.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }
}
