package easy;

import java.util.Scanner;

public class searchInsert {
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                r = mid - 1;
            }else
                l = mid + 1;
        }
        return l;
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
        System.out.println(searchInsert(nums, target));
    }
}
