import javax.swing.*;

import Game.Game;
import Game.Mark;

import java.awt.*;

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

    private Game game;

    public TicTacToe(int size, int winCondition) {
        game = new Game(size, winCondition);
        buttons = new JButton[size][size];
        setLayout(new GridLayout(size, size));
        
        for(int i = 0; i < game.getBoardSize(); ++i) {
            for(int j = 0; j < game.getBoardSize(); ++j) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                final int iCopy = i;
                final int jCopy = j;
                buttons[i][j].addActionListener(event -> {
                    System.out.println("Wciśnięto przycisk: ");
                    game.changeElement(Mark.X, iCopy, jCopy);
                    game.getBoard().displayBoard();
                });
                add(buttons[i][j]);
            }
        }
        
    }
}