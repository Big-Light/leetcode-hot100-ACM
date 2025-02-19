package hard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class maxPathSum {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }
    static int ans = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private static int dfs(TreeNode root) {
        if(root == null)
            return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        ans = Math.max(ans, root.val + l + r);
        return Math.max(0, Math.max(l, r) + root.val);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        TreeNode root = buildTree(split);
        System.out.println(maxPathSum(root));
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
