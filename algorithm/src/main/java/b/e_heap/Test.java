package b.e_heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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

    public static class LinkNode {
        public LinkNode next;
        public int val;

        public LinkNode(LinkNode next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    /**
     * 合并n个有序的链表
     *
     * @param arr
     * @return
     */
    public static LinkNode mergeKLinks(ArrayList<LinkNode> arr) {
        //小根堆
        PriorityQueue<LinkNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (LinkNode h : arr) {//遍历所有的头，只把头放到小根堆中
            if (h != null) {
                heap.add(h);
            }
        }
        if (heap.isEmpty())
            return null;
        LinkNode head = heap.poll();//先弹出第一个节点，作为结果的头
        LinkNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        //依次弹出heap此时的头节点，结果链表的pre节点的下一个指向这个头节点，完成后，pre往后走一个，并把刚才的头节点的下一个节点放到堆中
        while (!heap.isEmpty()) {
            LinkNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }

    /**
     * 求n条线段的最大公共
     *
     * @param line
     * @return
     */
    public static int compute(int[][] line) {
        int n = line.length;
        //先将n条线段按照头从小到大排序，尾先不管
        Arrays.sort(line, 0, n, (a, b) -> a[0] - b[0]);
        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();//默认就是小根堆
        for (int i = 0; i < n; i++) {
            //i : line[i][0] line[i][1]
            //小根堆，每次当前线段的尾节点和堆头比较，比堆头大，堆头出堆
            while (!heap.isEmpty() && heap.peek() <= line[i][0]) {
                heap.poll();
            }
            heap.add(line[i][1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    /**
     * 让数组整体累加和减半的最少操作次数
     *
     * @param arr
     * @return
     */
    public static int halveArray(int[] arr) {
        PriorityQueue<Double> heap = new PriorityQueue<>((a, b) -> (int) (b - a));
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            heap.add((double) arr[i]);
            sum += arr[i];
        }
        sum /= 2;
        int ans = 0;
        for (double minus = 0, curr; minus < sum; ans++, minus += curr) {
            curr = heap.poll() / 2;
            heap.add(curr);
        }
        return ans;
    }
}
