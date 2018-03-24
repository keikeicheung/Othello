package game.board;


public class HorizontalRowBoard implements Board {

    public int getPlayer(int[][] board, int x, int y, int i) {
        return board[y][i];
    }

    public void setPlayer(int[][] board, int x, int y, int i, int player) {
        board[y][i] = player;
    }

    public int getX(int x, int y, int i) {
        return i;
    }

    public int getY(int x, int y, int i) {
        return y;
    }
}
