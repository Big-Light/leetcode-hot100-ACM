package mid;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class kthSmallest {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }

    static int index = 1;
    static int ans = 0;
    public static int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }

    private static void dfs(TreeNode root, int k) {
        if(root == null)
            return;
        dfs(root.left, k);
        if(index == k)
            ans = root.val;
        index++;
        dfs(root.right, k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        TreeNode treeNode = buildTree(split);
        int k = sc.nextInt();
        System.out.println(kthSmallest(treeNode, k));
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
