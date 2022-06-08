import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import Game.Game;
import Game.Mark;

import Players.MiniMax;


public  class Window {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
                TicTacToe game = new TicTacToe(4, 3);
                JFrame window = new JFrame("Tic Tac Toe");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.getContentPane().add(game);
                window.setBounds(300, 300, 500, 500);
                window.setVisible(true);
            } 
        });
    }
}

class TicTacToe extends JPanel {
    private static JButton buttons[][];
    private boolean playerTurn = true;

    private Game game;

    public TicTacToe(int size, int winCondition) {
        game = new Game(size, winCondition);
        buttons = new JButton[size][size];
        setLayout(new GridLayout(size, size));
        // final boolean closeWindow = false;
        
        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                buttons[i][j] = createButton();
                final int iCopy = i;
                final int jCopy = j;
                buttons[i][j].addActionListener(event -> {
                    
                    if(game.setElement(Mark.O, iCopy, jCopy)) {
                        System.out.println("Successfully placed mark at {row: " + iCopy + " col: " + jCopy + "}");
                        buttons[iCopy][jCopy].setText("O");
                        if(game.checkWin(Mark.O)) {
                            JOptionPane.showMessageDialog(this, "Player won! You can close the window",
                                "Game result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else
                        System.out.println("Could not place mark at (" + iCopy + " " + jCopy + "), this place is already taken");

                    System.out.println("Minimax starts calculating");
                    int[] result = MiniMax.makeMove(game);
                    System.out.println("Minimax placed mark at {row: " + result[0] + " col: " + result[1] + "}");
                    buttons[result[0]][result[1]].setText("X");
                    if(game.checkWin(Mark.X)) {
                        JOptionPane.showMessageDialog(this, "Computer won! You can close the window",
                        "Game result", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    game.getBoard().displayBoard();
                    if(game.isGameOver()) {
                        JOptionPane.showMessageDialog(this, "Tie! You can close the window",
                        "Game result", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                add(buttons[i][j]);
            }
        }
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setFont(new FontUIResource("Arial", Font.PLAIN, 50));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}