package cd_diguiqiantao;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
    }

    public static int calc(String str) {
        where = 0;
        return f(str.toCharArray(), 0);
    }

    public static int where;

    /**
     * 表达式求值
     * 另一题，字符串解码，也是类似的解法，只是)换成]，加减乘除换成字符串重复几次拼接
     * 还有一题，元素方程式，统计元素的数量，也是类似
     * <p>
     * s[i...]开始计算，遇到字符串 或者 ) 终止
     * 返回自己负责的这一段的计算结果并且更新游标where，上游函数继续计算
     *
     * @param s
     * @param i
     * @return
     */
    public static int f(char[] s, int i) {
        int cur = 0;
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        while (i < s.length && s[i] != ')') {
            if (s[i] >= '0' && s[i] <= '9') {//数字，更新cur的数值
                cur = cur * 10 + s[i++] - '0';
            } else if (s[i] != '(') {//符号
                push(nums, ops, cur, s[i++]);
                cur = 0;
            } else {//(，递归调用子过程的计算结果
                cur = f(s, i + 1);
                i = where + 1;
            }
        }
        push(nums, ops, cur, '+');
        where = i;
        return compute(nums, ops);
    }

    public static void push(ArrayList<Integer> nums, ArrayList<Character> ops, int cur, char op) {
        int n = nums.size();
        if (n == 0 || ops.get(n - 1) == '+' || ops.get(n - 1) == '-') {
            nums.add(cur);
            ops.add(op);
        } else {
            int topNum = nums.get(n - 1);
            char topOp = ops.get(n - 1);
            if (topOp == '*') {
                nums.set(n - 1, topNum * cur);
            } else {
                nums.set(n - 1, topNum / cur);
            }
            ops.set(n - 1, topOp);
        }
    }

    public static int compute(ArrayList<Integer> nums, ArrayList<Character> ops) {
        int n = nums.size();
        int res = nums.get(0);
        for (int i = 1; i < n; i++) {
            res += ops.get(i - 1) == '+' ? nums.get(i) : -nums.get(i);
        }
        return res;
    }
}
