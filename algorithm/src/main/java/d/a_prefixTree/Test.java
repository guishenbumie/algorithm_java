package d.a_prefixTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 前缀树，数组实现方式（用哈希表也可以实现，基本一样，就是nexts换成hashmap类型）
     */
    public static class Trie {
        class TrieNode {
            public int pass;
            public int end;
            public TrieNode[] nexts;

            public TrieNode() {
                pass = 0;
                end = 0;
                nexts = new TrieNode[26];
            }
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            cur.pass++;
            for (int i = 0, path; i < word.length(); i++) {//从左到右遍历字符
                path = word.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
                cur.pass++;
            }
            cur.end++;
        }

        //如果之前word插入过前缀树，那么删掉一次；如果没有插入过，则什么也不做
        public void erase(String word) {
            if (search(word) > 0) {
                TrieNode cur = root;
                cur.pass--;
                for (int i = 0, path; i < word.length(); i++) {
                    path = word.charAt(i) - 'a';
                    if (--cur.nexts[path].pass == 0) {
                        cur.nexts[path] = null;
                        return;
                    }
                    cur = cur.nexts[path];
                }
                cur.end--;
            }
        }

        //查询是否有字符串
        public int search(String word) {
            TrieNode cur = root;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    return 0;
                }
                cur = cur.nexts[path];
            }
            return cur.end;
        }

        //查询有多少个单词以pre作为前缀
        public int countWordsStartingWith(String pre) {
            TrieNode cur = root;
            for (int i = 0, path; i < pre.length(); i++) {
                path = pre.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    return 0;
                }
                cur = cur.nexts[path];
            }
            return cur.pass;
        }
    }

    /**
     * 前缀树，静态数组
     */
    public static int MAXN = 150001;//增加数据量，就改大这个值
    public static int[][] tree = new int[MAXN][26];
    public static int[] end = new int[MAXN];
    public static int[] pass = new int[MAXN];
    public static int cnt;

    public static void build() {
        cnt = 1;
    }

    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur]++;
    }

    public static int search(String word) {
        int cur = 1;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {//没找到，当前没有这个字符串
                return 0;
            }
            cur = tree[cur][path];
        }
        return end[cur];//返回有几个字符串
    }

    public static int prefixNum(String pre) {
        int cur = 1;
        for (int i = 0, path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static void delete(String word) {
        if (search(word) > 0) {
            int cur = 1;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (--pass[tree[cur][path]] == 0) {
                    tree[cur][path] = 0;
                    return;
                }
                cur = tree[cur][path];
            }
            end[cur]--;
        }
    }

    public static String[] end2 = new String[MAXN];//结尾存这个路径的字符串

    public static void build2(String[] words) {
        cnt = 1;
        for (String word : words) {
            int cur = 1;
            pass[cur]++;
            for (int i = 0, path; i < words.length; i++) {
                path = words[i].charAt(0) - 'a';
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
                pass[cur]++;
            }
            end2[cur] = word;
        }
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end2[i] = null;
        }
    }

    /**
     * 二维数组中搜索单词
     *
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {
        build2(words);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, 1, ans);
            }
        }
        clear();
        return ans;
    }

    /**
     * 收集到了几个字符串
     *
     * @param board 二维网格
     * @param i     此时来到的格子的位置，i行
     * @param j     此时来到的格子的位置，j列
     * @param t     前缀树的编号
     * @param ans   收集到了哪些字符串
     * @return
     */
    public static int dfs(char[][] board, int i, int j, int t, List<String> ans) {
        //越界，或者 走了回头路，直接返回0
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == 0) {
            return 0;
        }
        //board[i][j] = 't' -> tmp
        char tmp = board[i][j];
        //路的编号，a->0，b->1，... z->25
        int road = tmp - 'a';
        t = tree[t][road];
        if (pass[t] == 0)
            return 0;
        //j，j的位置有必要来
        //fix：从当前的i、j位置出发，一共收集了几个字符串
        int fix = 0;
        if (end2[t] != null) {
            fix++;
            ans.add(end2[t]);
            end2[t] = null;
        }
        //把i、j位置的字符改成0，后续的过程，不需要再来i、j位置
        board[i][j] = 0;
        fix += dfs(board, i - 1, j, t, ans);
        fix += dfs(board, i + 1, j, t, ans);
        fix += dfs(board, i, j - 1, t, ans);
        fix += dfs(board, i, j + 1, t, ans);
        pass[t] -= fix;
        board[i][j] = tmp;
        return fix;
    }

}
