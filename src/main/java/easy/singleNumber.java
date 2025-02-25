package easy;

import java.util.Scanner;

public class singleNumber {
    public static int singleNumber(int[] nums) {
        int k = nums[0];
        for (int n = 1; n < nums.length; n++) {
            k = k ^ nums[n];
        }
        return k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(singleNumber(nums));
    }
}
