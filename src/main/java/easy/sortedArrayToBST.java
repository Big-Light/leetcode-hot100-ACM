package easy;

import java.util.*;

public class sortedArrayToBST {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int l, int r) {
        if(l > r)
            return null;
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, l, mid - 1);
        root.right = buildBST(nums, mid + 1, r);
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        TreeNode node = sortedArrayToBST(nums);
        List<Integer> list = levelOrder(node);
        System.out.println(list);
    }

    // 层序遍历输出二叉树
    public static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }
}
