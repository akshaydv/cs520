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
     * Creates a new game, initializes the view and model for the game
     *
     * @param rows - number of rows for the game
     * @param cols - number of cols for the game
     */
    public RowGameController(int rows, int cols) {
        gameModel = new RowGameModel(rows, cols);
        gameView = new RowGameGUI(this);

        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int column = 0; column < gameModel.getCols(); column++) {
                gameModel.getBlocksData()[row][column].setIsLegalMove(true);
                gameModel.getBlocksData()[row][column].setContents("");
                gameView.updateBlock(gameModel, row, column);
            }
        }
    }

    /**
     * Moves the current player into the given block and checks if the game is finished.
     *
     * @param block  - The block to be moved to by the current player
     * @param player - Current player making the move
     */
    public void move(JButton block, Player player) {
        gameModel.decrementMoves();
        if (gameModel.getMovesLeft() % 2 == 1) {
            gameView.getPlayerTurn().setText(Constants.PLAYER_1_TURN);
        } else {
            gameView.getPlayerTurn().setText(Constants.PLAYER_2_TURN);
        }

        // Iterate all blocks to match the block being changed
        for (int i = 0; i < gameModel.getRows(); i++) {
            for (int j = 0; j < gameModel.getCols(); j++) {

                // If block is found
                if (block == gameView.getBlocks()[i][j]) {
                    // Sets the appropriate tile value for current player
                    gameModel.getBlocksData()[i][j].setContents(RowGameUtils.getTileContent(player));

                    // Update the view to reflect the value change
                    gameView.updateBlock(gameModel, i, j);

                    // Check if there is a winner, if yes, end the game
                    checkIfGameHasEnded(player, i, j);

                    // As the operation is done, swap the player
                    gameModel.swapPlayer();

                    break;
                }
            }
        }
    }

    /**
     * Checks if there is a winner or the game has ended
     *
     * @param player - Player making the move
     * @param row    - Row of the block in which the move is done
     * @param col    - Col of the block in which the move is done
     */
    private void checkIfGameHasEnded(Player player, int row, int col) {

        // We don't need to check if there is a winner for the beginning turns
        if (shouldCheckForGameEnd()) {

            // Check if there is a winner
            if (checkWinningCondition(row, col, player)) {

                // We found a winner, set the final result
                gameModel.setFinalResult(RowGameUtils.getFinalResult(player));

                // End the game.
                endGame();
            } else if (gameModel.getMovesLeft() == 0) {

                // No moves left, so the game ends in a draw
                gameModel.setFinalResult(Constants.GAME_END_NO_WINNER);

                // End the game.
                endGame();
            }

            // If we have a final result in either a draw or win, set the text
            if (gameModel.getFinalResult() != null) {
                gameView.getPlayerTurn().setText(gameModel.getFinalResult());
            }
        }
    }

    /**
     * Checks if we need to check if there is a draw or the game has ended
     *
     * @return - boolean signifying if end game is to be checked
     */
    private boolean shouldCheckForGameEnd() {
        return gameModel.getMovesLeft() < (gameModel.getRows() * gameModel.getCols() - Constants.MIN_MOVES_FOR_WINNING_CHECK);
    }

    /**
     * Checks if someone has won
     *
     * @param row    - Block's row
     * @param col    - Block's col
     * @param player - Player who has made the move
     * @return - boolean signifying if the player has won
     */
    private boolean checkWinningCondition(int row, int col, Player player) {
        String winningString = getWinningString(player);
        return checkHorizontalWin(row, col, winningString)
                || checkVerticalWin(row, col, winningString)
                || checkDiagonalWin(row, col, winningString);
    }

    /**
     * Checks if the player has a winning condition in a diagonal of length 5 with the current element in it
     *
     * @param row           - block's row
     * @param col           - block's col
     * @param winningString - Pattern for which the player is considered the winner
     * @return -
     */
    private boolean checkDiagonalWin(int row, int col, String winningString) {

        // For cases where there are already 2 Xs or Os diagonal to the block- X
        //  starting from left                                                  X
        //                                                                       _
        //                                                                        X
        //                                                                         X

        StringBuilder leftDiagonal = new StringBuilder();

        // For cases where there are already 2 Xs or Os diagonal to the block-      X
        // starting from right                                                     X
        //                                                                        _
        //                                                                       X
        //                                                                      X
        StringBuilder rightDiagonal = new StringBuilder();


        for (int i = 1; i < Constants.WINNING_CONDITION; i++) {
            if (row - i >= 0 && col + i < gameModel.getCols()) leftDiagonal.append(getContents(row - i, col + i));
            if (row - i >= 0 && col - i >= 0) rightDiagonal.append(getContents(row - i, col - i));
        }
        leftDiagonal.append(getContents(row, col));

        rightDiagonal.append(getContents(row, col));
        for (int i = 1; i < Constants.WINNING_CONDITION; i++) {
            if (col - i >= 0 && row + i < gameModel.getRows()) leftDiagonal.append(getContents(row + i, col - i));
            if (row + i < gameModel.getRows() && col + i < gameModel.getCols())
                rightDiagonal.append(getContents(row + i, col + i));
        }

        // Check if any of the diagonals has the pattern
        return leftDiagonal.toString().contains(winningString) || rightDiagonal.toString().contains(winningString);
    }

    /**
     * Checks if the player has a winning condition in the current column
     *
     * @param row           - block's row
     * @param col           - block's col
     * @param winningString - Pattern for which the player is considered the winner
     * @return -
     */
    private boolean checkVerticalWin(int row, int col, String winningString) {

        // For cases where there are already 2 Xs or Os up the current block - X        O
        //                                                                     X   or   O
        //                                                                     _        _
        int start = Math.max(row - 2, 0);

        // For cases where there are already 2 Xs or Os up the current block - _        _
        //                                                                     X   or   O
        //                                                                     X        O
        int end = Math.min(row + 2, getGameModel().getRows() - 1);

        // Create a string from the row from 2 places up the current block to 2 places below the current block
        return checkIfWinner(col, winningString, start, end, false);
    }

    /**
     * Checks if the player has a winning condition in the current row
     *
     * @param row           - block's row
     * @param col           - block's col
     * @param winningString - Pattern for which the player is considered the winner
     * @return -
     */
    private boolean checkHorizontalWin(int row, int col, String winningString) {

        // For cases where there are already 2 Xs or Os before current block - XX_ or OO_
        int start = Math.max(col - 2, 0);

        // For cases where there are already 2 Xs or Os after current block - _XX or _OO
        int end = Math.min(col + 2, getGameModel().getCols() - 1);

        // Check if the pattern is in the string of length 5
        return checkIfWinner(row, winningString, start, end, true);
    }

    /**
     * Builds the string along the line considered and checks if the current player is a winner
     *
     * @param fixedDim      - For horizontal checks, this will be the row of the block, else it will be the column
     * @param winningString - Pattern for which the player is considered the winner
     * @param start         - start of the chunk we are considering
     * @param end           - end of the chunk we are considering
     * @param isHorizontal  - boolean to signify if we are currently considering the horizontal chunk
     * @return              - true if the player is a winner
     */
    private boolean checkIfWinner(int fixedDim, String winningString, int start, int end, boolean isHorizontal) {
        int i = start;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= start && i <= end) {
            if (isHorizontal) {
                stringBuilder.append(getContents(fixedDim, i));
            } else {
                stringBuilder.append(getContents(i, fixedDim));
            }
            i++;
        }

        // Check if the pattern is in the string of length 5
        return stringBuilder.toString().contains(winningString);
    }

    /**
     * Returns a string which the board may have in any direction for which the player is considered the winner
     * For example, if we find 3 Xs continuously in any row, column or diagonal, it means that Player 1 has won
     * Player 1 - XXX
     * Player 2 - OOO
     *
     * @param player - Player for who we are getting the string
     * @return winning string pattern for the player
     */
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
        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int column = 0; column < gameModel.getCols(); column++) {
                gameView.getBlocks()[row][column].setEnabled(false);
                gameModel.getBlocksData()[row][column].setIsLegalMove(false);
            }
        }
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int column = 0; column < gameModel.getCols(); column++) {
                gameModel.getBlocksData()[row][column].reset();
                gameModel.getBlocksData()[row][column].setIsLegalMove(true);
                gameView.updateBlock(gameModel, row, column);
            }
        }
        gameModel.setPlayer(Player.PLAYER_1);
        gameModel.setMovesLeft(gameModel.getRows() * gameModel.getCols());
        gameModel.setFinalResult(null);
        gameView.getPlayerTurn().setText(Constants.GAME_START);
    }

    public RowGameModel getGameModel() {
        return gameModel;
    }

    public RowGameGUI getGameView() {
        return gameView;
    }
}
