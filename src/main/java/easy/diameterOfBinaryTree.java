package easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class diameterOfBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }

    static int ans = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private static int dfs(TreeNode root) {
        if(root == null)
            return -1;
        int left = dfs(root.left) + 1;
        int rigth =  dfs(root.right) + 1;
        ans = Math.max(ans, left + rigth);
        return Math.max(left, rigth);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        TreeNode root = bT(split);
        System.out.println(diameterOfBinaryTree(root));
    }

    public static TreeNode bT(String[] s){
        if(s == null || s.length == 0 || s[0].equals("null"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < s.length){
            TreeNode cur = queue.poll();

            if(index < s.length && !s[0].equals("null")){
                cur.left = new TreeNode(Integer.parseInt(s[index]));
                queue.offer(cur.left);
            }
            index++;

            if(index < s.length && !s[0].equals("null")){
                cur.right = new TreeNode(Integer.parseInt(s[index]));
                queue.offer(cur.right);
            }
            index++;
        }
        return root;
    }
}
