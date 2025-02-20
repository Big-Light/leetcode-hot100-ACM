package mid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class permute {
    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    public static List<List<Integer>> permute(int[] nums) {
        backTracking(nums);
        return ans;
    }

    private static void backTracking(int[] nums) {
        if(path.size() == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int num : nums) {
            if(!path.contains(num)){
                path.add(num);
                backTracking(nums);
                path.remove(path.size() - 1);
            }
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
        System.out.println(permute(nums));
    }
}
