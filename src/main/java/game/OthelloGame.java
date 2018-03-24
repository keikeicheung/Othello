package game;

import game.board.*;

public class OthelloGame {
    private final int boardSize;
    private final int[][] board;
    private final String yPos = "12345678";
    private final String xPos = "abcdefgh";
    private final Player[] players = new Player[]{Player.X, Player.O};
    private int currentPlayerIndex = 0;

    public OthelloGame(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];
        board[yPos.indexOf('4')][xPos.indexOf('d')] = 2;
        board[yPos.indexOf('4')][xPos.indexOf('e')] = 1;
        board[yPos.indexOf('5')][xPos.indexOf('d')] = 1;
        board[yPos.indexOf('5')][xPos.indexOf('e')] = 2;
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

    }

    public String displayBoard() {
        StringBuilder displayResultBuilder = new StringBuilder();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                int playerIndex = board[y][x] - 1;
                String playerDisplay = "-";
                if (playerIndex > -1) {
                    playerDisplay = players[playerIndex].name();
                }
                displayResultBuilder.append(playerDisplay);
            }
            if (y < board.length - 1) {
                displayResultBuilder.append("\n");
            }
        }
        return displayResultBuilder.toString();
    }

    public String placeDisc(String coordinate) {
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
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[y][x] != 0) {
            return "Invalid move. Please try again.";
        }
        VerticalColumnBoard verticalBoard = new VerticalColumnBoard();
        HorizontalRowBoard horizontalBoard = new HorizontalRowBoard();
        PositiveDiagonalBoard positiveDiagonalBoard = new PositiveDiagonalBoard(boardSize);
        NegativeDiagonalBoard negativeDiagonalBoard = new NegativeDiagonalBoard();
        boolean verticalValid = isValidMove(x, y, currentPlayerIndex + 1, verticalBoard);
        boolean horizontalValid = isValidMove(x, y, currentPlayerIndex + 1, horizontalBoard);
        boolean positiveDiagonalValid = isValidMove(x, y, currentPlayerIndex + 1, positiveDiagonalBoard);
        boolean negativeDiagonalValid = isValidMove(x, y, currentPlayerIndex + 1, negativeDiagonalBoard);
        if (!verticalValid && !horizontalValid && !positiveDiagonalValid && !negativeDiagonalValid) {
            return "Invalid move. Please try again.";
        }
        board[y][x] = currentPlayerIndex + 1;
        if (verticalValid) {
            updateDisc(x, y, currentPlayerIndex + 1, verticalBoard);
        }
        if (horizontalValid) {
            updateDisc(x, y, currentPlayerIndex + 1, horizontalBoard);
        }
        if (positiveDiagonalValid) {
            updateDisc(x, y, currentPlayerIndex + 1, positiveDiagonalBoard);
        }
        if (negativeDiagonalValid) {
            updateDisc(x, y, currentPlayerIndex + 1, negativeDiagonalBoard);
        }
        currentPlayerIndex += 1;
        if (currentPlayerIndex >= players.length) {
            currentPlayerIndex = 0;
        }
        return displayBoard();
    }

    private void updateDisc(int x, int y, int player, Board board) {
        updateBoard(x, y, player, board);
    }

    private void updateBoard(int x, int y, int player, Board updateBoard) {
        int start = -1;
        int end = -1;
        boolean hasMatched = false;
        for (int i = 0; i < boardSize; i++) {
            int currentX = updateBoard.getX(x, y, i);
            int currentY = updateBoard.getY(x, y, i);
            if (currentX < 0 || currentY < 0 || currentX >= boardSize || currentY >= boardSize) {
                break;
            }
            if (updateBoard.getPlayer(board, x, y, i) == player && start == -1) {
                start = i;
            }
            if (isOppositePlayer(updateBoard.getPlayer(board, x, y, i), player) && start > -1) {
                hasMatched = true;
            }
            if (hasMatched) {
                end = i;
                break;
            } else if (hasMatched && updateBoard.getPlayer(board, x, y, i) == 0) {
                hasMatched = false;
            }
        }
        if (hasMatched) {
            for (int i = start + 1; i <= end; i++) {
                updateBoard.setPlayer(board, x, y, i, player);
            }
        }
    }

    private boolean isValidMove(int x, int y, int player, Board checkBoard) {
        int start = -1;
        int end = -1;
        boolean hasMatched = false;
        for (int i = 0; i < boardSize; i++) {
            int currentX = checkBoard.getX(x, y, i);
            int currentY = checkBoard.getY(x, y, i);
            if (currentX < 0 || currentY < 0 || currentX >= boardSize || currentY >= boardSize) {
                break;
            }
            if ((checkBoard.getPlayer(board, x, y, i) == player || (x == currentX && y == currentY)) && start == -1) {
                start = i;
            }
            if (isOppositePlayer(checkBoard.getPlayer(board, x, y, i), player) && start > -1) {
                hasMatched = true;
            }
            if (hasMatched && (checkBoard.getPlayer(board, x, y, i) == player || (x == currentX && y == currentY))) {
                end = i;
                break;
            } else if (hasMatched && checkBoard.getPlayer(board, x, y, i) == 0) {
                hasMatched = false;
            }
        }
        return hasMatched && start > -1 && end > -1;
    }


    private boolean isOppositePlayer(int playerOnBoard, int currentPlayer) {
        return playerOnBoard != currentPlayer && playerOnBoard != 0;
    }

}
