package game.board;


public class NegativeDiagonalBoard implements Board {

    public int getPlayer(int[][] board, int x, int y, int i) {
        if (x == y) {
            return board[i][i];
        } else if (x > y) {
            int offset = x - y;
            return board[i][i + offset];
        } else {
            int offset = y - x;
            return board[i + offset][i];
        }

    }

    public void setPlayer(int[][] board, int x, int y, int i, int player) {
        if (x == y) {
            board[i][i] = player;
        } else if (x > y) {
            int offset = x - y;
            board[i][i + offset] = player;
        } else {
            int offset = y - x;
            board[i + offset][i] = player;
        }

    }

    public int getX(int x, int y, int i) {
        if (x > y) {
            return i + (x - y);
        } else {
            return i;
        }
    }

    public int getY(int x, int y, int i) {
        if (y > x) {
            return i + (y - x);
        } else {
            return i;
        }
    }
}
