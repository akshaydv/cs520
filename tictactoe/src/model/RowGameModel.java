package model;


import enums.Player;

public class RowGameModel
{

    private final RowBlockModel[][] blocksData;

    /**
     * The current player taking their turn
     */
    private Player player = Player.PLAYER_1;
    private int movesLeft;

    private String finalResult = null;

    public RowGameModel(int rows, int cols) {
    super();

        this.movesLeft = rows * cols;
    blocksData = new RowBlockModel[rows][cols];

    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
        blocksData[row][col] = new RowBlockModel(this);
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
}
