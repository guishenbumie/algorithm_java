package e.a_slidingWindow;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 一个正数的数组，给定一个target，返回累加和大于等于target的最短子数组的长度（子数组就表示是在原数组中连续的一段）
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {//满足>=累加和的条件，尝试缩短左边界
                sum -= nums[l++];
            }
            if (sum >= target) {//更新答案
                ans = Math.min(ans, r - l + 1);
            }
            //继续往右扩右边界，尝试寻找答案
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 最长无重复字符的最长字串
     *
     * @param str
     * @return
     */
    public static int lengthOfLongestSubString(String str) {
        char[] chars = str.toCharArray();
        int[] lastIdx = new int[256];//记录上次字符出现的位置，初始为-1，拿map做也行
        Arrays.fill(lastIdx, -1);
        int ans = Integer.MIN_VALUE;
        for (int l = 0, r = 0; r < chars.length; r++) {
            l = Math.max(l, lastIdx[chars[r]] + 1);//lastIdx[chars[r]] + 1 > 0，说明之前这个右扩后的这个字符之前出现过，左边界要更新到新的值；用max就是为了排除下0的干扰
            ans = Math.max(ans, r - l + 1);//答案更新
            lastIdx[chars[r]] = r;//更新这个字符，最新出现的位置
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    /**
     * 最小覆盖字串，返回str中覆盖target的所有字符的最小字串
     *
     * @param str
     * @param target
     * @return
     */
    public static String minWindow(String str, String target) {
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int[] cnts = new int[256];//债务表，每种字符的负债情况，负数为负债，正数为盈余
        for (char c : t) {
            cnts[c]--;
        }
        int debt = t.length;
        int start = 0;
        int len = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < s.length; r++) {
            if (cnts[s[r]]++ < 0) {//窗口右滑，r为新入窗口的值，入窗口的字符s[r]原来在债务表中为负债情况，负债缩小1，r++
                debt--;
            }
            if (debt == 0) {//已经不欠债了，开始收缩左边界
                while (cnts[s[l]] > 0) {
                    cnts[s[l]]--;//更新债务表
                    l++;//收缩左边界
                }
                if (r - l + 1 < len) {//窗口长度更小，更新答案
                    len = r - l + 1;
                    start = l;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : str.substring(start, start + len);
    }

    /**
     * 加油站的良好出发点，找到一个出发点，可以跑一圈
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int l = 0, r = 0, sum; l < n; l = r + 1, r = l) {
            sum = 0;
            while (sum + gas[r % n] - cost[r % n] >= 0) {//新的这个加油站，加入后可以正常跑到，算到sum里来，右边界移动
                if (r - l + 1 == n) {//已经跑完了
                    return l;
                }
                sum += gas[r % n] - cost[r % n];
                r++;
            }
        }
        return -1;
    }

    /**
     * 替换子串得到平衡字符串，只有QWER的字符串，各个字符的数量都相等时是平衡字符串，给定一个字符串，替换里面的字串（连续的）成为平衡字符串
     *
     * @param str
     * @return
     */
    public static int balancedString(String str) {
        int n = str.length();
        int[] s = new int[n];
        int[] cnts = new int[4];
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            s[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));//把QWER转换为0123
            cnts[s[i]]++;//暂时先算下目前的值，后面会更新
        }
        //Q 0 4个   0
        //W 1 12个  -2
        //E 2 14个  -4
        //R 3 10个  0
        int debt = 0;
        for (int i = 0; i < 4; i++) {
            if (cnts[i] < n / 4) {
                cnts[i] = 0;
            } else {
                cnts[i] = n / 4 - cnts[i];
                debt += cnts[i];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < n; r++) {
            if (cnts[s[r]]++ < 0) {
                debt--;
            }
            if (debt == 0) {
                while (cnts[s[l]] > 0) {
                    cnts[s[l]]--;
                    l++;
                }
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    /**
     * 返回数组中，数字种类==k的子数组的数量
     *
     * @param arr
     * @param k
     * @return
     */
    public static int subArraysWithDistinct(int[] arr, int k) {
        return numsOfMostKinds(arr, k) - numsOfMostKinds(arr, k - 1);
    }

    public static int MAXN = 20001;

    /**
     * 返回数组中，数字种类不超过k的子数组的数量
     *
     * @param arr
     * @param k
     * @return
     */
    public static int numsOfMostKinds(int[] arr, int k) {
        int[] cnts = new int[MAXN];
        Arrays.fill(cnts, 1, arr.length + 1, 0);
        int ans = 0;
        for (int l = 0, r = 0, collect = 0; r < arr.length; r++) {
            if (++cnts[arr[r]] == 1) {//新的一种字符
                collect++;
            }
            while (collect > k) {//收缩窗口
                if (--cnts[arr[l++]] == 0) {
                    collect--;
                }
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
