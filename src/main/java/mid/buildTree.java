package mid;

import java.util.*;

public class buildTree {
    public static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
    }

    static int[] temp;
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        temp = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode build(int pb, int pe, int ib, int ie) {
        if(pb > pe || ib > ie)
            return null;
        int value = temp[pb];
        TreeNode root = new TreeNode(value);
        int index = map.get(value);
        root.left = build(pb + 1, pb + index - ib, ib, index - 1);  //最主要是pe的判断，
        root.right = build(pb + index - ib + 1, pe, index + 1, ie); //这里pb肯定是上面pe+1，
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        String p = sc.nextLine();
        String[] split2 = s.split(",");
        int[] preorder = new int[split.length];
        int[] inorder = new int[split.length];
        for (int i = 0; i < preorder.length; i++) {
            preorder[i] = Integer.parseInt(split[i]);
            inorder[i] = Integer.parseInt(split2[i]);
        }
        TreeNode node = buildTree(preorder, inorder);
        List<Integer> list = levelorder(node);
        System.out.println(list);
    }

    public static List<Integer> levelorder(TreeNode node){
        List<Integer> ans = new ArrayList<>();
        if(node == null)
            return ans;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(node);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node1 = deque.poll();
                if(node1.left != null)
                    deque.offer(node1.left);
                if(node1.right != null)
                    deque.offer(node1.right);
                ans.add(node1.val);
            }
        }
        return ans;
    }
}
