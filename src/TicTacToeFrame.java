import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {

    private TicTacToeButton[][] buttons = new TicTacToeButton[3][3];
    private TicTacToeGame game = new TicTacToeGame();

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        ButtonListener listener = new ButtonListener();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                TicTacToeButton b = new TicTacToeButton(r, c);
                b.addActionListener(listener);
                buttons[r][c] = b;
                boardPanel.add(b);
            }
        }

        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Goodbye!");
            System.exit(0);
        });

        add(boardPanel, BorderLayout.CENTER);
        add(quit, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonListener implements java.awt.event.ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            TicTacToeButton b = (TicTacToeButton) e.getSource();
            int r = b.getRow();
            int c = b.getCol();

            if (!game.isLegal(r, c)) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Illegal move. Try again.");
                return;
            }

            b.setText(String.valueOf(game.getCurrentPlayer()));
            game.makeMove(r, c);

            if (game.checkWin()) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Player " + game.getCurrentPlayer() + " wins!");
                askPlayAgain();
                return;
            }

            if (game.checkTie()) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Tie game!");
                askPlayAgain();
                return;
            }
            if (game.checkWin()) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Player " + game.getCurrentPlayer() + " wins!");
                askPlayAgain();
                return;
            }


            if (game.checkEarlyTie()) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Early tie — no player can win!");
                askPlayAgain();
                return;
            }

            if (game.checkTie()) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Tie game!");
                askPlayAgain();
                return;
            }

            game.switchPlayer();
        }
    }

    private void askPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(
                this, "Play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            game.reset();
            for (TicTacToeButton[] row : buttons)
                for (TicTacToeButton b : row)
                    b.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Goodbye!");
            System.exit(0);
        }
    }
}
