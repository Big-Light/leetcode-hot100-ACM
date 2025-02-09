package mid;

import java.util.Arrays;
import java.util.Scanner;

public class setZeroes {
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row = false, col = false;
        for (int i = 0; i < n; i++) {
            if(matrix[0][i] == 0)
                row = true;
        }
        for (int i = 0; i < m; i++) {
            if(matrix[i][0] == 0)
                col = true;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if(matrix[0][i] == 0)
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
        }
        for (int i = 1; i < m; i++) {
            if(matrix[i][0] == 0)
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
        }
        if(row)
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        if(col)
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] nums = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                nums[i][j] = sc.nextInt();
            }
        }
        System.out.println(Arrays.deepToString(nums));  //输入时记得还是先输入行列数，然后数据直接都用空格
        setZeroes(nums);                                //比如：3 4 0 1 2 0 3 4 5 2 1 3 1 5
        System.out.println(Arrays.deepToString(nums));
    }
}
