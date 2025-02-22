package mid;

import java.util.Scanner;

public class canJump {
    public static boolean canJump(int[] nums) {
        int maxPosition = 0;
        for (int i = 0; i <= maxPosition; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(i >= nums.length - 1)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(canJump(nums));
    }
}
