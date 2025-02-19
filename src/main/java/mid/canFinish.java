package mid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class canFinish {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {  //邻接表构建图
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {       //(a,b)要想学习a得先学习b，因此构建b——>a的有向边
            graph.get(pre[1]).add(pre[0]);
        }

        int[] visited = new int[numCourses];    //状态数组，0=未访问，1=正在访问，2=已访问

        for (int i = 0; i < numCourses; i++) {  //检测每个节点是否存在环
            if(hasCycle(graph, visited, i))
                return false;
        }

        return true;
    }

    private static boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        if(visited[course] == 1)    // 如果课程已经访问过且在当前路径上，说明存在环
            return true;

        if(visited[course] == 2)     // 如果课程已经访问过且不在当前路径上，说明没有环
            return false;

        visited[course] = 1;
        for (int nextCourse : graph.get(course)) {  // 递归访问当前课程的所有依赖课程
            if(hasCycle(graph, visited, nextCourse))
                return true;
        }
        visited[course] = 2;
        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCourse = sc.nextInt();
        int preLength = sc.nextInt();
        sc.nextLine();
        int[][] pre = new int[preLength][2];
        for (int i = 0; i < preLength; i++) {
            String s = sc.nextLine();
            pre[i][0] = s.charAt(0) - '0';
            pre[i][1] = s.charAt(1) - '0';
        }

        System.out.println(canFinish(numCourse, pre));
    }
}
