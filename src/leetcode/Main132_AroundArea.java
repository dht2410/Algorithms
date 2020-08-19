package leetcode;

/**
 * 把一个二维char数组中被X包围的O设置成X
 * 从边界的O出发进行dfs，利用marked数组来设置是否访问到
 */
public class Main132_AroundArea {
    public void solve(char[][] board) {
        int length = board.length;
        int width = (length>0)? board[0].length:0;
        boolean[][] marked = new boolean[length][width];
        for (int i=0;i<length;i++){
            for (int j=0;j<width;j++){
                if (isBoundary(length,width,i,j)){
                    dfs(board,marked,i,j);
                }
            }
        }
        for (int i=0;i<length;i++){
            for(int j=0;j<width;j++){
                if (marked[i][j]){
                    board[i][j]='O';
                    System.out.println(board[i][j]);
                }
                else {
                    board[i][j]='X';
                }
            }
        }
    }
    private boolean isBoundary(int length, int width, int i, int j){
        if (i==0 || i==length-1 || j==0 || j==width-1){
            return true;
        }
        return false;
    }
    private void dfs(char[][] board, boolean[][] marked, int i, int j){
        if (i<0 || j<0 || i==board.length || j==board[0].length || board[i][j]=='X' || marked[i][j]){
            return;
        }
        marked[i][j]=true;
        for (int w=-1;w<=1;w+=2){
            dfs(board,marked,i+w,j);
            dfs(board,marked,i,j+w);
        }
    }

    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                new char[]{'X','X','X','X'},
//                new char[]{'X','O','O','X'},
//                new char[]{'X','X','O','X'},
//                new char[]{'X','O','X','X'},
//        };
        char[][] board = new char[0][0];
        Main132_AroundArea obj = new Main132_AroundArea();
        obj.solve(board);
//        for (int i=0;i<4;i++){
//            System.out.println();
//            for (int j=0;j<4;j++){
//                System.out.print(board[i][j]);
//            }
//        }
    }
}
