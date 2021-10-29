package utils;

import enums.Player;

public class RowGameUtils {

    /**
     * Gets the content that is supposed to be filled in block on click by player
     *
     * @param player - Player who has clicked on the block
     * @return - string of the tile
     */
    public static String getTileContent(Player player) {
        if (player == Player.PLAYER_1) {
            return Constants.TILE_X;
        }
        return Constants.TILE_O;
    }

    /**
     * Gets the final result based on the player won
     *
     * @param player - player who has won
     * @return - string of the text to be displayed on player win
     */
    public static String getFinalResult(Player player) {
        if (player == Player.PLAYER_1) {
            return Constants.PLAYER_1_WINS;
        }
        return Constants.PLAYER_2_WINS;
    }
}
