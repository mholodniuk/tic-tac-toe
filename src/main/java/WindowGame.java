import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import Game.Game;
import Game.Mark;

import Players.MiniMax;


public  class WindowGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
                TicTacToe window = new TicTacToe(3, 3);
                window.setTitle("Tic Tac Toe");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setBounds(300, 300, 500, 500);
                window.setVisible(true);
            } 
        });
    }

    static class TicTacToe extends JFrame {
        private JButton buttons[][];
        private Game game;
        // private boolean isPlayersTurn = true;
    
        public TicTacToe(int size, int winCondition) {
            game = new Game(size, winCondition);
            buttons = new JButton[size][size];
            setLayout(new GridLayout(size, size));
            
            for(int i = 0; i < game.getBoardSize(); ++i) {
                for(int j = 0; j < game.getBoardSize(); ++j) {
                    buttons[i][j] = createButton();
                    final int iCopy = i;
                    final int jCopy = j;
                    buttons[i][j].addActionListener(event -> {
                        if(game.setElement(Mark.O, iCopy, jCopy)) {
                            System.out.println("Player successfully placed mark at {row: " + iCopy + " col: " + jCopy + "}\n");
                            buttons[iCopy][jCopy].setText("O");
    
                            if(game.isGameOver()) {
                                JOptionPane.showMessageDialog(this, "Tie! The window is about to close",
                                "Game result", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println("Tie");
                                System.exit(0);
                            }

                            if(game.checkWin(Mark.O)) {
                                JOptionPane.showMessageDialog(this, "Player won! The window is about to close",
                                    "Game result", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println("Player won");
                                System.exit(0);
                            }
                            makeAIMove();
                        } 
                        else
                            System.out.println("Could not place mark at (" + iCopy + " " + jCopy + "), this place is already taken");
                    });
                    add(buttons[i][j]);
                }
            }
        }

        public void makeAIMove() {
            System.out.println("Minimax starts calculating");
            int[] result = MiniMax.makeMove(game);
            System.out.println("Minimax placed mark at {row: " + result[0] + " col: " + result[1] + "}\n");
            buttons[result[0]][result[1]].setText("X");

            if(game.checkWin(Mark.X)) {
                JOptionPane.showMessageDialog(this, "Computer won! The window is about to close",
                "Game result", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Computer won");
                
                System.exit(0);
            }
        }

        private JButton createButton() {
            JButton button = new JButton();
            button.setFont(new FontUIResource("Arial", Font.PLAIN, 50));
            button.setBackground(Color.WHITE);
            button.setFocusable(false);
            return button;
        }
    }
}