package easy;

import java.util.*;

public class reverseList {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int x){
            val = x;
            next = null;
        }
    }
    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode temp = null, pre = null;
        while (cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String[] split = s.split(" ");
        ListNode head = new ListNode(Integer.parseInt(split[0]));
        ListNode temp = head;
        for (int i = 1; i < split.length; i++) {
            temp.next = new ListNode(Integer.parseInt(split[i]));
            temp = temp.next;
        }
        ListNode reverHead = reverseList(head);
        while (reverHead != null){
            System.out.println(reverHead.val);
            reverHead = reverHead.next;
        }
    }
}
