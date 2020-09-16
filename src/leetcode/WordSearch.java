package leetcode;

public class WordSearch {
    boolean ans = false;
    int[] dX = new int[]{0,0,-1,1};
    int[] dY = new int[]{-1,1,0,0};
    public boolean exist(char[][] board, String word) {
        int len = board.length;
        int wid = board[0].length;
        char firstChar = word.charAt(0);
        for (int x = 0;x<len;x++){
            for (int y=0;y<wid;y++){
                boolean[][] visited = new boolean[len][wid];
                if (board[x][y]==firstChar){
                    dfs(board,visited,word,x,y,0);
                    if (ans) return true;
                }
            }
        }
        return false;
    }

    private void dfs(char[][] board, boolean[][] visited, String word, int x, int y, int index){
        if (x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]!=word.charAt(index) ||visited[x][y]){
            return;
        }
        if (index==word.length()-1) {
            ans=true;
            return;
        }
        visited[x][y] = true;
        for (int i=0;i<4;i++){
            int X = x+dX[i];
            int Y = y+dY[i];
            dfs(board,visited,word,X,Y,index+1);
            if (ans) return;
        }
        visited[x][y] = false;
    }
}
