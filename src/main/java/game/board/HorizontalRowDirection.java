package game.board;


public class HorizontalRowDirection implements direction {

    public int getX(int x, int y, int i) {
        return i;
    }

    public int getY(int x, int y, int i) {
        return y;
    }
}
