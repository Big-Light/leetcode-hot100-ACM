package mid;

public class exist {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int k = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(backTracking(i, j, board, word, k))
                    return true;
            }
        }
        return false;
    }

    private boolean backTracking(int i, int j, char[][] board, String word, int k) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(k) != board[i][j])
            return false;
        if(k == word.length() - 1)  //这一步要注意，因为上面没有对k的越界判断，所以这里就要有一个判真的退出
            return true;
        char tem = board[i][j];
        board[i][j] = '.';
        boolean ans = backTracking(i + 1, j, board, word, k + 1) ||
                backTracking(i - 1, j, board, word, k + 1) ||
                backTracking(i, j  + 1, board, word, k + 1) ||
                backTracking(i, j - 1, board, word, k + 1);
        board[i][j] = tem;
        return ans;
    }
}
