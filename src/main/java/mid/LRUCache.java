package mid;

import java.util.HashMap;

public class LRUCache {

    public class Node{
        int key;
        int value;
        Node prev;
        Node next;
        Node(){};
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private int capacity;
    private Node dummy = new Node(0, 0);
    private HashMap<Integer, Node> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.next = dummy;
        dummy.prev = dummy;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null)
            return -1;
        //如果node存在，就将其移到链表头部
        move2Head(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null){
            //不为null，就更新值并将其移动到头部
            node.value = value;
            move2Head(node);
        }else {
            //为null，需要创建一个节点并添加到头部
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            add2Head(newNode);
            if(map.size() > capacity){
                //如果添加后超过了容量，就把最后一个节点删除。如何哈希表中也要删除，这就是为什么这个删除函数要返回node的原因
                Node lastNode = removeTail();
                map.remove(lastNode.key);
            }
        }
    }

    //添加一个节点到头部
    public void add2Head(Node node){
        dummy.next.prev = node;
        node.next = dummy.next;
        node.prev = dummy;
        dummy.next = node;
    }

    //删除一个节点
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //移动一个节点到头部
    public void move2Head(Node node){
        removeNode(node);
        add2Head(node);
    }

    //删除尾部节点（最久未使用）
    public Node removeTail(){
        Node node = dummy.prev;
        removeNode(node);
        return node;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // 输出 1
        lruCache.put(3, 3); // 删除键 2
        System.out.println(lruCache.get(2)); // 输出 -1
    }
}
