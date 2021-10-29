package controller;

import javax.swing.JButton;

import enums.Player;
import model.RowGameModel;
import utils.Constants;
import utils.RowGameUtils;
import view.RowGameGUI;

/**
 * Violation 4 - MVC Architecture
 * RowGameController is the controller between model and view.
 * Changes that are received from view are being propagated to the model and vice versa.
 */
public class RowGameController {
	private final RowGameModel gameModel;
	private final RowGameGUI gameView;

	/**
     * Creates a new game initializing the GUI.
     */
    public RowGameController(int rows, int cols) {
	gameModel = new RowGameModel(rows, cols);
	gameView = new RowGameGUI(this);

        for(int row = 0; row<gameModel.getRows(); row++) {
            for(int column = 0; column<gameModel.getCols() ;column++) {
	        gameModel.getBlocksData()[row][column].setContents("");
		gameModel.getBlocksData()[row][column].setIsLegalMove(true);
		gameView.updateBlock(gameModel,row,column);
            }
        }
    }

	public RowGameModel getGameModel() {
		return gameModel;
	}

	public RowGameGUI getGameView() {
		return gameView;
	}

	/**
     * Moves the current player into the given block.
     *
	 * @param block The block to be moved to by the current player
	 * @param player
	 */
    public void move(JButton block, Player player) {
	gameModel.decrementMoves();
	if(gameModel.getMovesLeft()%2 == 1) {
	    gameView.getPlayerTurn().setText(Constants.PLAYER_1_TURN);
	} else{
	    gameView.getPlayerTurn().setText(Constants.PLAYER_2_TURN);
	}

	for (int i = 0; i < gameModel.getRows(); i++) {
		for (int j = 0; j < gameModel.getCols(); j++) {
			if (block==gameView.getBlocks()[i][j]) {
				gameModel.getBlocksData()[i][j].setContents(player);
				gameView.updateBlock(gameModel, i, j);
				gameModel.swapPlayer();
				checkIfGameHasEnded(player, i, j);
			}
		}
	}
    }

	private void checkIfGameHasEnded(Player player, int row, int col) {
		if(shouldCheckForGameEnd()) {
			if(checkWinningCondition(row, col, player)) {
				gameModel.setFinalResult(RowGameUtils.getFinalResult(player));
				endGame();
			} else if(gameModel.getMovesLeft()==0) {
				gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
			}
			if (gameModel.getFinalResult() != null) {
				gameView.getPlayerTurn().setText(gameModel.getFinalResult());
			}
		}
	}

	private boolean shouldCheckForGameEnd() {
		return gameModel.getMovesLeft() < (gameModel.getRows() * gameModel.getCols() - Constants.MIN_MOVES_FOR_WINNING_CHECK);
	}

	private boolean checkWinningCondition(int row, int col, Player player) {
		String winningString = getWinningString(player);
		return checkHorizontalWin(row, col, winningString)
				|| checkVerticalWin(row, col, winningString)
				|| checkDiagonalWin(row, col, winningString);
	}

	private boolean checkDiagonalWin(int row, int col, String winningString) {
		StringBuilder leftDiagonal  = new StringBuilder();
		StringBuilder rightDiagonal = new StringBuilder();
		for (int i = 1; i < Constants.WINNING_CONDITION; i++) {
			if (row - i >= 0 && col + i < gameModel.getCols()) leftDiagonal.append(getContents(row - i, col + i));
			if (row - i >= 0 && col - i >= 0) rightDiagonal.append(getContents(row - i, col - i));
		}
		leftDiagonal.append(getContents(row, col));
		rightDiagonal.append(getContents(row, col));
		for (int i = 1; i < Constants.WINNING_CONDITION; i++) {
			if (col - i >= 0 && row + i < gameModel.getRows()) leftDiagonal.append(getContents(row + i, col - i));
			if (row + i < gameModel.getRows() && col + i < gameModel.getCols()) rightDiagonal.append(getContents(row + i, col + i));
		}
		return leftDiagonal.toString().contains(winningString) || rightDiagonal.toString().contains(winningString);
	}

	private boolean checkVerticalWin(int row, int col, String winningString) {
		int start = row - 2;
		int end   = row + 2;
		int i = start;
		StringBuilder stringBuilder = new StringBuilder();
		while (i >= 0 && i < gameModel.getRows() && i <= end) {
			stringBuilder.append(getContents(i, col));
			i++;
		}
		return stringBuilder.toString().contains(winningString);
	}

	private boolean checkHorizontalWin(int row, int col, String winningString) {
		int start = col - 2;
		int end   = col + 2;
		int i = start;
		StringBuilder stringBuilder = new StringBuilder();
		while (i >= 0 && i < gameModel.getCols() && i <= end) {
			stringBuilder.append(getContents(row, i));
			i++;
		}
		return stringBuilder.toString().contains(winningString);
	}

	private String getWinningString(Player player) {
		if (player == Player.PLAYER_1) {
			return "XXX";
		} else {
			return "OOO";
		}
	}

	private String getContents(int i, int j) {
		String contents = gameModel.getBlocksData()[i][j].getContents();
		if (contents.isEmpty()) {
			return "-";
		}
		return contents;
	}

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
	for(int row = 0;row<gameModel.getRows();row++) {
	    for(int column = 0;column<gameModel.getCols();column++) {
		gameView.getBlocks()[row][column].setEnabled(false);
	    }
	}
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for(int row = 0;row<gameModel.getRows();row++) {
            for(int column = 0;column<gameModel.getCols();column++) {
                gameModel.getBlocksData()[row][column].reset();
		gameModel.getBlocksData()[row][column].setIsLegalMove(true);
		gameView.updateBlock(gameModel,row,column);
            }
        }
        gameModel.setPlayer(Player.PLAYER_1);
        gameModel.setMovesLeft(gameModel.getRows() * gameModel.getCols());
        gameView.getPlayerTurn().setText(Constants.GAME_START);
    }
}
