package hard;

import java.util.Scanner;

public class reverseKGroup {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ;

        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        cur = head;
        ListNode pre = null;
        ListNode temp = pre;
        ListNode node = dummy;
        while (count >= k) {
            for (int i = 0; i < k; i++) {
                temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            ListNode tail = node.next;
            node.next = pre;
            tail.next = cur;
            node = tail;
            count -= k;
        }
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
        int k = sc.nextInt();
        ListNode node = reverseKGroup(head, k);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}

