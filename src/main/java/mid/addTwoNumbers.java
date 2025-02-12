package mid;

import java.util.Scanner;

public class addTwoNumbers {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null || l2 != null){
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            cur.next = new ListNode((val1 + val2 + temp) % 10);
            temp = (val1 + val2 + temp) / 10;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
            cur = cur.next;
        }
        if(temp == 1)
            cur.next = new ListNode(1);
        return dummy.next;
    }

    public static void main(String[] args) {    //这题输入类似上一题合并有序链表，直接cv了
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
        ListNode node = addTwoNumbers(list1, list2);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
