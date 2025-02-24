package mid;

import java.util.Scanner;

public class maxProduct {
    public static int maxProduct(int[] nums) {
        int maxPre = nums[0];
        int minPre = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if(nums[i] < 0){
                int temp = maxPre;
                maxPre = minPre;
                minPre = temp;
            }

            maxPre = Math.max(nums[i], maxPre * nums[i]);
            minPre = Math.min(nums[i], minPre * nums[i]);
            ans = Math.max(ans, maxPre);
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
        System.out.println(maxProduct(nums));
    }
}
