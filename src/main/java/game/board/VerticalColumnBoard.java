package game.board;

public class VerticalColumnBoard implements Board {

    public int getPlayer(int[][] board, int x, int y, int i) {
        return board[i][x];
    }

    public void setPlayer(int[][] board, int x, int y, int i, int player) {
        board[i][x] = player;
    }

    public int getX(int x, int y, int i) {
        return x;
    }

    public int getY(int x, int y, int i) {
        return i;
    }
}
