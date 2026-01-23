package ae_queueAndStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {

    }

    /**
     * 给定一个明确的数据量，使用数组实现队列
     */
    public static class MyQueue {
        public int[] queue;
        //[l,r)
        public int l;
        public int r;

        public MyQueue(int n) {
            queue = new int[n];
            l = 0;
            r = 0;
        }

        public boolean isEmpty() {
            return l == r;
        }

        //加入一个数
        public void offer(int x) {
            queue[r++] = x;
        }

        //拿出一个数
        public int poll() {
            return queue[l++];
        }

        public int head() {
            return queue[l];
        }

        public int tail() {
            return queue[r - 1];
        }

        public int size() {
            return r - l;
        }
    }

    public static class MyStack {
        public int[] stack;
        public int n;

        public MyStack(int n) {
            stack = new int[n];
            this.n = n;
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public void push(int x) {
            stack[n++] = x;
        }

        public int pop() {
            return stack[--n];
        }

        public int peek() {
            return stack[n - 1];
        }

        public int size() {
            return n;
        }
    }

    /**
     * 使用队列实现栈
     */
    public static class MyQueue2 {
        public Stack<Integer> in;
        public Stack<Integer> out;

        public MyQueue2() {
            in = new Stack<>();
            out = new Stack<>();
        }

        //从in将数据倒入out
        private void inToOut() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }

        public void push(int x) {
            in.push(x);
            inToOut();
        }

        public int pop() {
            inToOut();
            return out.pop();
        }

        public int peek() {
            inToOut();
            return out.peek();
        }

        public boolean isEmpty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    /**
     * 用队列实现栈
     */
    public static class MyStack2 {
        public Queue<Integer> queue;

        public MyStack2() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            int n = queue.size();
            queue.offer(x);
            for (int i = 0; i < n; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * 最小栈，以O(1)的时间复杂度获取栈中的最小值
     */
    public static class MinStack {
        public Stack<Integer> data;
        public Stack<Integer> min;

        public MinStack() {
            data = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if (min.isEmpty() || x < min.peek()) {
                min.push(x);
            } else {
                min.push(min.peek());
            }
        }

        public int pop() {
            min.pop();
            return data.pop();
        }

        public int top() {
            return data.peek();
        }

        public int min() {
            return min.peek();
        }
    }
}
