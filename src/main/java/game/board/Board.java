package game.board;

public interface Board {
    int getPlayer(int[][] board, int x, int y, int i);

    void setPlayer(int[][] board, int x, int y, int i, int player);

    int getX(int x, int y, int i);

    int getY(int x, int y, int i);


}
