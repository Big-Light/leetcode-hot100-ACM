package mid;

import java.util.Scanner;

public class maxSubArray {
    public static int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(maxSubArray(nums));
    }
}
