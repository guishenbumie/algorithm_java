package c.f_tongyuyuanli;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    /**
     * 第n个神奇的数字，能被a或者b整除的数叫神奇的数字
     *
     * @param n
     * @param a
     * @param b
     * @return
     */
    public static int nthMagicNumber(int n, int a, int b) {
        long lcm = lcm(a, b);
        long ans = 0;
        for (long l = 0, r = (long) n * Math.min(a, b), m = 0; l <= r; ) {
            m = l + (r - l) / 2;
            if (m / a + m / b - m / lcm >= n) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) (ans % 100000007);//题目要求%一下
    }

    /**
     * 任意两个数相加的结果%mod = 他们分别%mod后相加的后再%mod
     *
     * @param a
     * @param b
     * @param mod
     * @return
     */
    public static int f1(long a, long b, int mod) {
        return (int) ((a % mod + b % mod) % mod);
    }

    /**
     * 任意两个数非负的数相减的差%mod = 他们分别%mod的和加上mod的和再%mod
     *
     * @param a
     * @param b
     * @param mod
     * @return
     */
    public static int f2(long a, long b, int mod) {
        return (int) ((a % mod - b % mod + mod) % mod);
    }

    /**
     * 乘法同加法
     *
     * @param a
     * @param b
     * @param mod
     * @return
     */
    public static int f3(long a, long b, int mod) {
        return (int) ((a % mod + b % mod) % mod);
    }
}
