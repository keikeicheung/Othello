package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OthelloGameTest {

    private final OthelloGame othelloGame = new OthelloGame(
            "--------\n" +
                    "--------\n" +
                    "--------\n" +
                    "---OX---\n" +
                    "---XO---\n" +
                    "--------\n" +
                    "--------\n" +
                    "--------"
    );

    @Test
    public void shouldHave8x8BoardOnGameStart() {
        OthelloGame othelloGame = new OthelloGame(8);
        assertEquals(othelloGame.displayBoard(),
                "--------\n" +
                        "--------\n" +
                        "--------\n" +
                        "---OX---\n" +
                        "---XO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldStartWithPlayerXAndPlaceDiscThatTurnsAllVerticalAsPlayerX() {
        assertEquals(othelloGame.placeDisc("3d"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "---XO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldPlaceDiscWithPlayerOAfterXThatTurnsAllHorizontalAsPlayerO() {
        othelloGame.placeDisc("3d");
        assertEquals(othelloGame.placeDisc("c5"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldShowErrorOnInvalidMove() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        assertEquals(othelloGame.placeDisc("e7"),
                "Invalid move. Please try again."
        );
    }

    @Test
    public void shouldBeSamePlayerIfThePreviousMoveWasInvalid() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        assertEquals(othelloGame.placeDisc("e6"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOX---\n" +
                        "----X---\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldBeOppositePlayerTurnAfterASuccessfulMove() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        assertEquals(othelloGame.placeDisc("5f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOOO--\n" +
                        "----X---\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldTurnDiscOnPositiveGradientDiagonal() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        othelloGame.placeDisc("5f");
        assertEquals(othelloGame.placeDisc("6c"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OXOO--\n" +
                        "--X-X---\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldTurnDiscOnNegativeGradientDiagonal() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        othelloGame.placeDisc("5f");
        assertEquals(othelloGame.placeDisc("6f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OOXO--\n" +
                        "----XX--\n" +
                        "--------\n" +
                        "--------"
        );
    }

    @Test
    public void shouldTurnDiscOnPositiveGradientDiagonalWithOffset() {
        OthelloGame initialBoard = new OthelloGame(
                "--------\n" +
                        "--------\n" +
                        "----X---\n" +
                        "---OO---\n" +
                        "---OO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------");
        assertEquals(initialBoard.placeDisc("5c"),
                "--------\n" +
                        "--------\n" +
                        "----X---\n" +
                        "---XO---\n" +
                        "--XOO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }


    @Test
    public void shouldTurnDiscOnNegativeGradientDiagonalWithOffset() {
        OthelloGame initialBoard = new OthelloGame(
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---OO---\n" +
                        "---OO---\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------");
        assertEquals(initialBoard.placeDisc("5f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---OX---\n" +
                        "---OOX--\n" +
                        "--------\n" +
                        "--------\n" +
                        "--------"
        );
    }

}