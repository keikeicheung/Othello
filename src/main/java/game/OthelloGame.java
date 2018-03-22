package game;

public class OthelloGame {
    private final int boardSize;

    public OthelloGame(int boardSize) {
        this.boardSize = boardSize;
    }

    public String displayBoard() {
        StringBuilder displayResultBuilder = new StringBuilder();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                displayResultBuilder.append("-");
            }
            if (y < boardSize - 1) {
                displayResultBuilder.append("\n");
            }
        }
        return displayResultBuilder.toString();
    }
}
