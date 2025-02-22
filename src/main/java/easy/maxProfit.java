package easy;

import java.util.Scanner;

public class maxProfit {
    public static int maxProfit(int[] prices) {
        int ans = 0;
        int pre = prices[0];
        for (int price : prices) {
            pre = Math.min(pre, price);
            ans = Math.max(ans, price - pre);
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
        System.out.println(maxProfit(nums));
    }
}
