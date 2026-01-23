package af_binaryTree;

import java.util.Stack;

public class Test {
    public static void main(String[] args) {}

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) {
            val = x;
        }
    }

    public static void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void f(TreeNode root) {
        if (root == null)
            return;
        //1
        f(root.left);
        //2
        f(root.right);
        //3
    }

    /**
     * 二叉树先序，使用栈实现非递归方式
     * 使用栈先进后出的规则，遍历时先压右再压左，出栈就是先左再右了
     * @param root
     */
    public static void preOrder4Stack(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root.val + " ");
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
    }

    /**
     * 二叉树中序，使用栈实现非递归方式
     * 1. 将root和他左子树都压进栈，往下重复，一直到最左的叶子没有数了
     * 左子树没有了，出栈一个数，打印，将他右子树压进栈，重复1
     * @param root
     */
    public static void inOrder4Stack(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }
    }

    /**
     * 二叉树后序，使用栈实现非递归方式
     * 使用两个栈
     * 使用先序的类似的方式，遍历二叉树，同时压到另一个栈，中右左
     * 最后出栈就是左右中
     * @param root
     */
    public static void postOrder4Stack(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> collect = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            collect.push(root);
            if (root.left != null)
                stack.push(root.left);
            if (root.right != null)
                stack.push(root.right);
        }
        while (!collect.isEmpty()) {
            System.out.print(collect.pop().val + " ");
        }
    }
}
