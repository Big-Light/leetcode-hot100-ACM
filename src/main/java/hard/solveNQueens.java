package hard;

import java.util.*;

public class solveNQueens {
    static List<List<String>> ans = new ArrayList<>();
    public static List<List<String>> solveNQueens(int n) {
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chars[i][j] = '.';
            }
        }
        backTracking(chars, 0);
        return ans;
    }

    private static void backTracking(char[][] chars, int row) {
        if(row == chars.length){
            ans.add(array2List(chars));
            return;
        }
        for (int col = 0; col < chars[0].length; col++) {
            if(isT(chars, row, col)){
                chars[row][col] = 'Q';
                backTracking(chars, row + 1);
                chars[row][col] = '.';
            }
        }
    }

    private static List<String> array2List(char[][] chars) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            ans.add(new String(chars[i]));
        }
        return ans;
    }

    private static boolean isT(char[][] chars, int row, int col) {
        int m = chars.length;
        int n = chars[0].length;

        for (int i = 0; i < n; i++) {
            if(chars[row][i] == 'Q')
                return false;
        }

        for (int i = 0; i < m; i++) {
            if(chars[i][col] == 'Q')
                return false;
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if(chars[i][j] == 'Q')
                return false;
        }

        for (int i = row, j = col; i >= 0 && j < n; i--, j++){
            if(chars[i][j] == 'Q')
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solveNQueens(n));
    }
}
