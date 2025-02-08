package mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class merge {
    public static int[][] merge(int[][] intervals) {
        ArrayList<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));

        for (int[] p : intervals) {
            int size = list.size();
            if(size > 0 && list.get(size - 1)[1] >= p[0]){
                list.get(size - 1)[1] = Math.max(list.get(size - 1)[1], p[1]);
            }else
                list.add(p);
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = sc.nextInt();
            nums[i][1] = sc.nextInt();
        }
        System.out.println(Arrays.deepToString(merge(nums)));   //多维数组输出字符串的函数——deepToString
    }
}
