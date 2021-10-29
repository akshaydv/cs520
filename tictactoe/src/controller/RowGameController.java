package controller;

import javax.swing.JButton;

import enums.Player;
import model.RowGameModel;
import utils.Constants;
import view.RowGameGUI;

public class RowGameController {
	private final RowGameModel gameModel;
	private final RowGameGUI gameView;

	/**
     * Creates a new game initializing the GUI.
     */
    public RowGameController() {
	gameModel = new RowGameModel(3, 3);
	gameView = new RowGameGUI(this);

        for(int row = 0; row<3; row++) {
            for(int column = 0; column<3 ;column++) {
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
     */
    public void move(JButton block) {
	gameModel.setMovesLeft(gameModel.getMovesLeft()-1);
	if(gameModel.getMovesLeft()%2 == 1) {
	    gameView.getPlayerTurn().setText(Constants.PLAYER_1_TURN);
	} else{
	    gameView.getPlayerTurn().setText(Constants.PLAYER_2_TURN);
	}
	
	if(gameModel.getPlayer() == Player.PLAYER_1) {
	    // Check whether player 1 won
	    if(block==gameView.getBlocks()[0][0]) {
		gameModel.getBlocksData()[0][0].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,0,0);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[0][1]) {
		gameModel.getBlocksData()[0][1].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,0,1);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[0][2]) {
		gameModel.getBlocksData()[0][2].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,0,2);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[1][0]) {
		gameModel.getBlocksData()[1][0].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,1,0);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[1][1]) {
		gameModel.getBlocksData()[1][1].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,1,1);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[1][2]) {
		gameModel.getBlocksData()[1][2].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,1,2);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[2][0]) {
		gameModel.getBlocksData()[2][0].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,2,0);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[2][1]) {
		gameModel.getBlocksData()[2][1].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,2,1);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()) &&
			gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[2][2]) {
		gameModel.getBlocksData()[2][2].setContents(Constants.TILE_X);
		gameView.updateBlock(gameModel,2,2);
		gameModel.setPlayer(Player.PLAYER_2);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_1_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    }
	} else {
	    // Check whether player 2 won
	    if(block==gameView.getBlocks()[0][0]) {
		gameModel.getBlocksData()[0][0].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,0,0);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[0][1]) {
		gameModel.getBlocksData()[0][1].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,0,1);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[0][2]) {
		gameModel.getBlocksData()[0][2].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,0,2);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[1][0]) {
		gameModel.getBlocksData()[1][0].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,1,0);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[1][1]) {
		gameModel.getBlocksData()[1][1].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,1,1);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[1][2]) {
		gameModel.getBlocksData()[1][2].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,1,2);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[2][0]) {
		gameModel.getBlocksData()[2][0].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,2,0);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[2][1]) {
		gameModel.getBlocksData()[2][1].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,2,1);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()) &&
			gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    } else if(block==gameView.getBlocks()[2][2]) {
		gameModel.getBlocksData()[2][2].setContents(Constants.TILE_O);
		gameView.updateBlock(gameModel,2,2);
		gameModel.setPlayer(Player.PLAYER_1);
		if(gameModel.getMovesLeft()<7) {
		    if((gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()))) {
			gameModel.setFinalResult(Constants.PLAYER_2_WINS);
			endGame();
		    } else if(gameModel.getMovesLeft()==0) {
			gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);
		    }
		    if (gameModel.getFinalResult() != null) {
			gameView.getPlayerTurn().setText(gameModel.getFinalResult());
		    }
		}
	    }
	}
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
	for(int row = 0;row<3;row++) {
	    for(int column = 0;column<3;column++) {
		gameView.getBlocks()[row][column].setEnabled(false);
	    }
	}
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for(int row = 0;row<3;row++) {
            for(int column = 0;column<3;column++) {
                gameModel.getBlocksData()[row][column].reset();
		gameModel.getBlocksData()[row][column].setIsLegalMove(true);
		gameView.updateBlock(gameModel,row,column);
            }
        }
        gameModel.setPlayer(Player.PLAYER_1);
        gameModel.setMovesLeft(9);
        gameView.getPlayerTurn().setText(Constants.GAME_START);
    }
}
