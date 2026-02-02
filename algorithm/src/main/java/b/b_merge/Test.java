package b.b_merge;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 给定一个数组，求它的小和
     */
    public static long smallSum(int[] arr, int l, int r) {
        if (l == r)
            return 0;
        int m = l + (r - l) / 2;
        return smallSum(arr, l, m) + smallSum(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static long merge(int[] arr, int l, int m, int r) {
        long ans = 0;
        for (int j = m + 1, i = l, sum = 0; j <= r; j++) {
            while (i <= m && arr[i] <= arr[j]) {
                sum += arr[i++];
            }
            ans += sum;
        }
        //后面是正常的merge的排序
        int i = l;
        int a = l;
        int b = m + 1;
        int[] help = new int[r - l + 1];
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        //左右指针，必有一个还没遍历完，这俩while只有一个会执行
        while (a <= m) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        for (i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }

    /**
     * 统计翻转对
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int counts(int[] arr, int l, int r) {
        if (l == r)
            return 0;
        int m = l + (r - l) / 2;
        return counts(arr, l, m) + counts(arr, m + 1, r) + merge2(arr, l, m, r);
    }

    private static int merge2(int[] arr, int l, int m, int r) {
        int ans = 0;
        for (int i = l, j = m + 1; i <= m; i++) {
            while (j <= r && (long) arr[i] > (long) arr[j] * 2) {
                j++;
            }
            ans += j - m - 1;
        }
        //后面是正常的merge的排序
        int i = l;
        int a = l;
        int b = m + 1;
        int[] help = new int[r - l + 1];
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        //左右指针，必有一个还没遍历完，这俩while只有一个会执行
        while (a <= m) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        for (i = l; i <= r; i++) {
            arr[i] = help[i];
        }
        return ans;
    }
}
