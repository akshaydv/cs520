package utils;

import enums.Player;

public class RowGameUtils {

    public static String getContent(Player player) {
        if (player == Player.PLAYER_1) {
            return Constants.TILE_X;
        }
        return Constants.TILE_O;
    }

    public static String getFinalResult(Player player) {
        if (player == Player.PLAYER_1) {
            return Constants.PLAYER_1_WINS;
        }
        return Constants.PLAYER_2_WINS;
    }
}
