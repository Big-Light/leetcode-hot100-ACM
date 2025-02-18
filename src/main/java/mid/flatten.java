package mid;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class flatten {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }

    public static void flatten(TreeNode root) {
        while (root != null){
            TreeNode left = root.left;
            TreeNode temp = left;
            if(temp != null){
                while (temp.right != null){
                    temp = temp.right;
                }
                temp.right = root.right;
                root.left = null;
                root.right = left;
            }
            root = root.right;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        TreeNode node = buildTree(split);
        flatten(node);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.right;
        }
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
