import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.concurrent.ExecutionException;

import Game.*;
import Players.*;


public  class WindowGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
             TicTacToe window = new TicTacToe(4, 4);
             window.setTitle("Tic Tac Toe");
             window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             window.setBounds(300, 300, 500, 500);
             window.setVisible(true);
         });
    }

    static class TicTacToe extends JFrame {
        private final JButton[][] buttons;
        private final Game game;
        private boolean playersTurn = true;
    
        public TicTacToe(int size, int winCondition) {
            super();
            game = new Game(size, winCondition);
            buttons = new JButton[size][size];
            setLayout(new GridLayout(size, size));
            
            for(int i = 0; i < game.getBoardSize(); ++i) {
                for(int j = 0; j < game.getBoardSize(); ++j) {
                    buttons[i][j] = createButton();
                    final int iCopy = i;
                    final int jCopy = j;
                    buttons[i][j].addActionListener(event -> {
                        if(playersTurn) {
                            if(game.setElement(Mark.O, iCopy, jCopy)) {
                                System.out.println("Player successfully placed mark at {row: " + iCopy + " col: " + jCopy + "}\n");
                                buttons[iCopy][jCopy].setText("O");
                                checkIfWinOrDraw(Mark.O);

                                playersTurn = false;
                                runMiniMaxThread();
                            }
                            else
                                System.out.println("Could not place mark at (" + iCopy + " " + jCopy + "), this place is already taken"); 
                        }
                    });
                    add(buttons[i][j]);
                }
            }
        }

        public Integer[] makeAIMove() {
            System.out.println("Minimax starts calculating");
            Integer[] result = MiniMaxAlphaBeta.makeMove(game);
            System.out.println("Minimax placed mark at {row: " + result[0] + " col: " + result[1] + "}\n");
            return result;
        }

        private void runMiniMaxThread() {
            SwingWorker<Integer[], Void> thread = new SwingWorker<>() {
                @Override
                protected Integer[] doInBackground() {
                    if(!playersTurn)
                        return makeAIMove();
                    else 
                        return null;
                }

                @Override
                protected void done() {
                    Integer[] result;
                    try {
                        result = get();
                        if(result == null) {
                            System.out.println("Something went horribly wrong!");
                            System.exit(1);
                        }
                        buttons[result[0]][result[1]].setText("X");
                        checkIfWinOrDraw(Mark.X);
                        playersTurn = true;
                    } 
                    catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.execute();
        }

        private void checkIfWinOrDraw(Mark mark) {
            if(game.isGameOver()) {
                JOptionPane.showMessageDialog(this, "Tie! The window is about to close",
                "Game result", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Tie");
                System.exit(0);
            }

            String currentPlayer = (mark == Mark.O) ? "Player" : "Computer";
            if(game.checkWin(mark)) {
                JOptionPane.showMessageDialog(this, currentPlayer + " won! The window is about to close",
                "Game result", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(currentPlayer + " won");
                System.exit(0);
            }
        }

        private JButton createButton() {
            JButton button = new JButton();
            button.setFont(new FontUIResource("Arial", Font.PLAIN, (70 - (game.getBoardSize() - 3) * 10 )));
            button.setBackground(Color.WHITE);
            button.setFocusable(false);
            return button;
        }
    }
}