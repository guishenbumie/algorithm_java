package bh_weiyunsuan;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 位运算实现加法
     * 无进位相加 + 进位信息
     * 加完后有新的进位，继续重复
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int ans = 0;
        while (b != 0) {
            ans = a ^ b;//无进位相加的和
            b = (a & b) << 1;//进位信息
            a = ans;
        }
        return ans;
    }

    /**
     * a - b = a + (-b)
     *
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {
        return add(a, neg(b));
    }

    /**
     * -b = ~b + 1
     *
     * @param n
     * @return
     */
    public static int neg(int n) {
        return add(~n, 1);
    }

    /**
     * b所有非0的位，就是a每个向左移位多少位，都加起来
     *
     * @param a
     * @param b
     * @return
     */
    public static int multiply(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = add(ans, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return ans;
    }

    public static int div(int a, int b) {
        int x = a < 0 ? neg(a) : a;
        int y = b < 0 ? neg(b) : b;
        int ans = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                ans |= 1 << i;
                x = minus(x, y << i);
            }
        }
        return a < 0 ^ b < 0 ? neg(ans) : ans;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {//a、b都是整型最小
            return 1;
        }
        if (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE) {//a、b都不是整型最小，正常除
            return div(a, b);
        }
        if (b == Integer.MIN_VALUE) {//b是整型最小
            return 0;
        }
        if (b == neg(1)) {//a是整型最小，b是-1，返回整型最大（题目中说的）
            return Integer.MAX_VALUE;
        }
        a = add(a, b > 0 ? b : neg(b));
        int ans = div(a, b);
        int offset = b > 0 ? neg(1) : 1;
        return add(ans, offset);
    }
}
