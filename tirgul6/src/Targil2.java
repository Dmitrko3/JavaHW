import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Targil2 extends JFrame {

    private JTextField displayField;

    // State variables for calculation logic
    private double firstOperand = 0;
    private String operator = "";
    private boolean isNewInput = true;

    public Targil2() {
        setTitle("Primitive Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // 1. Setup Display
        displayField = new JTextField("0");
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setMargin(new Insets(5, 5, 5, 5));
        add(displayField, BorderLayout.NORTH);

        // 2. Setup Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(2, 2, 2, 2);

        // Create a single listener instance for all buttons
        CalculatorListener listener = new CalculatorListener();

        // --- Row 1: Backspace, CE, C ---
        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        JButton backspaceBtn = new JButton("Backspace");
        backspaceBtn.addActionListener(listener);
        buttonPanel.add(backspaceBtn, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 3;
        JButton ceBtn = new JButton("CE");
        ceBtn.addActionListener(listener);
        buttonPanel.add(ceBtn, gbc);

        gbc.gridx = 4;
        JButton cBtn = new JButton("C");
        cBtn.addActionListener(listener);
        buttonPanel.add(cBtn, gbc);

        // --- Rows 2 to 5 ---
        String[] row2 = {"7", "8", "9", "/", "sqrt"};
        addRowOfButtons(buttonPanel, gbc, row2, 1, listener);

        String[] row3 = {"4", "5", "6", "*", "1/x"};
        addRowOfButtons(buttonPanel, gbc, row3, 2, listener);

        String[] row4 = {"1", "2", "3", "-", "%"};
        addRowOfButtons(buttonPanel, gbc, row4, 3, listener);

        String[] row5 = {"0", "+/-", ".", "+", "="};
        addRowOfButtons(buttonPanel, gbc, row5, 4, listener);

        add(buttonPanel, BorderLayout.CENTER);

        setSize(320, 250);
        setLocationRelativeTo(null);
    }

    // Helper method to add rows and attach the listener
    private void addRowOfButtons(JPanel panel, GridBagConstraints gbc, String[] buttons, int row, ActionListener listener) {
        gbc.gridy = row;
        for (int i = 0; i < buttons.length; i++) {
            gbc.gridx = i;
            JButton btn = new JButton(buttons[i]);
            btn.addActionListener(listener);
            panel.add(btn, gbc);
        }
    }

    // Inner class to handle button clicks and math logic
    private class CalculatorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String currentText = displayField.getText();

            try {
                // Number or Decimal Point Input
                if (command.matches("[0-9]") || command.equals(".")) {
                    if (isNewInput) {
                        displayField.setText(command.equals(".") ? "0." : command);
                        isNewInput = false;
                    } else {
                        // Prevent multiple decimals
                        if (command.equals(".") && currentText.contains(".")) return;
                        displayField.setText(currentText + command);
                    }
                }
                // Basic Operators
                else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                    firstOperand = Double.parseDouble(currentText);
                    operator = command;
                    isNewInput = true;
                }
                // Calculate Result
                else if (command.equals("=")) {
                    if (!operator.isEmpty()) {
                        double secondOperand = Double.parseDouble(currentText);
                        double result = performBasicOperation(firstOperand, secondOperand, operator);
                        displayField.setText(formatResult(result));
                        operator = "";
                        isNewInput = true;
                    }
                }
                // Special Math Operations (Immediate execution)
                else if (command.equals("sqrt")) {
                    double val = Double.parseDouble(currentText);
                    displayField.setText(val >= 0 ? formatResult(Math.sqrt(val)) : "Error");
                    isNewInput = true;
                }
                else if (command.equals("1/x")) {
                    double val = Double.parseDouble(currentText);
                    displayField.setText(val != 0 ? formatResult(1 / val) : "Cannot divide by zero");
                    isNewInput = true;
                }
                else if (command.equals("%")) {
                    double val = Double.parseDouble(currentText);
                    double percentValue;

                    // If adding or subtracting, percentage is relative to the first operand
                    if (operator.equals("+") || operator.equals("-")) {
                        percentValue = firstOperand * (val / 100.0);
                    }
                    // If multiplying, dividing, or no operator, it's just the value / 100
                    else {
                        percentValue = val / 100.0;
                    }

                    displayField.setText(formatResult(percentValue));
                    isNewInput = true;
                }
                else if (command.equals("+/-")) {
                    double val = Double.parseDouble(currentText);
                    displayField.setText(formatResult(val * -1));
                }
                // Deletion and Clearing
                else if (command.equals("Backspace")) {
                    if (!isNewInput && currentText.length() > 0) {
                        currentText = currentText.substring(0, currentText.length() - 1);
                        displayField.setText(currentText.isEmpty() ? "0" : currentText);
                        if (currentText.isEmpty()) isNewInput = true;
                    }
                }
                else if (command.equals("CE")) {
                    // Clear Entry (current text only)
                    displayField.setText("0");
                    isNewInput = true;
                }
                else if (command.equals("C")) {
                    // Clear All (reset completely)
                    displayField.setText("0");
                    firstOperand = 0;
                    operator = "";
                    isNewInput = true;
                }

            } catch (NumberFormatException ex) {
                displayField.setText("Error");
                isNewInput = true;
            }
        }
    }

    // Helper to perform the basic math
    private double performBasicOperation(double num1, double num2, String op) {
        switch (op) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/": return (num2 != 0) ? num1 / num2 : 0; // Simple division by zero handling
            default: return num2;
        }
    }

    // Helper to format the output (removes .0 from whole numbers)
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Targil2().setVisible(true);
        });
    }
}