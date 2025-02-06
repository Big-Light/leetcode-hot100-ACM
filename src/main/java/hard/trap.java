package hard;

import java.util.Scanner;

public class trap {
    public static int trap(int[] height) {
        int n = height.length;
        if(n <= 2)  return 0;
        int ans = 0;
        int lMax = height[0], rMax = height[n - 1];
        int l = 1, r = n - 2;
        while (l <= r){
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);
            if(lMax < rMax)
                ans += lMax - height[l++];
            else
                ans += rMax - height[r--];
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(trap(nums));
    }
}
