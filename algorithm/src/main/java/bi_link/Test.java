package bi_link;

public class Test {
    public static void main(String[] args) {
    }

    public static class LinkNode {
        public LinkNode next;
        public LinkNode random;
        public int val;

        public LinkNode(int val) {
            this.val = val;
        }

        public LinkNode(LinkNode next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    /**
     * 返回两个无环链表相交的第一个节点（不能用容器，用容器的话直接把h1存到一个hashset中，再遍历h2就能找到相交节点）
     *
     * @param h1
     * @param h2
     * @return
     */
    public static LinkNode getIntersectionNode(LinkNode h1, LinkNode h2) {
        if (h1 == null || h2 == null) return null;
        LinkNode a = h1, b = h2;
        int diff = 0;
        while (a.next != null) {
            a = a.next;
            diff++;
        }
        while (b.next != null) {
            b = b.next;
            diff--;
        }
        if (a != b) {//两个链表最后的节点都不同，肯定不相交
            return null;
        }
        if (diff >= 0) {//a赋值为长链表的头，b为短的头
            a = h1;
            b = h2;
        } else {
            a = h2;
            b = h1;
        }
        diff = Math.abs(diff);
        while (diff-- != 0) {//长链表先走差值步
            a = a.next;
        }
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    /**
     * 每k个节点一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public static LinkNode reverseKGroup(LinkNode head, int k) {
        LinkNode start = head;
        LinkNode end = teamEnd(start, k);
        if (end == null) return head;
        //第一组，要记录下，结果要返回新的头
        head = end;
        reverse(start, end);
        //后面继续翻转
        LinkNode lastTeamEnd = start;
        while (lastTeamEnd.next != null) {
            start = lastTeamEnd.next;
            end = teamEnd(start, k);
            if (end == null) return head;
            reverse(start, end);
            lastTeamEnd.next = end;
            lastTeamEnd = start;
        }
        return head;
    }

    public static LinkNode teamEnd(LinkNode s, int k) {
        while (--k != 0 && s != null) {
            s = s.next;
        }
        return s;
    }

    public static void reverse(LinkNode s, LinkNode e) {
        e = e.next;
        LinkNode pre = null, cur = s, next = null;
        while (cur != e) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        s.next = e;
    }

    /**
     * 链表的节点有一个变量随机指针，克隆这个链表
     *
     * @param head
     * @return
     */
    public static LinkNode copyRandomList(LinkNode head) {
        if (head == null) return null;
        LinkNode cur = head;
        LinkNode next = null;
        // 1->2->3
        // 1->1‘->2->2’->3->3‘
        while (cur != null) {
            next = cur.next;
            cur.next = new LinkNode(cur.val);//克隆一个节点，放到源节点的后面，先不管random指针
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        LinkNode copy = null;
        //再遍历一遍链表，设置random的值
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        LinkNode ans = head.next;
        cur = head;
        //新老链表的分离
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return ans;
    }

    /**
     * 一个链表是否是回文
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(LinkNode head) {
        if (head == null || head.next == null) return true;
        LinkNode slow = head, fast = head;
        //快慢指针找到中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //从中点往后节点逆序
        LinkNode pre = slow;
        LinkNode cur = pre.next;
        LinkNode next = null;
        pre.next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        boolean ans = true;
        LinkNode l = head;
        LinkNode r = pre;
        while (l != null && r != null) {
            if (l.val != r.val) {
                ans = false;
                break;
            }
            l = l.next;
            r = r.next;
        }
        //还原原始链表
        cur = pre.next;
        pre.next = null;
        next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }

    /**
     * 找到可能存在环的入环结点
     *
     * @param head
     * @return
     */
    public static LinkNode detectCycle(LinkNode head) {
        if (head == null || head.next == null || head.next.next == null) return null;
        LinkNode slow = head.next, fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 链表排序，要求时间复杂度是O(n * log n)，空间复杂度是O(1)（也就是不能用递归）
     *
     * @param head
     * @return
     */
    public static LinkNode sort(LinkNode head) {
        int n = 0;
        LinkNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        LinkNode l1, r1;//每组的左部分
        LinkNode l2, r2;//每组的右部分
        LinkNode next;//下一组的开头
        LinkNode lastTeamEnd;//上一组的结尾
        for (int step = 1; step < n; step <<= 1) {
            l1 = head;
            r1 = findEnd(l1, step);
            l2 = r1.next;
            r2 = findEnd(l2, step);
            next = r2.next;
            r1.next = null;
            r2.next = null;
            merge(l1, r1, l2, r2);
            head = start;
            lastTeamEnd = end;
            while (next != null) {
                l1 = next;
                r1 = findEnd(l1, step);
                l2 = r1.next;
                if (l2 == null) {
                    lastTeamEnd.next = l1;
                    break;
                }
                r2 = findEnd(l2, step);
                next = r2.next;
                r1.next = null;
                r2.next = null;
                merge(l1, r1, l2, r2);
                lastTeamEnd.next = start;
                lastTeamEnd = end;
            }
        }
        return head;
    }

    public static LinkNode start;
    public static LinkNode end;

    public static LinkNode findEnd(LinkNode head, int k) {
        while (head.next != null && --k != 0) {
            head = head.next;
        }
        return head;
    }

    public static void merge(LinkNode l1, LinkNode r1, LinkNode l2, LinkNode r2) {
        LinkNode pre;
        if (l1.val <= l2.val) {
            start = l1;
            pre = l1;
            l1 = l1.next;
        } else {
            start = l2;
            pre = l2;
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            pre.next = l1;
            end = r1;
        } else {
            pre.next = r2;
            end = r2;
        }
    }
}
