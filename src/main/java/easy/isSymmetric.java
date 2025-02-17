package easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class isSymmetric {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }
    public static boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return isD(root.left, root.right);
    }

    private static boolean isD(TreeNode left, TreeNode right) {
        if(left == null && right == null)
            return true;
        else if (left != null && right != null) {
            if(left.val != right.val)
                return false;
        }else
            return false;
        boolean b1 = isD(left.left, right.right);
        boolean b2 = isD(left.right, right.left);
        return b1 && b2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        TreeNode root = bT(split);
        System.out.println(isSymmetric(root));
    }

    public static TreeNode bT(String[] nodes){
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
