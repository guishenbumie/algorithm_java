package b.l_zhongweishu;

import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 获取一个流的中位数
     */
    public static class MedianFinder {
        private PriorityQueue<Integer> maxHeap;//大根堆
        private PriorityQueue<Integer> minHeap;//小根堆

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            balance();
        }

        private void balance() {
            if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
                if (maxHeap.size() > minHeap.size()) {
                    minHeap.add(maxHeap.poll());
                } else {
                    maxHeap.add(minHeap.poll());
                }
            }
        }

        public double findMedian() {
            if (maxHeap.isEmpty() && minHeap.isEmpty())
                return 0.0;
            if (maxHeap.size() == minHeap.size()) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }
    }
}
