package game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OthelloGameTest {

    private final OthelloGame othelloGame = new OthelloGame(8);

    @Test
    public void shouldHave8x8BoardOnGameStart() {
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
    public void shouldTurnDiscOnNegativeGradientDiagonalWithOffset() {
        othelloGame.placeDisc("3d");
        othelloGame.placeDisc("c5");
        othelloGame.placeDisc("e7");
        othelloGame.placeDisc("e6");
        othelloGame.placeDisc("5f");
        othelloGame.placeDisc("6c");
        othelloGame.placeDisc("7c");
        assertEquals(othelloGame.placeDisc("6f"),
                "--------\n" +
                        "--------\n" +
                        "---X----\n" +
                        "---XX---\n" +
                        "--OXXO--\n" +
                        "--O-XX--\n" +
                        "--O-----\n" +
                        "--------"
        );
    }

}