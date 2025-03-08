import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculatrice implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] keys = new JButton[10];
    JButton[] functionKeys = new JButton[9];
    JButton add, sub, mult, div, equal, clear, del, dot, percent;
    JPanel display;
    JPanel keyBoard;
    JTextField history;
    Font font = new Font("Arial", Font.BOLD, 20);
    Timer timer;
    final int HISTORY_WIDTH = 370;
    int HISTORY_HEIGHT = 100;
    int historyDeployement = 10;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculatrice() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();
        display = new JPanel();
        keyBoard = new JPanel();
        history = new JTextField();

        history.setPreferredSize(new Dimension(HISTORY_WIDTH, HISTORY_HEIGHT));
        history.setBackground(Color.decode("#CCCCFF"));
        history.setLayout(new GridLayout(1, 10));
        history.setEditable(false);

        textField = new JTextField();
        textField.setBounds(0, 0, 370, 100);
        textField.setFont(font);
        textField.setEditable(false);

        frame.setTitle("Calculatrice");
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(370, 550));
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));

        display.setPreferredSize(new Dimension(340, 100));
        display.setBackground(Color.WHITE);
        display.setLayout(new BorderLayout());
        display.setBorder(BorderFactory.createLoweredBevelBorder());

        keyBoard.setPreferredSize(new Dimension(360, 380));
        keyBoard.setBackground(Color.WHITE);
        keyBoard.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        keyBoard.setLayout(new GridLayout(5, 4, 10, 10));

        add = new JButton("+");
        sub = new JButton("-");
        mult = new JButton("*");
        div = new JButton("/");
        equal = new JButton("=");
        clear = new JButton("C");
        del = new JButton("DEL");
        dot = new JButton(".");
        percent = new JButton("%");

        functionKeys[0] = add;
        functionKeys[1] = sub;
        functionKeys[2] = mult;
        functionKeys[3] = div;
        functionKeys[4] = equal;
        functionKeys[5] = clear;
        functionKeys[6] = del;
        functionKeys[7] = dot;
        functionKeys[8] = percent;
        for (int i = 0; i < 9; i++) {
            functionKeys[i].addActionListener(this);
            functionKeys[i].setFont(font);
            functionKeys[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            keys[i] = new JButton(String.valueOf(i));
            keys[i].addActionListener(this);
            keys[i].setFont(font);
            keys[i].setFocusable(false);
        }

        keyBoard.add(keys[1]);
        keyBoard.add(keys[2]);
        keyBoard.add(keys[3]);
        keyBoard.add(percent);
        keyBoard.add(keys[4]);
        keyBoard.add(keys[5]);
        keyBoard.add(keys[6]);
        keyBoard.add(add);
        keyBoard.add(keys[7]);
        keyBoard.add(keys[8]);
        keyBoard.add(keys[9]);
        keyBoard.add(sub);
        keyBoard.add(dot);
        keyBoard.add(keys[0]);
        keyBoard.add(mult);
        keyBoard.add(div);
        keyBoard.add(del);
        keyBoard.add(equal);
        keyBoard.add(clear);

        display.add(textField);

        frame.addKeyListener(new KeyboardAction());
        frame.add(display);
        frame.add(keyBoard);
        frame.add(history);
        frame.pack();
        frame.setVisible(true);

    }

    public void dotFunction() {
        textField.setText(textField.getText().concat("."));
    }

    public void addFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '+';
        textField.setText("");
    }

    public void subFunction() {
        if (textField.getText().equals("")) {
            textField.setText("-");
        } else {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
    }

    public void multFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '*';
        textField.setText("");
    }

    public void divFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '/';
        textField.setText("");
    }

    public void percentFunction() {
        num1 = Double.parseDouble(textField.getText());
        operator = '%';
        textField.setText("");
    }

    public void equalFunction() {
        num2 = Double.parseDouble(textField.getText());
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            case '%':
                result = (num1 / 100) * num2;
                break;
        }
        textField.setText(String.valueOf(result));
        num1 = result;

    }

    public void clearFunction() {
        textField.setText("");
    }

    public void delFunction() {
        String str = textField.getText();
        textField.setText("");
        for (int i = 0; i < str.length() - 1; i++) {
            textField.setText(textField.getText() + str.charAt(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == keys[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == dot) {
            dotFunction();
        }
        if (e.getSource() == add) {
            addFunction();
        }
        if (e.getSource() == sub) {
            subFunction();
        }
        if (e.getSource() == mult) {
            multFunction();
        }
        if (e.getSource() == div) {
            divFunction();
        }
        if (e.getSource() == clear) {
            clearFunction();
        }
        if (e.getSource() == del) {
            delFunction();
        }
        if (e.getSource() == percent) {
            percentFunction();
        }
        if (e.getSource() == equal) {
            equalFunction();
        }

    }

    public class KeyboardAction extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());

            for (int i = 0; i < 10; i++) {
                if (e.getKeyCode() == 96 + i) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DECIMAL) {
                dotFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_ADD) {
                addFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                subFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                multFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
                divFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                clearFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                delFunction();
            }
            if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar(0)) {
                percentFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                equalFunction();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println(history.getHeight());
            }
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Calculatrice();
    }

}
