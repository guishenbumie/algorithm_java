package e.c_binaryAnswer;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 爱吃香蕉的珂珂，获得最小的能够吃完香蕉的速度
     *
     * @param piles
     * @param h
     * @return
     */
    public static int minSpeed(int[] piles, int h) {
        int l = 1;//最小的速度
        int r = 0;//最大的速度
        for (int pile : piles) {
            r = Math.max(r, pile);
        }
        int ans = 0;
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (f(piles, m) <= h) {//达标，在左侧继续二分
                ans = m;
                r = m - 1;
            } else {//不达标，在右侧二分
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * 吃完香蕉的时间
     *
     * @param piles
     * @param speep
     * @return
     */
    public static long f(int[] piles, int speep) {
        long ans = 0;
        for (int pile : piles) {
            ans += (pile + speep - 1) / speep;
        }
        return ans;
    }

    /**
     * 给定一个非负整数数组，和一个整数k，将数组分成k个非空的连续子数组，使得这k个子数组各自和的最大值最小，返回这个最小的最大值
     * 画家问题，给k的画家，每个人只能画连续的画，怎么划分工作，花费的时间最小
     *
     * @param nums
     * @param k
     * @return
     */
    public static int splitArray(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long ans = 0;
        //[0,sum]上二分
        for (long l = 0, r = sum, m, need; l <= r; ) {
            m = l + (r - l) / 2;
            need = f2(nums, m);
            if (need <= k) {//达标，左侧继续二分
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) ans;
    }

    /**
     * 让数组的每个部分的累加和<=m，要划分为几个部分才够
     *
     * @param nums
     * @param m
     * @return
     */
    public static int f2(int[] nums, long m) {
        int parts = 0;
        int sum = 0;
        for (int num : nums) {
            if (num > m) {
                return Integer.MAX_VALUE;
            }
            if (sum + num > m) {
                parts++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return parts;
    }

    /**
     * 找出第k小的数对距离
     *
     * @param nums
     * @param k
     * @return
     */
    public static int smallestKPair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        //[0,最大-最小]，二分查找
        for (int l = 0, r = nums[n - 1] - nums[0], m, cnt; l <= r; ) {
            m = l + (r - l) / 2;
            cnt = f2(nums, m);//数字有几对
            if (cnt >= k) {//满足条件，往左缩小范围，继续找
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * nums中任意两数的差值<=limit，这样的数字有几对
     *
     * @param nums
     * @param limit
     * @return
     */
    public static int f3(int[] nums, int limit) {
        int cnt = 0;
        for (int l = 0, r = 0; l < nums.length; l++) {//nums是从小到大排好序的，用滑动窗口
            while (r + 1 < nums.length && nums[r + 1] - nums[l] <= limit) {
                r++;
            }
            cnt += r - 1;
        }
        return cnt;
    }

    /**
     * arr数组是服务员服务人的需要的时间，问第w+1个客人来的时候需要等待的时间（w是前面有多少个人）
     * 也可以用堆实现，但时间复杂度不如二分
     * @param arr
     * @param w
     * @return
     */
    public static int waitingTime(int[] arr, int w) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        int ans = 0;
        for (int l = 0, r = min * arr.length, m; l <= r; ) {
            m = l + (r - l) / 2;
            if (f4(arr, m) >= w + 1) {//满足条件
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * 在指定时间，能够给几个客人提供服务
     *
     * @param arr
     * @param time
     * @return
     */
    public static int f4(int[] arr, int time) {
        int cnt = 0;
        for (int num : arr) {
            cnt += (time / num) + 1;
        }
        return cnt;
    }
}
