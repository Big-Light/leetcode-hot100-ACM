package easy;


import java.util.*;

public class inorderTraversal {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }
    static List<Integer> ans = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {  //递归版
        dfs(root);
        return ans;
    }

    private static void dfs(TreeNode root) {
        if(root == null)
            return;
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }


    public List<Integer> inorderTraversal1(TreeNode root) {  //迭代版
        List<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        TreeNode root = buildTree(split);
        List<Integer> list = inorderTraversal(root);
        System.out.println(list);
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
