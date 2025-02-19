package mid;

import java.util.Scanner;

public class numIslands {
    public static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    dfs(i, j, grid);
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void dfs(int i, int j, char[][] grid) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1')
            return;
        grid[i][j] = '0';
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j - 1,grid);
        dfs(i, j + 1,grid);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        System.out.println(numIslands(grid));
    }
}
