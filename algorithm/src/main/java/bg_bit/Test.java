package bg_bit;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 一个数是否是2的幂
     * 1 2 4 8 16 32 ....
     * 肯定二进制里有1位是1，其他是0，找到最右侧的1，如果是他自己就是2的幂
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & -n);
    }

    /**
     * 返回大于等于n的最小的2某次方
     *
     * @param n
     * @return
     */
    public static int near2Power(int n) {
        if (n <= 0)
            return 1;
        //将二进制最左的1后面的位也都刷成1
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    /**
     * 区间[l,f]，求所有数的&和
     *
     * @param l
     * @param r
     * @return
     */
    public static int power2(int l, int r) {
        while (l < r) {
            r -= r & -r;
        }
        return r;
    }

    /**
     * 位图
     */
    public static class BitSet {
        public int[] set;

        //n个数
        public BitSet(int n) {
            set = new int[(n + 31) / 32];//a/b向上取整=(a-b+1)/b
        }

        public void add(int num) {
            set[num / 32] |= 1 << (num % 32);
        }

        public void remove(int num) {
            set[num / 32] &= ~(1 << (num % 32));
        }

        public void reverse(int num) {
            set[num / 32] ^= (1 << (num % 32));
        }

        public boolean contains(int num) {
            return ((set[num / 32] >> (num % 32) & 1)) == 1;
        }
    }
}
