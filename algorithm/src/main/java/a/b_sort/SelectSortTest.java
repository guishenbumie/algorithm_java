package a.b_sort;

public class SelectSortTest {
    public static void main(String[] args) {

    }

    /**
     * 选择排序：i~n-1范围上，找到最小值放到i位置上，然后i+1~n-1范围上继续找
     * 0-n 找到最小的数，和0位置上原本的数交换位置
     * 1-n 找到最小的数，和1位置上原本的数交换位置
     * ...
     * <p>
     * <p>
     * 5 3 1 2 4 (2位置数1最小，5和1交换位置)
     * 1 3 5 2 4 (3位置数2最小，3和2交换位置)
     * 1 2 5 3 4 (3位置数3最小，5和3交换位置)
     * 1 2 3 5 4 (4位置数4最小，5和4交换位置)
     * 1 2 3 4 5
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int minIdx;
        for (int i = 0; i < arr.length - 1; i++) {
            minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
