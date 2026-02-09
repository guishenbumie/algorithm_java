package e.b_twoPointer;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 给一个数组排序（划分），奇数放在i为奇数的位置，偶数放在i为偶数的位置
     */
    public static int[] sortArray4ParityII(int[] nums) {
        int n = nums.length;
        for (int odd = 1, even = 0; odd < n && even < n; ) {
            if ((nums[n - 1] & 1) == 1) {//最后一个位置是奇数
                swap(nums, odd, n - 1);
                odd += 2;
            } else {//偶数
                swap(nums, even, n - 1);
                even += 2;
            }
        }
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 给定一个包含n+1个整数的数组，数字都在[1,n]范围内，有一个数字是重复的，找到这个数
     * 用快慢指针求链表环的解法，把数组的i和num组成一个环，0->nums[0]->nums[nums[0]]->nums[nums[nums[0]]]...
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 接雨水问题
     *
     * @param nums
     * @return
     */
    public static int trap(int[] nums) {
        int l = 1, r = nums.length - 2;
        int lmax = nums[0];
        int rmax = nums[nums.length - 1];
        int ans = 0;
        while (l <= r) {
            if (lmax <= rmax) {
                ans += Math.max(0, lmax - nums[l]);
                lmax = Math.max(lmax, nums[l++]);
            } else {
                ans += Math.max(0, rmax - nums[r]);
                rmax = Math.max(rmax, nums[r--]);
            }
        }
        return ans;
    }

    /**
     * 救生艇问题，一组人的体重，救生艇最大只能装载limit的重量，最多只能装2个人，求最少派出的救生艇数量
     * 先排序，然后双向指针
     *
     * @param people
     * @param limit
     * @return
     */
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int l = 0, r = people.length - 1;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            if (sum > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * 盛最多水的容器
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int ans = 0;
        for (int l = 0, r = height.length - 1; l < r; ) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }

    /**
     * 一个未排序的数组，里面都是整数，找出没有出现的最小的正整数
     *
     * @param arr
     * @return
     */
    public static int firstMissingPositive(int[] arr) {
        //每次看l位置上的数字，看能不能扩容
        //[0..i]上都是连续的小到大的正整数
        //[r...]是垃圾区，不符合预期的，会使预期变差的都往这里放
        int l = 0, r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                swap(arr, l, --r);
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }
}
