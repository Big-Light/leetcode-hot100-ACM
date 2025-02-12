package mid;

import java.util.Scanner;

public class swapPairs {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode temp = dummy;
        while (head != null && head.next != null){
            temp.next = head.next;      // ——>2
            head.next = head.next.next; //1——>3
            temp.next.next = head;  //2 -> 1
            temp = head;
            head = head.next;
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
        ListNode node = swapPairs(head);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
