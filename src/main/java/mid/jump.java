package mid;

import java.util.Scanner;

public class jump {
    public static int jump(int[] nums) {
        int ans = 0;
        int end = 0;
        int maxStep = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxStep = Math.max(maxStep, i + nums[i]);
            if(i == end){
                ans++;
                end = maxStep;
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
        System.out.println(jump(nums));
    }
}
