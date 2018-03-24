package game.board;


public class PositiveDiagonalBoard implements Board {

    private int boardSize;

    public PositiveDiagonalBoard(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getPlayer(int[][] board, int x, int y, int i) {
        int lastPtr = boardSize - 1;
        if (x == lastPtr - y) {
            return board[lastPtr - i][i];
        } else if (x > (lastPtr - y)) {
            int offset = x - (lastPtr - y);
            return board[lastPtr - i][i + offset];
        } else {
            int offset = (lastPtr - y) - x;
            return board[lastPtr - i - offset][i];
        }
    }

    public void setPlayer(int[][] board, int x, int y, int i, int player) {
        int lastPtr = boardSize - 1;
        if (x == lastPtr - y) {
            board[lastPtr - i][i] = player;
        } else if (x > (lastPtr - y)) {
            int offset = x - (lastPtr - y);
            board[lastPtr - i][i + offset] = player;
        } else {
            int offset = (lastPtr - y) - x;
            board[lastPtr - i - offset][i] = player;
        }
    }

    public int getX(int x, int y, int i) {
        int lastPtr = boardSize - 1;
        if (x > (lastPtr - y)) {
            int offset = x - (lastPtr - y);
            return i + offset;
        } else {
            return i;
        }
    }

    public int getY(int x, int y, int i) {
        int lastPtr = boardSize - 1;
        if ((lastPtr - y) > x) {
            int offset = (lastPtr - y) - x;
            return lastPtr - i - offset;
        } else {
            return lastPtr - i;
        }
    }
}
