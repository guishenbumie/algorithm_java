package ab_sort;

public class BubbleSortTest {
    public static void main(String[] args) {

    }

    /**
     * 冒泡排序：0~i范围上，相邻位置较大的数滚下去，最大值最终来到i位置，然后0~i-1范围继续
     * <p>
     * 5 3 4 1 2 => 3 5 4 1 2 => 3 4 5 1 2 => 3 4 1 5 2 => 3 4 1 2 5
     * 3 4 1 2 => 3 1 4 2 => 3 1 2 4
     * 3 1 2 => 1 3 2 => 1 2 3
     * 1 2 3 4 5
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }
}
