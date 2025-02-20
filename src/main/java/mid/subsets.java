package mid;

import java.util.*;

public class subsets {
    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        backTracking(nums, 0);
        return ans;
    }

    private static void backTracking(int[] nums, int begin) {
        ans.add(new ArrayList<>(path));
        if(begin == nums.length)
            return;

        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(subsets(nums));
    }
}
