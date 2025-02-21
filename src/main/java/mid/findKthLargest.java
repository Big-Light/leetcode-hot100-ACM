package mid;

import java.util.Random;
import java.util.Scanner;

public class findKthLargest {
    private static final Random rand = new Random();
    public static int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    private static void quickSort(int[] nums, int l, int r) {
        if(l < r){
            int pivotIndex = partition(nums, l, r);
            quickSort(nums, l, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, r);
        }
    }

    private static int partition(int[] nums, int l, int r) {
        int random = rand.nextInt(r - l  + 1) + l;
        swap(nums, random, l);
        int i = l + 1, j = r;
        int pivot = nums[l];
        while (true){
            while (i <= j && nums[i] < pivot)   i++;
            while (i <= j && nums[j] > pivot)   j--;
            if(i >= j)  break;
            swap(nums, i, j);
            i++;
            j--;
        }
        swap(nums, l, j);
        return j;
    }

    private static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int k = sc.nextInt();
        System.out.println(findKthLargest(nums, k));
    }

}
