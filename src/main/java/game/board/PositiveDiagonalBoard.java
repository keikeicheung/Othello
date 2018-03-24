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
        }
        return 0;
    }

    public void setPlayer(int[][] board, int x, int y, int i, int player) {
        int lastPtr = boardSize - 1;
        if (x == lastPtr - y) {
            board[lastPtr - i][i] = player;
        }
    }

    public int getX(int x, int y, int i) {
        if (x > (boardSize - 1 - y)) {
            return i - boardSize - 1 + y;
        } else {
            return i;
        }
    }

    public int getY(int x, int y, int i) {
        if (x < (boardSize - 1 - y)) {
            return boardSize - 1 + y - x - i;
        } else {
            return boardSize - 1 - i;
        }
    }
}
