package hard;

import java.util.Scanner;

public class firstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (x > 0 && x <= n && x != nums[x - 1]){
                int temp = nums[x - 1];
                nums[x - 1] = x;
                x = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if(nums[i] != i + 1)
                return i + 1;
        }
        return n + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(firstMissingPositive(nums));
    }
}
