package b.d_randomSelect_findKthLargest;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 无序数组中找到第k大的数，要求时间复杂度为O(n)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int findKthLargest(int[] arr, int k) {
        return randomizedSelect(arr, arr.length - k);
    }

    public static int randomizedSelect(int[] arr, int i) {
        int ans = 0;
        for (int l = 0, r = arr.length - 1; l <= r; ) {
            int x = arr[l + (int) (Math.random() * (r - l + 1))];
            partition2(arr, l, r, x);
            if (i < first) {
                r = first - 1;
            } else if (i > last) {
                l = last + 1;
            } else {
                ans = arr[i];
                break;
            }
        }
        return ans;
    }

    public static int first, last;

    /**
     * 划分数组，将<=x，==x放中间，>x的放右边，first和last更新为==x区域的左右边界
     * 想象成两个领域，<=x不断的往右边扩，>x的不断往左扩
     *
     * @param arr
     * @param l
     * @param r
     * @param x
     */
    public static void partition2(int[] arr, int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (arr[i] == x) {
                i++;
            } else if (arr[i] < x) {
                swap(arr, first++, i++);
            } else {
                swap(arr, i, last--);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
