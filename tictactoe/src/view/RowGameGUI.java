package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import controller.RowGameController;
import utils.Constants;

public class RowGameGUI {
    private final JFrame gui = new JFrame(Constants.GAME_TITLE);
    private final JButton[][] blocks;
    private final JTextArea playerTurn = new JTextArea();

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameController controller) {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(controller.getGameModel().getRows(),controller.getGameModel().getCols()));
        gamePanel.add(game, BorderLayout.CENTER);

        this.blocks = new JButton[controller.getGameModel().getRows()][controller.getGameModel().getCols()];

        JPanel options = new JPanel(new FlowLayout());
        JButton reset = new JButton(Constants.RESET);
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerTurn);
        playerTurn.setText(Constants.GAME_START);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.resetGame();
            }
        });

        // Initialize a JButton for each cell of the 3x3 game board.
        for(int row = 0; row<controller.getGameModel().getRows(); row++) {
            for(int column = 0; column<controller.getGameModel().getCols() ;column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75,75));
                game.add(blocks[row][column]);
                blocks[row][column].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
			controller.move((JButton)e.getSource(), controller.getGameModel().getPlayer());
                    }
                });
            }
        }
    }

    /**
     * Updates the block at the given row and column 
     * after one of the player's moves.
     *
     * @param gameModel The RowGameModel containing the block
     * @param row The row that contains the block
     * @param column The column that contains the block
     */
    public void updateBlock(RowGameModel gameModel, int row, int column) {
	blocks[row][column].setText(gameModel.getBlocksData()[row][column].getContents());
	blocks[row][column].setEnabled(gameModel.getBlocksData()[row][column].getIsLegalMove());
    }

    public JFrame getGui() {
        return gui;
    }

    public JButton[][] getBlocks() {
        return blocks;
    }

    public JTextArea getPlayerTurn() {
        return playerTurn;
    }
}
