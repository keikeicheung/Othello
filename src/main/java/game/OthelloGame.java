package game;


public class OthelloGame {
    private final int boardSize;
    private final int[][] board;
    private final String xPos = "12345678";
    private final String yPos = "abcdefgh";
    private final String[] players = new String[]{"-", "X", "O"};

    public OthelloGame(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];
    }

    public String displayBoard() {
        StringBuilder displayResultBuilder = new StringBuilder();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                displayResultBuilder.append(players[board[x][y]]);
            }
            if (x < board.length - 1) {
                displayResultBuilder.append("\n");
            }
        }
        return displayResultBuilder.toString();
    }

    public void placeDisc(String coordinate) {
        char[] positions = coordinate.toCharArray();
        int x = -1;
        int y = -1;
        for (int i = 0; i < positions.length; i++) {
            if (xPos.indexOf(positions[i]) > -1) {
                x = xPos.indexOf(positions[i]);
            } else {
                y = yPos.indexOf(positions[i]);
            }
        }
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            throw new InvalidInputException();
        }
        board[x][y] = 1;
    }
}
