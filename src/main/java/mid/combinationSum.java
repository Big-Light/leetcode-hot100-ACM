package mid;

import java.util.*;

public class combinationSum {
    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracking(candidates, 0, 0, target);
        return ans;
    }

    private static void backTracking(int[] candidates, int begin, int cur, int target) {
        if(cur == target){  //注意这里退出且可添加path的条件仅有cur == target，而begin的越界不一定有cur == target，所以应该放在下面
            ans.add(new ArrayList<>(path));
            return;
        }
        if(cur > target || begin == candidates.length)
            return;
        for (int i = begin; i < candidates.length; i++) {
            cur += candidates[i];
            path.add(candidates[i]);
            backTracking(candidates, i, cur, target);
            path.remove(path.size() - 1);
            cur -= candidates[i];
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
        int target = sc.nextInt();
        System.out.println(combinationSum(nums, target));
    }
}
