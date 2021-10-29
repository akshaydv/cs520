import enums.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import model.RowBlockModel;
import controller.RowGameController;
import utils.Constants;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private RowGameController game;

    @Before
    public void setUp() {
        game = new RowGameController(3, 3);
    }

    @After
    public void tearDown() {
        game = null;
    }

    @Test
    public void testNewGame() {
        assertEquals(Player.PLAYER_1, game.getGameModel().getPlayer());
        assertEquals(9, game.getGameModel().getMovesLeft());
        for (int i = 0; i < game.getGameModel().getRows(); i++) {
            for (int j = 0; j < game.getGameModel().getCols(); j++) {
                RowBlockModel block = game.getGameModel().getBlocksData()[i][j];
                Assert.assertEquals("", block.getContents());
                assertTrue(block.getIsLegalMove());
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBlockViolatesPrecondition() {
        RowBlockModel block = new RowBlockModel(null);
    }

    @Test
    public void testLegalMove() {
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_1);
        Assert.assertEquals(Constants.PLAYER_2_TURN, game.getGameView().getPlayerTurn().getText());
        assertFalse(game.getGameModel().getBlocksData()[0][0].getIsLegalMove());
        Assert.assertEquals(Constants.TILE_X, game.getGameModel().getBlocksData()[0][0].getContents());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalMove() {
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_1);
        assertFalse(game.getGameModel().getBlocksData()[0][0].getIsLegalMove());
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_2);
    }

    @Test
    public void testPlayer1WinningCondition() {
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[0][1], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[1][1], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[0][2], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[2][2], Player.PLAYER_1);
        Assert.assertEquals(Constants.PLAYER_1_WINS, game.getGameModel().getFinalResult());
        Assert.assertEquals(Constants.PLAYER_1_WINS, game.getGameView().getPlayerTurn().getText());
    }

    @Test
    public void testPlayer2WinningCondition() {
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[0][2], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[1][0], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[1][1], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[0][1], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[2][0], Player.PLAYER_2);
        Assert.assertEquals(Constants.PLAYER_2_WINS, game.getGameModel().getFinalResult());
        Assert.assertEquals(Constants.PLAYER_2_WINS, game.getGameView().getPlayerTurn().getText());
    }

    @Test
    public void testPlayersTie() {
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[1][0], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[0][1], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[1][1], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[1][2], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[0][2], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[2][0], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[2][1], Player.PLAYER_2);
        game.move(game.getGameView().getBlocks()[2][2], Player.PLAYER_1);
        Assert.assertEquals(Constants.GAME_END_NO_WINNER, game.getGameModel().getFinalResult());
        Assert.assertEquals(Constants.GAME_END_NO_WINNER, game.getGameView().getPlayerTurn().getText());
    }

    @Test
    public void testResetGame() {
        game.move(game.getGameView().getBlocks()[0][0], Player.PLAYER_1);
        game.move(game.getGameView().getBlocks()[0][1], Player.PLAYER_2);
        game.resetGame();
        for (int i = 0; i < game.getGameModel().getRows(); i++) {
            for (int j = 0; j < game.getGameModel().getCols(); j++) {
                RowBlockModel block = game.getGameModel().getBlocksData()[i][j];
                Assert.assertEquals("", block.getContents());
                assertTrue(block.getIsLegalMove());
            }
        }
    }

    @Test
    public void testController() {
        game.endGame();
        for (int i = 0; i < game.getGameModel().getRows(); i++) {
            for (int j = 0; j < game.getGameModel().getCols(); j++) {
                RowBlockModel block = game.getGameModel().getBlocksData()[i][j];
                Assert.assertEquals("", block.getContents());
                assertFalse(block.getIsLegalMove());
            }
        }
    }

    @Test
    public void testView() {
        game.getGameModel().getBlocksData()[0][0].setContents(Constants.TILE_X);
        game.getGameModel().getBlocksData()[0][0].setIsLegalMove(false);
        game.getGameView().updateBlock(game.getGameModel(), 0, 0);
        Assert.assertEquals(Constants.TILE_X, game.getGameView().getBlocks()[0][0].getText());
    }

    @Test
    public void testRowGameModel() {
        game = new RowGameController(5, 5);
        Assert.assertEquals(25, game.getGameModel().getMovesLeft());
        Assert.assertNotNull(game.getGameModel().getBlocksData());
        Assert.assertEquals(Player.PLAYER_1, game.getGameModel().getPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRowBlockModel() {
        RowBlockModel block = new RowBlockModel(game.getGameModel());
        block.setContents(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRowBlockModelIllegal() {
        RowBlockModel block = new RowBlockModel(game.getGameModel());
        block.setIsLegalMove(false);
        block.setContents(Constants.TILE_X);
    }
}
