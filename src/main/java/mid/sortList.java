package mid;

import java.util.Scanner;

public class sortList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
            this.next = null;
        }
        ListNode(){};
    }
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (left != null && right != null){
            if(left.val < right.val){
                cur.next = left;
                left = left.next;
            }else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        ListNode head = new ListNode(Integer.parseInt(split[0]));
        ListNode cur = head;
        for (int i = 1; i < split.length; i++) {
            cur.next = new ListNode(Integer.parseInt(split[i]));
            cur = cur.next;
        }
        ListNode node = sortList(head);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
