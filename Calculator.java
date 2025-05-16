import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0;
    private char operator = ' ';

    public Calculator() {
        setTitle("Colorful Calculator");
        setSize(500, 600); // Medium size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 30));

       
        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(240, 240, 240));
        display.setForeground(new Color(25, 25, 25));
        display.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(display, BorderLayout.NORTH);

        
        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(30, 30, 30));

        String[] buttons = {
            "C", "", "", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", ""
        };

        Color[] colors = {
            new Color(255, 99, 71), // C
            null, null, new Color(70, 130, 180), // /
            new Color(100, 149, 237), new Color(100, 149, 237), new Color(100, 149, 237), new Color(70, 130, 180),
            new Color(144, 238, 144), new Color(144, 238, 144), new Color(144, 238, 144), new Color(70, 130, 180),
            new Color(255, 228, 181), new Color(255, 228, 181), new Color(255, 228, 181), new Color(70, 130, 180),
            new Color(255, 218, 185), new Color(255, 218, 185), new Color(60, 179, 113), null
        };

        for (int i = 0; i < buttons.length; i++) {
            String text = buttons[i];
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 24));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setPreferredSize(new Dimension(90, 70));

            if (!text.equals("")) {
                btn.addActionListener(this);
                btn.setBackground(colors[i] != null ? colors[i] : new Color(211, 211, 211));
                btn.setForeground(Color.BLACK);
                btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            } else {
                btn.setEnabled(false);
                btn.setBorder(null);
                btn.setBackground(new Color(30, 30, 30));
            }

            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9\\.]")) {
            display.setText(display.getText() + cmd);
        } else if (cmd.equals("C")) {
            display.setText("");
            num1 = num2 = 0;
            operator = ' ';
        } else if (cmd.equals("=")) {
            if (operator != ' ' && !display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = (num2 != 0) ? num1 / num2 : 0; break;
                }
                display.setText(String.valueOf(result));
                operator = ' ';
            }
        } else if ("+-*/".contains(cmd)) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = cmd.charAt(0);
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
