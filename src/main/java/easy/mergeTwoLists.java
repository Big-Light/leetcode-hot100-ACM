package easy;

import java.util.Scanner;

public class mergeTwoLists {
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

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        ListNode list1 = new ListNode(Integer.parseInt(split[0]));
        ListNode l1 = list1;
        for (int i = 1; i < split.length; i++) {
            l1.next = new ListNode(Integer.parseInt(split[i]));
            l1 = l1.next;
        }

        String p = sc.nextLine();
        String[] split1 = p.split(" ");
        ListNode list2 = new ListNode(Integer.parseInt(split1[0]));
        ListNode l2 = list2;
        for (int i = 1; i < split1.length; i++) {
            l2.next = new ListNode(Integer.parseInt(split1[i]));
            l2 = l2.next;
        }
        ListNode temp = mergeTwoLists(list1, list2);
        while (temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}