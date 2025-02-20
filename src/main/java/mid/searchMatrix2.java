package mid;

import java.util.Scanner;

public class searchMatrix2 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0, j = n - 1; i < m && j >= 0;){
            int temp = matrix[i][j];
            if(temp < target)
                i++;
            else if (temp > target) {
                j--;
            }else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int target = sc.nextInt();

        sc.nextLine(); // 消耗换行符
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            String line = sc.nextLine(); // 读取一行
            String[] parts = line.split(","); // 按逗号分割
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(parts[j]); // 解析整数
            }
        }
        System.out.println(searchMatrix(nums, target));
    }
}
