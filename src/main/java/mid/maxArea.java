package mid;

import java.util.Scanner;

public class maxArea {
    public static int maxArea(int[] height) {
        int low = 0, high = height.length - 1;
        int ans = 0;
        while (low <= high){
            if(height[low] < height[high]){
                ans = Math.max(ans, height[low] * (high - low));
                low++;
            }else {
                ans = Math.max(ans, height[high] * (high - low));
                high--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(maxArea(nums));
    }
}
