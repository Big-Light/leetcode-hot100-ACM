package mid;

import java.util.Scanner;

public class search {
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            int temp = nums[mid];
            if(temp == target)
                return mid;
            else if(temp <= nums[r]){
                if(temp < target && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }else {
                if(target >= nums[l] && target < temp)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }
        return -1;
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
        System.out.println(search(nums, target));
    }
}
