package c.e_nQueue;

public class Test {
    public static void main(String[] args) {
        System.out.println(totalNQueens(3));
    }

    /**
     * 用数组实现N皇后问题（不推荐）
     * 性能更好是用位运算，有点难，看懂了大神的解法，但好奇最开始人家是怎么想出来的呢，有时间再学吧。。。
     *
     * @param n
     * @return
     */
    public static int totalNQueens(int n) {
        if (n < 1)
            return 0;
        return f(0, new int[n], n);
    }

    /**
     * @param i    当期来到的行数
     * @param path 0——i-1行的皇后都摆在了哪列
     * @param n    几皇后问题
     * @return 0——i-1行已经摆完了，i——n-1行可以去尝试的情况下还能找到几种有效方法
     */
    public static int f(int i, int[] path, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (check(path, i, j)) {
                path[i] = j;
                res += f(i + 1, path, n);
            }
        }
        return res;
    }

    /**
     * 当前在i行、j列摆了一个皇后，是否可以
     *
     * @param path
     * @param i
     * @param j
     * @return
     */
    public static boolean check(int[] path, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == path[k] || Math.abs(i - k) == Math.abs(j - path[k])) {
                return false;
            }
        }
        return true;
    }
}
