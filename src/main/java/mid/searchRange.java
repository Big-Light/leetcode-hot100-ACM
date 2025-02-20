package mid;

import java.util.Arrays;
import java.util.Scanner;

public class searchRange {
    public static int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            int temp = nums[mid];
            if(temp > target)
                r = mid - 1;
            else if (temp < target) {
                l = mid + 1;
            }else {
                int lMin = mid, rMax = mid;
                while (lMin >= 0 && nums[lMin] == target)   lMin--;
                while (rMax < nums.length && nums[rMax] == target)  rMax++;
                return new int[]{lMin + 1, rMax - 1};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int target = sc.nextInt();
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }
}
