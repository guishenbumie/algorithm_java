package c.b_binaryTree2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    /**
     * 普通二叉树的最低公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null) return root;//左树也搜到，右树也搜到，p和q在两个子树里
        if (l == null && r == null) return null;//都没搜到
        return l != null ? l : r;//返回不空的那个，也就是p包含了q或者q包含了p，p和q在一颗子树里
    }

    /**
     * 搜索二叉树的最低公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //假设p是小的
        //root从上到下
        //如果先遇到p，p是答案
        //如果先遇到q，q是答案
        //如果root在p q之间，root是答案
        //如果root在p q左边，root往右移动
        //如果root在p q右边，root往左移动
        while (root.val != p.val && root.val != q.val) {
            if (Math.min(p.val, q.val) < root.val && root.val < Math.max(p.val, q.val)) {
                break;
            }
            root = root.val < Math.min(p.val, q.val) ? root.right : root.left;
        }
        return root;
    }

    /**
     * 收集累加和为aim的所有的路径
     *
     * @param root
     * @param aim
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int aim) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            List<Integer> path = new ArrayList<>();
            process(root, aim, 0, path, res);
        }
        return res;
    }

    public static void process(TreeNode cur, int aim, int sum, List<Integer> path, List<List<Integer>> res) {
        if (cur.left == null && cur.right == null) {//叶节点
            if (cur.val + sum == aim) {//找到一个答案
                path.add(cur.val);
                copy(path, res);
                path.remove(path.size() - 1);
            }
        } else {//不是叶节点
            path.add(cur.val);
            if (cur.left != null) {
                process(cur.left, aim, sum + cur.val, path, res);
            }
            if (cur.right != null) {
                process(cur.right, aim, sum + cur.val, path, res);
            }
            path.remove(path.size() - 1);
        }
    }

    public static void copy(List<Integer> path, List<List<Integer>> res) {
        List<Integer> temp = new ArrayList<>(path);
        res.add(temp);
    }

    public static boolean balance;

    /**
     * 验证平衡二叉树
     * 平衡二叉树，所有节点左树高度和右树高度相差不超过1
     *
     * @param root
     * @return
     */
    public static boolean isBalancedBT(TreeNode root) {
        balance = true;
        height(root);
        return balance;
    }

    public static int height(TreeNode root) {
        if (!balance || root == null) return 0;
        int lh = height(root.left);
        int rh = height(root.right);
        if (Math.abs(lh - rh) > 1) {
            balance = false;
        }
        return Math.max(lh, rh) + 1;
    }

    /**
     * 验证搜索二叉树
     * 中序遍历都是升序
     *
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root) {
        if (root == null) return true;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && pre.val >= root.val) {
                    return false;
                }
                pre = root;
                root = root.right;
            }
        }
        return true;
    }

    public static long min, max;

    /**
     * 递归方式
     *
     * @param root
     * @return
     */
    public static boolean isBST2(TreeNode root) {
        if (root == null) {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
            return true;
        }
        boolean lok = isBST2(root.left);
        long lmin = min;
        long lmax = max;
        boolean rok = isBST2(root.right);
        long rmin = min;
        long rmax = max;
        min = Math.min(Math.min(lmin, rmin), root.val);
        max = Math.max(Math.max(lmax, rmax), root.val);
        return lok && rok && lmax < root.val && root.val < rmin;
    }

    /**
     * 修剪一棵搜索二叉树，保留[low,high]范围的树的部分
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) {//当前比左边界小，当前和它左树都不保留，替换为它的右树的结果
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {//当前比右边界大，当前和它右树都不保留，替换为它的左树的结果
            return trimBST(root.left, low, high);
        }
        //root在范围中的情况
        root.left = trimBST(root.left, low, high);//左树替换为左树的结果
        root.right = trimBST(root.right, low, high);//右树替换为右树的结果
        return root;
    }

    public static int yes;//完成x子树的遍历，返回后，yes变成x子树在偷头节点情况下的最大收益
    public static int no;//完成x子树的遍历，返回后，no变成x子树在不偷头节点情况下的最大收益

    /**
     * 打家劫舍问题，小偷偷东西，相邻的节点不能偷，最大的收益
     *
     * @param root
     * @return
     */
    public static int rob(TreeNode root) {
        f(root);
        return Math.max(yes, no);
    }

    public static void f(TreeNode root) {
        if (root == null) {
            yes = 0;
            no = 0;
        } else {
            int y = root.val;//偷当前的头节点的收益
            int n = 0;//不偷的收益
            rob(root.left);//左孩子收益计算
            y += no;//加上不偷左孩子的头节点的收益
            n += Math.max(yes, no);//加上左孩子的最大收益，头可偷可不偷
            rob(root.right);
            y += no;
            n += Math.max(yes, no);
            yes = y;
            no = n;
        }
    }
}
