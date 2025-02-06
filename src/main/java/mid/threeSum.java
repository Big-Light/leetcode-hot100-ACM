package mid;

import java.util.*;

public class threeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] >= 0)
                break;
            if(i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                int temp = nums[i] + nums[left] + nums[right];
                if(temp < 0)
                    left++;
                else if (temp > 0) {
                    right--;
                }else{
                    ans.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])    left++;
                    while (left < right && nums[right] == nums[right - 1])  right--;
                    left++;
                    right--;
                }
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
        System.out.println(threeSum(nums));
    }
}
