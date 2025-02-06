package easy;

import java.util.*;

public class moveZeroes {
    public static void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if(nums[slow] != 0){
                slow++;
            } else if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                nums[fast] = 0;
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
