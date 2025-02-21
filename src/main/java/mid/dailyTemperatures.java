package mid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class dailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int p = stack.pop();    //弹出的索引该处的元素下一个更大元素找到了！
                ans[p] = i - p;
            }
            stack.push(i);
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
        System.out.println(Arrays.toString(dailyTemperatures(nums)));
    }
}
