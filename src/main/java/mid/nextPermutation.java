package mid;

import java.util.Arrays;
import java.util.Scanner;

public class nextPermutation {
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1])
            i--;

        if(i > 0){
            int j = nums.length - 1;
            while (j > i && nums[j] < nums[i])
                j--;

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        int l = i + 1, r = nums.length - 1;
        while (l <= r){
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
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
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
