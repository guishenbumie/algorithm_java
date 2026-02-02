package c.a_binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int x) {
            val = x;
        }
    }

    public static int MAXN = 2001;//leetcode中的一道题，题目说的最大就是这个值
    public static TreeNode[] queue = new TreeNode[MAXN];
    public static int l, r;

    /**
     * 二叉树的层序遍历（也可以使用队列+map的形式实现，map记录每层的节点数）
     * 每层进行遍历，有节点往队列里放，r+1；拿出节点时，l+1
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            l = r = 0;
            queue[r++] = root;
            while (l < r) {
                int size = r - l;
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue[l++];
                    list.add(cur.val);
                    if (cur.left != null) {
                        queue[r++] = cur.left;
                    }
                    if (cur.right != null) {
                        queue[r++] = cur.right;
                    }
                }
                ans.add(list);
            }
        }
        return ans;
    }

    /**
     * 锯齿形遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            l = r = 0;
            queue[r++] = root;
            boolean reverse = false;
            while (l < r) {
                int size = r - l;
                ArrayList<Integer> list = new ArrayList<>();
                //reverse == false，从左往右收集，l r-1
                //reverse == true，从右往左收集，r-1 l
                for (int i = reverse ? r - 1 : l, j = reverse ? -1 : 1, k = 0; k < size; i += j, k++) {
                    TreeNode cur = queue[i];
                    list.add(cur.val);
                }
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue[l++];
                    if (cur.left != null) {
                        queue[r++] = cur.left;
                    }
                    if (cur.right != null) {
                        queue[r++] = cur.right;
                    }
                }
                ans.add(list);
                reverse = !reverse;
            }
        }
        return ans;
    }

    /**
     * 最左不空到最右不空节点的最大数
     *
     * @param root
     * @return
     */
    public static TreeNode[] nq = new TreeNode[MAXN];
    public static int[] iq = new int[MAXN];

    public static int widthOfBT(TreeNode root) {
        int ans = 1;
        l = r = 0;
        nq[r] = root;
        iq[r++] = 1;
        while (l < r) {
            int size = r - l;
            ans = Math.max(ans, iq[r - 1] - iq[l] + 1);
            for (int i = 0; i < size; i++) {
                TreeNode cur = nq[l];
                int id = iq[l++];
                if (cur.left != null) {
                    nq[r] = cur.left;
                    iq[r++] = id * 2;
                }
                if (cur.right != null) {
                    nq[r] = cur.right;
                    iq[r++] = id * 2 + 1;
                }
            }
        }
        return ans;
    }

    /**
     * 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;//空树
        if (root.left == null && root.right == null) return 1;//叶节点
        int ldepth = Integer.MAX_VALUE;
        int rdepth = Integer.MAX_VALUE;
        if (root.left != null) {
            ldepth = minDepth(root.left);
        }
        if (root.right != null) {
            rdepth = minDepth(root.right);
        }
        return Math.min(ldepth, rdepth) + 1;
    }

    /**
     * 先序或者后续都可以序列化一棵树，并且反序列化出来；中序不行
     *
     * @param root
     * @return
     */
    public static String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    public static void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#");
        } else {
            builder.append(root.val).append(",");
            serialize(root.left, builder);
            serialize(root.right, builder);
        }
    }

    public static int cnt;

    public static TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        cnt = 0;
        return deserialize(vals);
    }

    public static TreeNode deserialize(String[] vals) {
        String cur = vals[cnt++];
        if (cur.isEmpty()) {
            return null;
        } else {
            TreeNode root = new TreeNode();
            root.val = Integer.parseInt(cur);
            root.left = deserialize(vals);
            root.right = deserialize(vals);
            return root;
        }
    }

    /**
     * 按层序列化
     *
     * @param root
     * @return
     */
    public static String serialize2(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        if (root != null) {
            builder.append(root.val).append(",");
            l = 0;
            r = 0;
            queue[r++] = root;
            while (l < r) {
                root = queue[l++];
                if (root.left != null) {
                    builder.append(root.left.val).append(",");
                    queue[r++] = root.left;
                } else {
                    builder.append("#");
                }
                if (root.right != null) {
                    builder.append(root.right.val).append(",");
                    queue[r++] = root.right;
                } else {
                    builder.append("#");
                }
            }
        }
        return builder.toString();
    }

    public static TreeNode deserialize2(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] vals = data.split(",");
        int idx = 0;
        TreeNode root = gen(vals[idx++]);
        l = 0;
        r = 0;
        queue[r++] = root;
        while (l < r) {
            TreeNode cur = queue[l++];
            cur.left = gen(vals[idx++]);
            cur.right = gen(vals[idx++]);
            if (cur.left != null) {
                queue[r++] = cur.left;
            }
            if (cur.right != null) {
                queue[r++] = cur.right;
            }
        }
        return root;
    }

    private static TreeNode gen(String val) {
        return val.equals("#") ? null : new TreeNode(Integer.parseInt(val));
    }

    /**
     * 根据先序和中序遍历的数组，构造出一棵树
     *
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return buildTree(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    public static TreeNode buildTree(int[] pre, int l1, int r1, int[] in, int l2, int r2, HashMap<Integer, Integer> map) {
        if (l1 > r1)
            return null;
        TreeNode root = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return root;
        }
        int find = map.get(pre[l1]);
        root.left = buildTree(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, map);
        root.right = buildTree(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, map);
        return root;
    }

    /**
     * 是否是完全二叉树
     *
     * @param root
     * @return
     */
    public static boolean isFullBT(TreeNode root) {
        if (root == null) return true;
        l = r = 0;
        queue[r++] = root;
        boolean leaf = false;//是否遇到过左右两个孩子不双全的节点
        while (l < r) {
            root = queue[l++];
            //破坏下面两个条件就不是完全二叉树：
            //1. 有右孩子，但没左孩子
            //2. 左右孩子有不为空的情况，并且不是叶子节点
            if ((root.left == null && root.right != null) || (leaf && (root.left != null || root.right != null))) {
                return false;
            }
            if (root.left != null) {
                queue[r++] = root.left;
            }
            if (root.right != null) {
                queue[r++] = root.right;
            }
            if (root.left == null || root.right == null) {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 求完全二叉树的节点个数，时间复杂度是O(n)
     *
     * @param root
     * @return
     */
    public static int fullBTNodes(TreeNode root) {
        if (root == null) return 0;
        return f(root, 1, mostLeft(root, 1));
    }

    //h表示整棵树的高度，不是cur这颗子树的高度，返回这颗子树上有多少节点
    public static int f(TreeNode cur, int level, int h) {
        if (level == h) {
            return 1;
        }
        if (mostLeft(cur.right, level + 1) == h) {//cur右树上的最左扎到了最底
            return (1 << (h - level)) + f(cur.right, level + 1, h);
        } else {//cur右树上的最左节点，没扎到最底
            return (1 << (h - level - 1)) + f(cur.left, level + 1, h);
        }
    }

    //level是cur当前节点的层数，返回cur不停往左能够扎到第几层
    public static int mostLeft(TreeNode cur, int level) {
        while (cur != null) {
            level++;
            cur = cur.left;
        }
        return level - 1;
    }
}
