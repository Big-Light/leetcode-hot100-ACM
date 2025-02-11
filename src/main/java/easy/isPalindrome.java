package easy;

import java.util.List;
import java.util.Scanner;

public class isPalindrome {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
            next = null;
        }
    }
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. 快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半部分链表
        ListNode reversedHalf = reverseList(slow);

        // 3. 比较前后两部分
        ListNode p1 = head, p2 = reversedHalf;
        boolean result = true;
        while (p2 != null) {  // 只需比较后半部分的长度
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 4. 恢复链表（可选）
        reverseList(reversedHalf);
        return result;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
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
        System.out.println(isPalindrome(head));
    }
}
