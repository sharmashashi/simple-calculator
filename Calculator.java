import javax.swing.*;
import java.awt.Dimension;

public class Calculator {
    static int windowHeight = 700;
    static int windowWidth = 400;

    /* labels that can be used to label buttons */
    JLabel _btns(String lbl) {
        return new JLabel(lbl);
    }

    static void _initWindow() {
        /* create a new window with the fixed window size */
        JFrame _frame = new JFrame("Open Calculator");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setMinimumSize(new Dimension( windowWidth,windowHeight));
        _frame.setMaximumSize(new Dimension( windowWidth,windowHeight));
        _frame.setVisible(true);
    }

    public static void main(String[] args) {
        _initWindow();
    }

}