package ad_link;

public class LinkTest {

    public static void main(String[] args) {
    }

    public static class LinkNode {
        public int val;
        public LinkNode next;

        public LinkNode(int val) {
            this.val = val;
        }

        public LinkNode(int val, LinkNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 反转一个单链表
     * @param head
     * @return
     */
    public static LinkNode reverse(LinkNode head) {
        LinkNode prev = null;
        LinkNode next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * 合并两个链表
     * @param h1
     * @param h2
     * @return
     */
    public static LinkNode merge(LinkNode h1, LinkNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        LinkNode h = h1.val <= h2.val ? h1 : h2;
        LinkNode cur1 = h1.next;
        LinkNode cur2 = h == h1 ? h2 : h1;
        LinkNode prev = h;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                prev.next = cur1;
                cur1 = cur1.next;
            } else {
                prev.next = cur2;
                cur2 = cur2.next;
            }
            prev = prev.next;
        }
        prev.next = cur1 != null ? cur1 : cur2;
        return h;
    }

    /**
     * 两个链表相加
     * 984 和 9762相加，不用int或long，用链表实现
     * 4->8->9和2->6->7->9
     * @param head1
     * @param head2
     * @return
     */
    public static LinkNode add(LinkNode head1, LinkNode head2) {
        LinkNode ans = null;
        LinkNode curr = null;
        int carry = 0;
        for (int sum, val;
             head1 != null || head2 != null;
             head1 = head1 == null ? null : head1.next, head2 = head2 == null ? null : head2.next) {
            sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            val = sum % 10;
            carry = sum / 10;
            if (ans == null) {
                ans = new LinkNode(val);
                curr = ans;
            } else {
                curr.next = new LinkNode(val);
                curr = curr.next;
            }
        }
        if (carry > 0) {
            curr.next = new LinkNode(carry);
        }
        return ans;
    }

    /**
     * 划分链表，小于k的放在左边，大于等于k的放在右边，不能改变相对次序
     * 6->5->3->4->2->1->1->7 k=4
     * 3->2->1->1->6->5->4->7
     * @param head
     * @param k
     * @return
     */
    public static LinkNode split(LinkNode head, int k) {
        LinkNode lHead = null, lTail = null;
        LinkNode rHead = null, rTail = null;
        LinkNode next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < k) {
                if (lHead == null) {
                    lHead = head;
                } else {
                    lTail.next = head;
                }
                lTail = head;
            } else {
                if (rHead == null) {
                    rHead = head;
                } else {
                    rTail.next = head;
                }
                rTail = head;
            }
            head = next;
        }
        if (lHead == null) {
            return rHead;
        }
        lTail.next = rHead;
        return lHead;
    }
}