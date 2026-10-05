import javax.swing.JButton;

public class TicTacToeButton extends JButton {
    private int row;
    private int col;

    public TicTacToeButton(int r, int c) {
        this.row = r;
        this.col = c;
        setFont(getFont().deriveFont(48f));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
