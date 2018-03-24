package game.board;


public class NegativeDiagonalBoard implements Board {

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
