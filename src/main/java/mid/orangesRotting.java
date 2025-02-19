package mid;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class orangesRotting {
    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 2) //说明是腐烂的橘子，将坐标加入队列
                    queue.offer(new int[]{i, j});
                else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if(fresh == 0)  //说明没有新鲜橘子
            return 0;

        int time = 0;
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()){
            int size = queue.size();
            boolean flag = false;   //用于标记当前遍历是否感染了新鲜橘子
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dire : direction) {
                    int x = cur[0] + dire[0];
                    int y = cur[1] + dire[1];
                    //如果没越界且是新鲜橘子，就给腐烂
                    if(x >=0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                        fresh--;
                        flag = true;
                    }
                }
            }
            if(flag)
                time++;
        }
        return fresh == 0 ? time : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(orangesRotting(grid));
    }
}
