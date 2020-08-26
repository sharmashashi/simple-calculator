import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Calculator {
    static JFrame _frame = new JFrame("Open Calculator");
    static final int windowHeight = 650;
    static final int windowWidth = 450;
    static final int displayHeight = 150;
    static final int buttonWidth = 100;
    static final int buttonHeight = 100;
    static HashMap<String, JButton> _btns = new HashMap<>();
    static JLabel result;
    static JLabel input;

    static JPanel _display;

    /* create a new window with the fixed window size */
    static void _initWindow() {
        _frame.setBackground(Color.WHITE);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setSize(new Dimension(windowWidth, windowHeight));
        _frame.setResizable(false);
        _frame.setVisible(true);
    }

    static void _loadComponents() {
        /* initialize buttons */
        HashMap<String, JButton> _tempMap = new HashMap<>();
        for (int i = 0; i <= 11; i++) {
            if (i <= 10) {
                _tempMap.put(Integer.toString(i), new JButton(Integer.toString(i)));
                _tempMap.get(Integer.toString(i)).setBackground(Color.GRAY);
            } else {
                _tempMap.put(".", new JButton("."));
                _tempMap.put("=", new JButton("="));
                _tempMap.put("+", new JButton("+"));
                _tempMap.put("-", new JButton("-"));
                _tempMap.put("x", new JButton("x"));
                _tempMap.put("/", new JButton("/"));
                _tempMap.get("=").setForeground(Color.blue);;
            }
        }
        _btns = _tempMap;
        /* load display */
        _display = new JPanel();
        _display.setPreferredSize(new Dimension(windowWidth, displayHeight));
        _display.setBounds(0, 0, windowWidth, displayHeight);
        _display.setBackground(Color.lightGray);
        result = new JLabel();
        

        _frame.add(_display, BorderLayout.NORTH);

    }

    static void _addButtons() {
        int breakPoint = 0;
        int _btnFromLeft = 10;
        int _btnFromTop = 10 + displayHeight;
        int _iController = 1;

        for (int i = 1; i <= 13; i++) {

            if (breakPoint == 0 && i % 4 == 0) {
                _btns.get("/").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("/"));
                _btnFromLeft = 10;
                _btnFromTop += (10 + buttonHeight);
                breakPoint++;
            } else if (breakPoint == 1 && i % 4 == 0) {
                _btns.get("x").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("x"));
                _btnFromLeft = 10;
                _btnFromTop += (10 + buttonHeight);
                breakPoint++;
            } else if (breakPoint == 2 && i % 4 == 0) {
                _btns.get("-").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("-"));
                _btnFromLeft = 10;
                _btnFromTop += (10 + buttonHeight);
                breakPoint++;
            } else if (breakPoint == 3) {
                _btns.get("0").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("0"));
                _btnFromLeft += (buttonWidth + 10);
                _btns.get(".").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("."));
                _btnFromLeft += (buttonWidth + 10);
                _btns.get("=").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("="));
                _btnFromLeft += (buttonWidth + 10);
                _btns.get("+").setBounds(_btnFromLeft, _btnFromTop, buttonWidth, buttonHeight);
                _frame.add(_btns.get("+"));

            } else {
                _btns.get(Integer.toString(_iController)).setBounds(_btnFromLeft, _btnFromTop, buttonWidth,
                        buttonHeight);
                _frame.add(_btns.get(Integer.toString(_iController)));
                ///
                _btnFromLeft +=(buttonWidth+ 10);
                _iController++;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        _initWindow();
        _loadComponents();
        _addButtons();

        _frame.repaint();
    }

}