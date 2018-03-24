package game;

import game.board.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OthelloGame {
    private final int boardSize;
    private final int[][] board;
    private final List<Board> boards;

    private int currentPlayerIndex = 0;

    public OthelloGame(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
        boards = Arrays.asList(new VerticalColumnBoard(), new HorizontalRowBoard(), new PositiveDiagonalBoard(boardSize), new NegativeDiagonalBoard());
    }

    public OthelloGame(String boardDisplay) {
        String[] rows = boardDisplay.split("\n");
        this.boardSize = rows.length - 1;
        this.board = new int[boardSize][boardSize];
        for (int y = 0; y < boardSize; y++) {
            char[] charArray = rows[y].toCharArray();
            for (int x = 2; x < charArray.length; x++) {
                if (charArray[x] == 'X') {
                    board[y][x - 2] = 1;
                } else if (charArray[x] == 'O') {
                    board[y][x - 2] = 2;
                }
            }
        }
        boards = Arrays.asList(new VerticalColumnBoard(), new HorizontalRowBoard(), new PositiveDiagonalBoard(boardSize), new NegativeDiagonalBoard());
    }

    public String displayBoard() {
        StringBuilder displayResultBuilder = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            displayResultBuilder.append(y + 1 + " ");
            for (int x = 0; x < board[y].length; x++) {
                String playerDisplay = "-";
                if (board[y][x] > 0) {
                    playerDisplay = Player.getPlayerByIntValue(board[y][x]).name();
                }
                displayResultBuilder.append(playerDisplay);
            }
            displayResultBuilder.append("\n");
        }
        displayResultBuilder.append("  abcdefgh");
        return displayResultBuilder.toString();
    }

    public String nextStep() {
        if (haveFurtherMovesAvailable()) {
            return "Next Player's Turn, Player " + Player.getPlayerByIntValue(currentPlayerIndex + 1);
        } else {
            return "No further moves available\n" + getResultSummary();
        }
    }

    private String getResultSummary() {
        int playerXScore = 0;
        int playerOScore = 0;
        for (int y = 0; y < board.length; y++) {
            int[] row = board[y];
            for (int x = 0; x < row.length; x++) {
                Player player = Player.getPlayerByIntValue(row[x]);
                if (player == Player.X) {
                    playerXScore += 1;
                } else if (player == Player.O) {
                    playerOScore += 1;
                }
            }
        }
        if (playerXScore > playerOScore) {
            return "Player 'X' wins ( " + playerXScore + " vs " + playerOScore + " )";
        } else if (playerOScore > playerXScore) {
            return "Player 'O' wins ( " + playerOScore + " vs " + playerXScore + " )";
        } else {
            return "No player wins ( " + playerXScore + " vs " + playerOScore + " )";
        }

    }

    public boolean haveFurtherMovesAvailable() {
        boolean emptyCell = false;
        for (int y = 0; y < board.length; y++) {
            int[] row = board[y];
            for (int x = 0; x < row.length; x++) {
                if (row[x] == 0) {
                    emptyCell = true;
                    break;
                }
            }
        }
        return emptyCell;
    }

    public String placeDisc(String coordinateStr, Player player) {
        currentPlayerIndex = player.getIntValue() - 1;
        return placeDisc(coordinateStr);
    }

    public String placeDisc(String coordinateStr) {
        Coordinate coordinate = Coordinate.parseCoordinate(coordinateStr);
        if (coordinate.isValidPositionToPlaceDisc(boardSize) || board[coordinate.getY()][coordinate.getX()] != 0) {
            return "Invalid move. Please try again.";
        }
        List<Coordinate> results = new ArrayList<Coordinate>();
        for (Board board : boards) {
            results.addAll(getCoordinatesToBeUpdated(coordinate.getX(), coordinate.getY(), currentPlayerIndex + 1, board));
        }
        if (results.size() == 0) {
            return "Invalid move. Please try again.";
        }
        board[coordinate.getY()][coordinate.getX()] = currentPlayerIndex + 1;
        for (Coordinate coord : results) {
            board[coord.getY()][coord.getX()] = currentPlayerIndex + 1;
        }
        currentPlayerIndex += 1;
        if (currentPlayerIndex >= Player.values().length) {
            currentPlayerIndex = 0;
        }
        return displayBoard();
    }

    private List getCoordinatesToBeUpdated(int x, int y, int player, Board updateBoard) {
        int start = -1;
        int end = -1;
        boolean hasMatched = false;
        boolean hasSeenTheNewlyPlaced = false;
        List results = new ArrayList();
        for (int i = 0; i < boardSize; i++) {
            int currentX = updateBoard.getX(x, y, i);
            int currentY = updateBoard.getY(x, y, i);
            Coordinate current = new Coordinate(currentX, currentY);
            if (current.isValidPositionToPlaceDisc(boardSize)) {
                break;
            }
            int discOnBoard = board[currentY][currentX];
            if (hasSeenTheNewlyPlaced && start > -1 && !hasMatched && !Player.isOppositePlayer(discOnBoard, player)) {
                break;
            }
            if (x == currentX && y == currentY) {
                hasSeenTheNewlyPlaced = true;
            }
            if (hasMatched && (discOnBoard == player || (x == currentX && y == currentY)) && hasSeenTheNewlyPlaced) {
                end = i;
                break;
            }
            if (discOnBoard == 0) {
                start = -1;
                hasMatched = false;
            }
            if ((x == currentX && y == currentY) || discOnBoard == player) {
                start = i;
                hasMatched = false;
            }
            if (Player.isOppositePlayer(discOnBoard, player) && start > -1) {
                hasMatched = true;
            }

            if (hasMatched) {
                results.add(current);
            } else {
                results = new ArrayList();
            }
        }
        return (!hasMatched || start < 0 || end < 0) ? new ArrayList() : results;
    }

}
