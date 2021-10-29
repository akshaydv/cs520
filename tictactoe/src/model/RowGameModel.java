package model;


import enums.Player;

/**
 * Violation 4 - MVC Architecture
 * RowGameModel is the model. It is waiting for updates from controller and does not interact with the view
 */
public class RowGameModel {

    private final RowBlockModel[][] blocksData;

    private int rows;

    private int cols;

    /**
     * The current player taking their turn
     */
    private Player player = Player.PLAYER_1;
    private int movesLeft;

    private String finalResult = null;

    public RowGameModel(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;

        this.movesLeft = this.rows * this.cols;
        blocksData = new RowBlockModel[this.rows][this.cols];

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                blocksData[row][col] = new RowBlockModel();
            } // end for col
        } // end for row
    }

    public String getFinalResult() {
        return this.finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public RowBlockModel[][] getBlocksData() {
        return blocksData;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public void decrementMoves() {
        this.movesLeft--;
    }

    public void swapPlayer() {
        if (this.player == Player.PLAYER_1) {
            this.player = Player.PLAYER_2;
        } else {
            this.player = Player.PLAYER_1;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
