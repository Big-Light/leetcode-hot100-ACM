package mid;

import java.util.*;

public class longestConsecutive {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int i : set) {
            if(!set.contains(i - 1)){
                int cur = 1;
                while (set.contains(++i))
                    cur++;
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(",");   //默认输入以逗号作为间隔
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(longestConsecutive(nums));
    }
}
