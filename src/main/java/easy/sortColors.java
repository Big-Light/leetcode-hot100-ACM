package easy;

import java.util.Arrays;
import java.util.Scanner;

public class sortColors {
    public static void sortColors(int[] nums) {
        int n = nums.length;
        int a = 0, c = 0, b = n - 1;

        while (c <= b){
            if(nums[c] == 0){
                int temp = nums[a];
                nums[a++] = nums[c];
                nums[c++] = temp;
            }else if(nums[c] == 1)
                c++;
            else {
                int temp = nums[c];
                nums[c] = nums[b];
                nums[b--] = temp;
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
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
