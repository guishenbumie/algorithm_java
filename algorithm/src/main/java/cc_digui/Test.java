package cc_digui;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        genPermutation2("abc");
    }

    /**
     * 生成字符串的所有子序列（序列是不能颠倒顺序的）
     *
     * @param str
     * @return
     */
    public static String[] genPermutation1(String str) {
        char[] s = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        f1(s, 0, new StringBuilder(), set);
        int m = set.size();
        String[] res = new String[m];
        int i = 0;
        for (String cur : set) {
            res[i++] = cur;
        }
        return res;
    }

    public static void f1(char[] s, int i, StringBuilder path, HashSet<String> set) {
        if (i == s.length) {
            set.add(path.toString());
        } else {
            //节点在路径中
            path.append(s[i]);
            f1(s, i + 1, path, set);
            //节点不在路径中
            path.deleteCharAt(path.length() - 1);
            f1(s, i + 1, path, set);
        }
    }

    public static String[] genPermutation2(String str) {
        char[] s = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        f2(s, 0, new char[s.length], 0, set);
        int m = set.size();
        String[] res = new String[m];
        int i = 0;
        for (String cur : set) {
            res[i++] = cur;
        }
        return res;
    }

    //size是往path中填进了几个数
    public static void f2(char[] s, int i, char[] path, int size, HashSet<String> set) {
        if (i == s.length) {
            set.add(String.valueOf(path, 0, size));
        } else {
            path[size] = s[i];
            f2(s, i + 1, path, size + 1, set);
            f2(s, i + 1, path, size, set);
        }
    }

    /**
     * 数组的全部子集组合
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subSetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        f3(nums, 0, new int[nums.length], 0, res);
        return res;
    }

    //size是path的数量
    public static void f3(int[] nums, int i, int[] path, int size, List<List<Integer>> res) {
        if (i == nums.length) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                cur.add(path[j]);
            }
            res.add(cur);
        } else {
            //j是下一组新数字的起始位置，2 2 2 2 3，i是第一个2的位置0，j就是第一个3的位置4
            int j = i + 1;
            while (j < nums.length && nums[j] == nums[i]) {
                j++;
            }

            //上面例子中，要0个2
            f3(nums, j, path, size, res);
            for (; i < j; i++) {//上面例子中，依次要1、2、3、4个2
                path[size++] = nums[i];
                f3(nums, j, path, size, res);
            }
        }
    }

    /**
     * 数组没有重复的值，返回数组的全部排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        f4(nums, 0, res);
        return res;
    }

    public static void f4(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            res.add(cur);
        } else {
            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);//交换两个数的位置
                f4(nums, i + 1, res);//从下一个位置继续递归
                swap(nums, i, j);//要换回两个数的位置
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 数组有重复的值，返回全部的排列，要去重
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        f5(nums, 0, res);
        return res;
    }

    public static void f5(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            res.add(cur);
        } else {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                if (!set.contains(nums[j])) {
                    set.add(nums[j]);
                    swap(nums, i, j);
                    f5(nums, i + 1, res);
                    swap(nums, i, j);
                }
            }
        }
    }

    /**
     * 用递归逆序一个栈
     *
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = bottomOut(stack);
        reverse(stack);
        stack.push(last);
    }

    /**
     * 移除栈底的元素，并返回这个值，其他的栈内的元素不变顺序压下来
     *
     * @param stack
     * @return
     */
    public static int bottomOut(Stack<Integer> stack) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        } else {
            int last = bottomOut(stack);
            stack.push(top);
            return last;
        }
    }

    /**
     * 用递归排序一个栈
     *
     * @param stack
     */
    public static void sort(Stack<Integer> stack) {
        int deep = deep(stack);
        while (deep > 0) {
            int max = max(stack, deep);
            int times = times(stack, deep, max);
            down(stack, deep, max, times);
            deep -= times;
        }
    }

    /**
     * 不改变栈的数据情况下，返回栈的深度
     *
     * @param stack
     * @return
     */
    public static int deep(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        int top = stack.pop();
        int deep = deep(stack) + 1;
        stack.push(top);
        return deep;
    }

    /**
     * 获取deep层内的最大值
     *
     * @param stack
     * @param deep
     * @return
     */
    public static int max(Stack<Integer> stack, int deep) {
        if (deep == 0)
            return Integer.MIN_VALUE;
        int top = stack.pop();
        int max = max(stack, deep - 1);
        stack.push(top);
        return Math.max(top, max);
    }

    /**
     * 不改变栈的数据的情况下，获取从栈顶开始往下数deep层已知最大的数max出现的次数
     *
     * @param stack
     * @param deep
     * @param max
     * @return
     */
    public static int times(Stack<Integer> stack, int deep, int max) {
        if (deep == 0)
            return 0;
        int top = stack.pop();
        int times = times(stack, deep - 1, max);
        stack.push(top);
        return times + top == max ? 1 : 0;
    }

    /**
     * 从栈顶开始往下数deep层已知最大的数max出现的times次，把max沉底，其他的数值相对次序不变
     *
     * @param stack
     * @param deep
     * @param max
     * @param times
     */
    public static void down(Stack<Integer> stack, int deep, int max, int times) {
        if (deep == 0) {
            for (int i = 0; i < times; i++) {
                stack.push(deep);
            }
        } else {
            int top = stack.pop();
            down(stack, deep - 1, max, times);
            if (top != max) {
                stack.push(top);
            }
        }
    }

    /**
     * 汉诺塔问题
     *
     * @param n
     */
    public static void hanoi(int n) {
        if (n > 0) {
            f(n, "left", "right", "middle");
        }
    }

    public static void f(int i, String from, String to, String other) {
        if (i == 1) {
            System.out.println("move 1 from " + from + " to" + to);
        } else {
            f(i - 1, from, other, to);
            System.out.println("move " + i + " from " + from + " " + to);
            f(i - 1, other, to, from);
        }
    }
}
