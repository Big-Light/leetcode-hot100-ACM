package easy;

import java.util.Scanner;

public class hasCycle {
    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
            next = null;
        }
    }
    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        //本题输入可分三步走，1、获取链表元素；2、构建链表；3、获取环的位置并构建环
        //1、构建链表
        ListNode head = new ListNode(Integer.parseInt(split[0]));
        ListNode temp = head;
        for (int i = 1; i < split.length; i++) {
            temp.next = new ListNode(Integer.parseInt(split[i]));
            temp = temp.next;
        }
        //2.获取环的位置并构建环
        int pos = sc.nextInt();
        if(pos != -1){
            ListNode lastNode = head;
            //先找到末尾节点
            while (lastNode.next != null){
                lastNode = lastNode.next;
            }
            //再找到环的位置，然后让末尾节点的next指向环的入口节点
            ListNode cycleNode = head;
            for (int i = 0; i < pos; i++) {
                cycleNode = cycleNode.next;
            }
            lastNode.next = cycleNode;
        }
        System.out.println(hasCycle(head));
    }
}
