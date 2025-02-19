import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGameBoard {
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 700;
    private ActionListener eventListener;
    private ActionListener restartActionListener;
    public JLabel textLabel;
    public JButton restartLabel;
    public JButton[][] board = new JButton[3][3];

    public TicTacToeGameBoard(ActionListener tileEventListener, ActionListener restartEventListener) {
        this.eventListener = tileEventListener;
        this.restartActionListener = restartEventListener;
        JFrame frame = constructFrameBase();
        JPanel textPanel = constructTextPanel();
        JPanel boardPanel = constructBoardPanel();
        JPanel restartPanel = constructRestartPanel();
        frame.add(textPanel, BorderLayout.NORTH); 
        frame.add(boardPanel);
        frame.add(restartPanel, BorderLayout.SOUTH);
        constructAndAddButtons(boardPanel);
    }

    private JFrame constructFrameBase() {
        JFrame frame = new JFrame("Tic Tac Toe Minigame");
        frame.setVisible(true);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    private JLabel constructTextLabel() {
        textLabel = new JLabel();
        textLabel.setBackground(Color.pink);
        textLabel.setForeground(Color.white); 
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER); 
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);
        return textLabel;
    }

    public JButton constructRestartLabel() {
        restartLabel = new JButton();
        restartLabel.addActionListener(restartActionListener);
        restartLabel.setBackground(Color.pink);
        restartLabel.setForeground(Color.white);
        restartLabel.setFont(new Font("Arial", Font.BOLD, 50));
        restartLabel.setHorizontalAlignment(JLabel.CENTER); 
        restartLabel.setText("RESTART");
        restartLabel.setOpaque(true);
        return restartLabel;
    }

    private JPanel constructTextPanel() {
        JLabel textLabel = constructTextLabel();
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel); 
        return textPanel;
    }

    private JPanel constructRestartPanel() {
        JButton restartLabel = constructRestartLabel();
        JPanel restartPanel = new JPanel();
        restartPanel.setLayout(new BorderLayout());
        restartPanel.add(restartLabel);
        return restartPanel;
    }

    private JPanel constructBoardPanel() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.pink);
        return boardPanel;
    }

    private void constructAndAddButtons(JPanel boardPanel) {
        for (int r=0; r < 3; r++){ //r = row
            for (int c = 0; c < 3; c++) {
                JButton tile = createTile();
                board [r][c] = tile;
                boardPanel.add(tile);
            }
        }
    }

    private JButton createTile() {
        JButton tile = new JButton();
        styleTile(tile);
        addEventListenerToTile(tile);
        return tile;
    }

    private void addEventListenerToTile(JButton tile) {
        tile.addActionListener(eventListener);
    }

    private void styleTile(JButton tile) {
        tile.setBackground(Color.pink);
        tile.setForeground(Color.white);
        tile.setFont(new Font("Arial", Font.BOLD, 120));
        tile.setFocusable(false);
    }
}
