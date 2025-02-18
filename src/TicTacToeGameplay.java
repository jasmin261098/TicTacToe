import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGameplay {
    private final String PLAYER_X = "X";
    private final String PLAYER_O = "O";
    private int turns = 0;
    private boolean gameOver = false;
    private String currentPlayer = PLAYER_X;
    private TicTacToeGameBoard gameBoard;
    private ActionListener eventListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            actionConditionsAndPlayOn(e);
        }
    };

    public TicTacToeGameplay() {
        this.gameBoard = new TicTacToeGameBoard(eventListener);
    }

    private void actionConditionsAndPlayOn(ActionEvent e) {
        if (gameOver) return;
        JButton tile = (JButton) e.getSource();
        if (tile.getText() == "") {
            tile.setText(currentPlayer);
            turns++; 
            checkWinner();
            alternatePlayers();
        }
    }

    private void alternatePlayers() {
        if (!gameOver) {
            if (currentPlayer == PLAYER_X) {
                currentPlayer = PLAYER_O;
            } else {
                currentPlayer = PLAYER_X;
            } 
            gameBoard.textLabel.setText(currentPlayer + "'s turn.");
        }
    }

    private void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (gameBoard.board[r][0].getText() == "") continue; //if the first box is empty its impossible to win so we continue

            if (gameBoard.board[r][0].getText() == gameBoard.board[r][1].getText() &&
                gameBoard.board[r][1].getText() == gameBoard.board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(gameBoard.board[r][i]);
                }
                gameOver = true;
                return;
                }
        }
        
        //vertical
        for (int c = 0; c < 3; c++) {
            if (gameBoard.board[0][c].getText() == "") continue;

            if (gameBoard.board[0][c].getText() == gameBoard.board[1][c].getText() &&
                gameBoard.board[1][c].getText() == gameBoard.board[2][c].getText()) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(gameBoard.board[i][c]);
                    }
                gameOver = true;
                return;
                }
        }
        
        //diagonally
        if (gameBoard.board[0][0].getText() == gameBoard.board[1][1].getText() &&
            gameBoard.board[1][1].getText() == gameBoard.board[2][2].getText() &&
            gameBoard.board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(gameBoard.board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally
        if (gameBoard.board[0][2].getText() == gameBoard.board[1][1].getText() &&
            gameBoard.board[1][1].getText() == gameBoard.board[2][0].getText() &&
            gameBoard.board[0][2].getText() != "") {
            setWinner(gameBoard.board[0][2]);
            setWinner(gameBoard.board[1][1]);
            setWinner(gameBoard.board[2][0]);
            gameOver = true;
            return;
        }

        //tie
        if(turns == 9) {
            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    setTie(gameBoard.board[r][c]);
                }
            }
            gameOver = true;
        }

    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.pink);
        tile.setBackground(Color.orange);
        gameBoard.textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.pink);
        gameBoard.textLabel.setText("Tie!");
    }
}
