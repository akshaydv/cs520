import controller.RowGameController;

public class RowGameApp {
    /**
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Please provide rows and cols respectively!");
        }
        int rows = Integer.parseInt(args[0]);
        int cols = Integer.parseInt(args[1]);
        RowGameController game = new RowGameController(rows, cols);
        game.getGameView().getGui().setVisible(true);
    }
}