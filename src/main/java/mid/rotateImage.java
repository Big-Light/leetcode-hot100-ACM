package mid;

import java.util.Arrays;
import java.util.Scanner;

public class rotateImage {
    public static void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l <= r){
                int temp = matrix[i][l];
                matrix[i][l++] = matrix[i][r];
                matrix[i][r--] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextInt();
            }
        }
        System.out.println(Arrays.deepToString(nums));
        rotate(nums);
        System.out.println(Arrays.deepToString(nums));
    }
}
