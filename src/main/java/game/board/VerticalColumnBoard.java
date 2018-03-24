package game.board;

public class VerticalColumnBoard implements Board {

    public int getX(int x, int y, int i) {
        return x;
    }

    public int getY(int x, int y, int i) {
        return i;
    }
}
