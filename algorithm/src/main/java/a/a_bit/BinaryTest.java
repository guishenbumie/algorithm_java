package a.a_bit;

public class BinaryTest {
    public static void main(String[] args) {
        /**
         * 非负数：反码、补码都=原码
         * 负数：
         *      反码= 符号位不变，数值位按位取反
         *      补码= 反码 + 1（末位加 1，有进位则进位）
         * 计算机为了在运算中没有逻辑运算，全是加法运算，使性能快
         *
         * 十进制负数的二进制转化：先算正数的二进制，再-1，最后取反
         * 二进制负数的十进制转化：取反，再加1
         */

        //正数
        int a = 78;
        System.out.println(a);
        printBinary(a);
        System.out.println("===a===");

        //负数
        int b = -6;
        System.out.println(b);
        printBinary(b);
        System.out.println("===b===");

        //直接写二进制形式定义变量
        int c = 0b1001110;
        System.out.println(c);
        printBinary(c);
        System.out.println("===c===");

        //直接写十六进制形式定义变量
        int d = 0x4e;
        System.out.println(d);
        printBinary(d);
        System.out.println("===d===");

        //~ 相反数
        System.out.println(a);
        printBinary(a);
        printBinary(~a);
        int e = ~a + 1;
        System.out.println(e);
        printBinary(e);
        System.out.println("===e===");

        //int long的最小值，取相反数、绝对值，都是自己
        int f = Integer.MIN_VALUE;
        System.out.println(f);
        printBinary(f);
        System.out.println(-f);
        printBinary(-f);
        System.out.println(~f + 1);
        printBinary(~f + 1);
        System.out.println("===f===");

        // | & ^
        int g = 0b0001010;
        int h = 0b0001100;
        printBinary(g | h);//每一位只要有一个1，该位=1
        printBinary(g & h);//每一位都是1，该位=1
        printBinary(g ^ h);//每一位相同=0，不同=1
        System.out.println("===g h===");

        // <<
        int i = 0b0011010;
        printBinary(i);
        printBinary(i<<1);
        printBinary(i<<2);
        printBinary(i<<3);
        System.out.println("===i <<===");

        // 非负数 >> >>> 效果一样
        printBinary(i);
        printBinary(i>>2);//用符号位来补位
        printBinary(i>>>2);//用0来补位
        System.out.println("===i >> >>>===");

        // 负数 >> >>> 效果不一样
        int j = 0b11110000000000000000000000000000;
        printBinary(j);
        printBinary(j>>2);//用符号位来补位
        printBinary(j>>>2);//用0来补位
        System.out.println("===j >> >>>===");

        /**
         * 非负数：
         *      << 1：乘以2
         *      << 2：乘以4
         *      << 3：乘以8
         *      << n：乘以2的n次方
         *
         *      >> 1：除以2
         *      >> 2：除以4
         *      >> 3：除以8
         *      >> n：除以2的n次方
         * 只有非负数符合这个特征，负数不要用
         */
        int k = 3;
        System.out.println(k);
        System.out.println(k << 1);
        System.out.println(k << 2);
        System.out.println(k << 3);
        System.out.println("===k << 3===");
    }

    /**
     * 打印十进制在32位下的二进制数
     * 1 << i 表示第i位为1时的二进制数
     * a & (1 << i) 表示a在二进制下，第i位是否不是0
     * @param a
     */
    public static void printBinary(int a) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((a & (1 << i)) == 0 ? '0' : '1');
        }
        System.out.println();
    }
}
