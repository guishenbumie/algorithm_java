package a.b_sort;

public class InsertSortTest {
    public static void main(String[] args) {

    }

    /**
     * 插入排序：0~i范围上已经有序，新来的数从右到左滑到不再小的位置上插入，然后继续
     * <p>
     * 5 3 4 1 2
     * <p>
     * 5
     * 5 3 => 3 5
     * 3 5 4 => 3 4 5
     * 3 4 5 1 => 3 4 1 5 => 3 1 4 5 => 1 3 4 5
     * 1 3 4 5 2 => 1 3 4 2 5 => 1 3 2 4 5 => 1 2 3 4 5
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
