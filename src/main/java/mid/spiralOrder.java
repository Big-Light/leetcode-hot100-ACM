package mid;

import java.util.*;

public class spiralOrder {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = n - 1, s = 0, x = m - 1;
        int count = 0;
        while (count <= m * n){
            for (int i = l; i <= r; i++){
                ans.add(matrix[s][i]);
                if(++count == m * n)
                    return ans;
            }
            s++;

            for (int i = s; i <= x; i++){
                ans.add(matrix[i][r]);
                if(++count == m * n)
                    return ans;
            }
            r--;

            for (int i = r; i >= l; i--){
                ans.add(matrix[x][i]);
                if(++count == m * n)
                    return ans;
            }
            x--;

            for (int i = x; i >= s; i--){
                ans.add(matrix[i][l]);
                if(++count == m * n)
                    return ans;
            }
            l++;
        }
        return ans;
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
        System.out.println(spiralOrder(nums));
    }
}
