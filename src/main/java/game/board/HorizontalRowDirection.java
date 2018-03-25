package game.board;


public class HorizontalRowDirection implements Direction {

    public int getX(int x, int y, int i) {
        return i;
    }

    public int getY(int x, int y, int i) {
        return y;
    }
}
