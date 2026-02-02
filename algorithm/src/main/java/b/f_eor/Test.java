package b.f_eor;

public class Test {
    //异或运算，就是无进位相加，满足结合律和交换律，n^n=0，n^0=n
    public static void main(String[] args) {
        //交换两个数
        int a = 3;
        int b = 7;
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
    }

    /**
     * 找到缺失的数字，一个数组大小是n，包含0~n的数字，不重复，只缺少一个
     * 0~n的数字^和 ^ 数组的^和 ，就是缺失的数字
     * @param arr
     * @return
     */
    public static int missingNum(int[] arr) {
        int eorAll = 0, eorHas = 0;
        for (int i = 0; i < arr.length; i++) {
            eorAll ^= i;
            eorHas ^= arr[i];
        }
        eorAll ^= arr.length;
        return eorAll ^ eorHas;
    }

    /**
     * 一个数，出现奇数次，其他的数都是出现偶数次，找到这个数
     * @param arr
     * @return
     */
    public static int singleNum(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        return eor;
    }

    /**
     * 两个数出现奇数次，其他都是出现偶数次
     * @param arr
     * @return
     */
    public static int[] singleNum2(int[] arr) {
        int eor1 = 0;
        for (int num : arr) {
            eor1 ^= num;
        }
        //eor1 就是 a ^ b
        //使用Brian Kernighan算法，提取出二进制里最右侧的1
        int rightOne = eor1 & (-eor1);
        int eor2 = 0;
        for (int num : arr) {
            if ((num & rightOne) == 0) {//二进制某位是1的有一批数，比如包含a，那另一批某位是0的包含b
                eor2 ^= num;
            }
        }
        return new int[]{eor2, eor1 ^ eor2};
    }

    /**
     * 数组中只有1种数出现次数小于m，其他数都出现m次
     * cnts[0]：0位上有多少个1
     * cnts[i]：i位上有多少个1
     * cnts[31]：31位上有多少个1
     * @param arr
     * @param m
     * @return
     */
    public static int find(int[] arr, int m) {
        int[] cnts = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                cnts[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (cnts[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
