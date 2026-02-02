package a.c_binarySearch;

public class BinarySearchTest {
    public static void main(String[] args) {
        //二分查找的时间复杂度是O(log n)
    }

    /**
     * 一个有序数组中，是否存在target
     *
     * @param arr
     * @param target
     * @return
     */
    public static boolean exist(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int l = 0, r = arr.length - 1, m;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] == target) {
                return true;
            } else if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    /**
     * 一个有序数组中，找>=target的最左的位置
     * <p>
     * L 中 R
     * 中 >= target，记答案，往左二分
     * 中 < target，不记答案，往右二分
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findLeft(int[] arr, int target) {
        int l = 0, r = arr.length - 1, m;
        int ans = -1;
        while (l <= r) {
//            m = (l + r) / 2;
//            m = l + (r - l) / 2;
            m = l + (r - l) >> 1;
            if (arr[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * 一个有序数组中，找<=target的最右的位置
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findRight(int[] arr, int target) {
        int l = 0, r = arr.length - 1, m;
        int ans = -1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] <= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * 峰值，找到一个峰值，可能存在多个，返回任意一个
     * 1.看首尾两个点，是不是峰值
     * 2.首尾不是，峰值肯定在中间某个位置，二分进行查找
     *
     * @param arr
     * @return
     */
    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        if (n == 1)
            return 0;
        if (arr[0] > arr[1])
            return 0;
        if (arr[n - 1] > arr[n - 2])
            return n - 1;
        int l = 1, r = n - 2;
        int m;
        int ans = -1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m - 1] > arr[m]) {//左边大，左边是下降的，峰值在左边
                r = m - 1;
            } else if (arr[m] < arr[m + 1]) {//右边大，右边是上升的，峰值在右边
                l = m + 1;
            } else {//左边右边都不比m大，峰值找到
                ans = m;
                break;
            }
        }
        return ans;
    }
}
