package b.c_quickSort;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 随机快排
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        int m = partition(arr, l, r, x);
        quickSort(arr, l, m - 1);
        quickSort(arr, m + 1, r);
    }

    /**
     * 划分数组，将<=x，>x的放右边，并确保划分完成的<=x的区域的最后一个数字是x
     * 想象<=x是一个领域，不断的往右边扩，将符合<=x条件的数字都搞进来
     *
     * @param arr
     * @param l
     * @param r
     * @param x
     * @return
     */
    public static int partition(int[] arr, int l, int r, int x) {
        int a = l, xi = 0;//xi记录x的位置，最后将l....a-1的最后一个位置a-1和x交换
        for (int i = l; i <= r; i++) {
            if (arr[i] <= x) {
                swap(arr, a, i);
                if (arr[a] == x) {
                    xi = a;
                }
                a++;
            }
            //arr[i] > x 不需要动
        }
        swap(arr, xi, a - 1);
        return a - 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //荷兰国旗问题
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

    /**
     * 快排改进版，时间复杂度是O(n * log n)
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r - l + 1))];
        partition2(arr, l, r, x);
        int left = first;
        int right = last;
        quickSort2(arr, l, left - 1);
        quickSort2(arr, right + 1, r);
    }
}
