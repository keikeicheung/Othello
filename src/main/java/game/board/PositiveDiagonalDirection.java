package game.board;


public class PositiveDiagonalDirection implements Direction {

    private int boardSize;

    public PositiveDiagonalDirection(int boardSize) {
        this.boardSize = boardSize;
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
