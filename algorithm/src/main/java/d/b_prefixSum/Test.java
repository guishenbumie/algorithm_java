package d.b_prefixSum;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
    }

    public static int[] sum;

    public static void genSumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    /**
     * 获取数组任意范围内的和，时间复杂度是O(1)
     *
     * @param l
     * @param r
     * @return
     */
    public static int sumRange(int l, int r) {
        return sum[r + 1] - sum[l];
    }

    /**
     * 从数组中找到累加和为aim的最长子数组（子数组表示是连续的意思）
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int compute(int[] arr, int aim) {
        HashMap<Integer, Integer> map = new HashMap<>();//key=某个前缀和，value=这个前缀和最早出现的位置
        map.put(0, -1);
        int ans = 0;
        for (int i = 0, sum = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {//sum-aim的数值，已经出现过，说明当前i符合目标子数组，但不一定是最长的，记录长度，继续遍历
                ans = Math.max(ans, i - map.get(sum - aim));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }

    /**
     * 返回无序数组中，累加和为给定值aim的子数组的个数
     *
     * @param nums
     * @param aim
     * @return
     */
    public static int subArraySum(int[] nums, int aim) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - aim, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
