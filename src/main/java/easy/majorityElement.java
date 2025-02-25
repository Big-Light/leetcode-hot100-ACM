package easy;

import java.util.Scanner;

public class majorityElement {
    public static int majorityElement(int[] nums) {
        int vote = 0;
        int ans = 0;
        for (int num : nums) {
            if(vote == 0)
                ans = num;
            if(num == ans)
                vote++;
            else
                vote--;
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
        System.out.println(majorityElement(nums));
    }
}
