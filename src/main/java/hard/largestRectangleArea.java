package hard;

import java.util.ArrayDeque;
import java.util.Scanner;

public class largestRectangleArea {
    public static int largestRectangleArea(int[] heights) {
        int ans = Integer.MIN_VALUE;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]){
                int high = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                ans = Math.max(ans, high * width);
            }
            stack.push(i);
        }

        while (stack.peek() != -1){
            int high = heights[stack.pop()];
            int width = heights.length - stack.peek() - 1;
            ans = Math.max(ans, high * width);
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
        System.out.println(largestRectangleArea(nums));
    }
}
