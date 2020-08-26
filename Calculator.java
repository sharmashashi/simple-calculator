import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Calculator {
    static JFrame _frame = new JFrame("Open Calculator");
    static final int windowHeight = 650;
    static final int windowWidth = 450;
    static final int displayHeight = 150;
    static final int buttonWidth = 100;
    static final int buttonHeight = 100;
    static HashMap<String, JButton> _btns = new HashMap<>();
    private static JLabel result;
    private static JLabel input;

    static JPanel _display;

    protected void setResult(String res) {
        result.setText(res);
    }

    protected void setInput(String str) {
        input.setText(str);
    }

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
                _tempMap.get("=").setForeground(Color.blue);
                ;
            }
        }
        _btns = _tempMap;
        /* load display */
        _display = new JPanel();
        _display.setPreferredSize(new Dimension(windowWidth, displayHeight));
        _display.setBounds(0, 0, windowWidth, displayHeight);
        _display.setBackground(Color.lightGray);
        result = new JLabel();
        input = new JLabel();
        result.setBounds(10, 10, windowWidth - 20, 65);
        input.setBounds(10, 75, windowWidth - 20, 65);
        result.setText("0.0");
        input.setText("0");
        _display.add(result);
        _display.add(input);
        _display.setLayout(new BorderLayout());
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
                _btnFromLeft += (buttonWidth + 10);
                _iController++;
            }

        }
    }

    /* register all buttons for events */
    static void _handleEvents() {
        for (int i = 0; i < 11; i++) {
            _btns.get(Integer.toString(i)).addActionListener(new ButtonEvent(true, i, null));
        }
        _btns.get("/").addActionListener(new ButtonEvent(false, -1, "/"));
        _btns.get("x").addActionListener(new ButtonEvent(false, -1, "x"));
        _btns.get("+").addActionListener(new ButtonEvent(false, -1, "+"));
        _btns.get("-").addActionListener(new ButtonEvent(false, -1, "-"));
        _btns.get("=").addActionListener(new ButtonEvent(false, -1, "="));
        // _btns.get(".").addActionListener(new ButtonEvent(false, -1, "."));
    }

    public static void main(String[] args) throws IOException {
        _initWindow();
        _loadComponents();
        _addButtons();
        _frame.setLayout(new BorderLayout());
        _frame.repaint();
        _handleEvents();
    }

}

class ButtonEvent extends Calculator implements ActionListener {
    final boolean isNum;
    final int number;
    final String operator;

    ButtonEvent(boolean isNum, int number, String operator) {
        this.isNum = isNum;
        this.number = number;
        this.operator = operator;

    }

    public void actionPerformed(ActionEvent e) {
        _showInputString();
    }

    String _calculate(String input) {
        InputBuffer _ib = InputBuffer.instance();
        String result = "0.0";
        if (_ib.getTotalOperator() + 1 == _ib.getTotalOperand()) {
            result = "okay";
        } else {
            result = "Please type valid operation";
        }
        return result;
    }

    void _showInputString() {
        InputBuffer _ib = InputBuffer.instance();
        if (isNum == true) {

            /*
             * check for last character in buffer and decide whether it is another operand
             * or same one
             */
            if (_ib.getBuffer() == "") {
                _ib.setBuffer(Integer.toString(number), false);
                _ib.setTotalOperand(1);
            } else {
                if (Character.isDigit(_ib.getBuffer().charAt(_ib.getBuffer().length() - 1)) != true) {

                    _ib.setTotalOperand(_ib.getTotalOperand() + 1);
                }
                _ib.setBuffer(Integer.toString(number), false);
            }

        } else if (operator != "=") {

            _ib.setBuffer(operator, false);
            _ib.setTotalOperator(_ib.getTotalOperator() + 1);
        } else if (operator == "=") {
            super.setResult(_calculate(_ib.getBuffer()));
            _ib.setBuffer("", true);
        }

    }
}

class InputBuffer extends Calculator {
    private String _buffer = "";
    private int _operator = 0;
    private int _operand = 0;
    private static InputBuffer _instance = null;

    public static InputBuffer instance() {
        if (_instance == null)
            _instance = new InputBuffer();

        return _instance;
    }

    public void setBuffer(String str, boolean clear) {
        if (clear == true) {
            _buffer = "";
            _operand = 0;
            _operator = 0;
        } else {
            _buffer += str;

        }
        super.setInput(_buffer);
    }

    public String getBuffer() {
        return _buffer;
    }

    public void setTotalOperator(int val) {
        _operator = val;
        System.out.println("number of operators " + _operator);
    }

    public int getTotalOperator() {
        return _operator;
    }

    public void setTotalOperand(int val) {
        _operand = val;
        System.out.println("number of operands " + _operand);
    }

    public int getTotalOperand() {
        return _operand;
    }
}