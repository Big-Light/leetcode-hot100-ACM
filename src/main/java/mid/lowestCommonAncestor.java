package mid;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class lowestCommonAncestor {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root)
            return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if(l != null && r != null)
            return root;
        return l == null ? r : l;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        TreeNode node = buildTree(split);
        int pVal = sc.nextInt();
        TreeNode p = findNode(node, pVal);
        int qVal = sc.nextInt();
        TreeNode q = findNode(node, qVal);
        System.out.println(lowestCommonAncestor(node, p, q).val);
    }

    private static TreeNode findNode(TreeNode node, int val) {  //本题由于p，q是作为节点输入，所以要写这么一个函数
        if(node == null || node.val == val)                     //可以根据传入的树根节点和值，来找到对应的节点
            return node;
        TreeNode left = findNode(node.left, val);
        TreeNode right = findNode(node.right, val);
        return left == null ? right : left;
    }

    public static TreeNode buildTree(String[] nodes){
        if(nodes == null || nodes.length == 0 || nodes[0].equals("null"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < nodes.length){
            TreeNode cur = queue.poll();

            if(index < nodes.length && !nodes[index].equals("null")){
                cur.left = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(cur.left);
            }
            index++;

            if(index < nodes.length && !nodes[index].equals("null")){
                cur.right = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(cur.right);
            }
            index++;
        }
        return root;
    }
}
