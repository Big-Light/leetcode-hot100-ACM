package mid;

import java.util.Scanner;

public class searchMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        //这一题用240题搜索二维矩阵Ⅱ那个从右上角模拟二叉树的思路也可以
        //或者用这种二维矩阵映射到一维有序数组上
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l < r){
            int mid = l + (r - l) / 2;
            int row = mid / n;
            int col = mid % n;
            int temp = matrix[row][col];
            if(temp == target)
                return true;
            else if (temp < target) {
                l = mid + 1;
            }else
                r = mid - 1;
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
