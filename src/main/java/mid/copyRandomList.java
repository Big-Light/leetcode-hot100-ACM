package mid;


import java.util.HashMap;

public class copyRandomList {
    public static class Node{
        int val;
        Node next;
        Node random;
        Node(int val){
            this.val = val;
            next = null;
            random = null;
        }
    }
    public Node copyRandomList(Node head) {
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null){
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            Node node = map.get(cur);
            node.next = map.get(cur.next);
            node.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
