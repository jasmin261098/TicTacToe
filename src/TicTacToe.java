import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3]; //creating buttons to press for X and O with a 2d array
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    //checking for a tie
    int turns = 0;

    public TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);//creates window at the center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white); //changes font color to white within the label
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER); //centers the text
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel); //adding the label to the panel
        frame.add(textPanel, BorderLayout.NORTH); //adding the panel to the frame and position it at the top (north)

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        //buttons
        for (int r=0; r < 3; r++){ //r = row
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board [r][c] = tile;
                boardPanel.add(tile);

                //styling buttons
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                //setting the text on a button to the current player when clicking a button
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //checking if its already game over
                        if (gameOver) return;

                        JButton tile = (JButton) e.getSource(); //where does the action come from
                        //checking if button is already filled out so players cant overwrite it
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++; //counting the turns
                            //checking for the winner
                            checkWinner();
                            if (!gameOver) {
                                //alternating between X and O
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                //alternating text label between players
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }
    
    //checking winning conditions
    void checkWinner() {
        
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue; //if the first box is empty its impossible to win so we continue

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
                }
        }
        
        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(board[i][c]);
                    }
                gameOver = true;
                return;
                }
        }
        
        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        //tie
        if(turns == 9) {
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }

    }

    //adjusting the design of the tile to show winner
    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    //defining setTie function
    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
