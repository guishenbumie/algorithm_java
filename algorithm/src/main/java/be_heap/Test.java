package be_heap;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        var arr = new int[]{4, 0, 1, 2};
        heapInsert(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 大根堆的插入
     * arr[i] = x，x是新来的
     * 时间复杂度是O(log n)
     *
     * @param arr
     * @param i
     */
    public static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {//比自己的父亲大，和父亲交换位置，直到不能再篡位
            int temp = arr[i];
            arr[i] = arr[(i - 1) / 2];
            arr[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    /**
     * i位置数变小了，还需要维持大根堆
     * 向下调整大根堆
     * 时间复杂度是O(log n)
     *
     * @param arr
     * @param i
     * @param size
     */
    public static void heapify(int[] arr, int i, int size) {
        int l = i * 2 + 1;
        while (l < size) {//有左孩子
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;//找到最大的孩子
            best = arr[best] > arr[i] ? best : i;//新插入的数和他孩子选出最大的
            if (best == i) {
                break;
            } else {
                swap(arr, best, i);
                i = best;
                l = i * 2 + 1;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 从顶到底建大根堆，堆排序从小到大，时间复杂度是O(n * log n)
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {//先建立大根堆
            heapInsert(arr, i);
        }
        int size = n;
        while (size > 1) {//每次将大根堆的顶点（也就是大根堆的当时的最大值）和size交换位置，这个数就排好了它应该在的顺序的位置，重复
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    /**
     * 从底到顶建立大根堆，时间复杂度O(n * log n)
     *
     * @param arr
     */
    public static void heapSort2(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        int size = n;
        while (size > 1) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }
}
