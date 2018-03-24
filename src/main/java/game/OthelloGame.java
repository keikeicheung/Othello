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
        this.boardSize = rows.length;
        this.board = new int[boardSize][boardSize];
        for (int y = 0; y < rows.length; y++) {
            char[] charArray = rows[y].toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                if (charArray[x] == 'X') {
                    board[y][x] = 1;
                } else if (charArray[x] == 'O') {
                    board[y][x] = 2;
                }
            }
        }
        boards = Arrays.asList(new VerticalColumnBoard(), new HorizontalRowBoard(), new PositiveDiagonalBoard(boardSize), new NegativeDiagonalBoard());
    }

    public String displayBoard() {
        StringBuilder displayResultBuilder = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                String playerDisplay = "-";
                if (board[y][x] > 0) {
                    playerDisplay = Player.getPlayerByIntValue(board[y][x]).name();
                }
                displayResultBuilder.append(playerDisplay);
            }
            if (y < board.length - 1) {
                displayResultBuilder.append("\n");
            }
        }
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

    private List<Coordinate> getCoordinatesToBeUpdated(int x, int y, int player, Board updateBoard) {
        int start = -1;
        int end = -1;
        boolean hasMatched = false;
        boolean hasMatchedWithNewlyPlaced = false;
        for (int i = 0; i < boardSize; i++) {
            int currentX = updateBoard.getX(x, y, i);
            int currentY = updateBoard.getY(x, y, i);
            if (currentX < 0 || currentY < 0 || currentX >= boardSize || currentY >= boardSize) {
                break;
            }
            int discOnBoard = board[currentY][currentX];
            if (hasMatchedWithNewlyPlaced && start > -1 && !hasMatched && !Player.isOppositePlayer(discOnBoard, player)) {
                break;
            }
            if (x == currentX && y == currentY) {
                hasMatchedWithNewlyPlaced = true;
            }
            if (discOnBoard == player && (!hasMatchedWithNewlyPlaced)) {
                start = i;
                hasMatched = false;
            }

            if (!hasMatched && start > -1 && discOnBoard == 0) {
                start = -1;
            }
            if ((discOnBoard == player || (x == currentX && y == currentY)) && start == -1) {
                start = i;
                hasMatched = false;
            }
            if (Player.isOppositePlayer(discOnBoard, player) && start > -1) {
                hasMatched = true;
            }
            if (hasMatched && (discOnBoard == player || (x == currentX && y == currentY))) {
                if (hasMatchedWithNewlyPlaced) {
                    end = i;
                    break;
                } else {
                    start = i;
                    hasMatched = false;
                }

            } else if (hasMatched && discOnBoard == 0) {
                hasMatched = false;
            }
        }
        List results = new ArrayList();
        if (hasMatched && start > -1 && end > -1) {
            for (int i = start + 1; i <= end; i++) {
                int currentX = updateBoard.getX(x, y, i);
                int currentY = updateBoard.getY(x, y, i);
                results.add(new Coordinate(currentX, currentY));
            }
        }
        return results;
    }

}
