package mid;

import java.util.Scanner;

public class findMin {
    public static int findMin(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            int temp = nums[mid];
            if(temp <= nums[r]){
                ans = Math.min(ans, temp);
                r = mid - 1;
            }else {
                ans = Math.min(ans, nums[l]);
                l = mid + 1;
            }
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
        System.out.println(findMin(nums));
    }
}
