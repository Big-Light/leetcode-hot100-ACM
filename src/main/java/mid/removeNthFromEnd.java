package mid;

import java.util.Scanner;

public class removeNthFromEnd {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = new ListNode();
        slow.next = head;
        ListNode dummy = slow;
        ListNode fast = slow;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
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
        int n = sc.nextInt();
        ListNode node = removeNthFromEnd(head, n);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
