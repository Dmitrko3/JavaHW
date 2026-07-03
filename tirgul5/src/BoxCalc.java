import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BoxCalc extends JFrame {
    private JTextField widthField;
    private JTextField heightField;
    private JTextField lengthField;
    private JLabel volumeLabel;
    private JLabel areaLabel;

    public BoxCalc() {
        // Setup main window
        setTitle("Box Volume & Area");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 160);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // Create the Input Panel using GridLayout (3 rows, 2 columns)
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // 1. Setup the formatter for decimal numbers
        java.text.NumberFormat format = java.text.NumberFormat.getNumberInstance();
        javax.swing.text.NumberFormatter formatter = new javax.swing.text.NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0); // Prevent negative numbers
        formatter.setAllowsInvalid(false); // Prevents typing letters

        JFormattedTextField widthField = new JFormattedTextField(formatter);
        JFormattedTextField heightField = new JFormattedTextField(formatter);
        JFormattedTextField lengthField = new JFormattedTextField(formatter);
        inputPanel.add(new JLabel("Width:"));
        inputPanel.add(widthField);

        inputPanel.add(new JLabel("Height:"));
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Length:"));
        inputPanel.add(lengthField);

        // Create the Bottom Panel using FlowLayout
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        JButton calcButton = new JButton("Calculate");
        volumeLabel = new JLabel("volume");
        areaLabel = new JLabel("area");

        bottomPanel.add(calcButton);
        bottomPanel.add(volumeLabel);
        bottomPanel.add(areaLabel);

        // Add panels to the main frame
        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listener to the button
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get values from JFormattedTextField (returns an Object, so cast to Number then get double)
                    double w = Double.parseDouble(widthField.getText().trim());
                    double h = Double.parseDouble(heightField.getText().trim());
                    double l = Double.parseDouble(lengthField.getText().trim());

                    // A box cannot have dimensions of 0. If they enter 0, show the MessageBox.
                    if (w <= 0 || h <= 0 || l <= 0) {
                        // This fulfills the "MessageBox" requirement
                        JOptionPane.showMessageDialog(null,
                                "Dimensions must be greater than zero.",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Perform calculations with the Box class
                    Box box = new Box(l, w, h);

                    // Format the output so it doesn't show crazy long decimals
                    volumeLabel.setText(String.format("Volume: %.2f", box.getVolume()));
                    areaLabel.setText(String.format("Area: %.2f", box.getSurfaceArea()));

                } catch (NullPointerException ex) {
                    // This catches the error if they leave a field completely empty
                    JOptionPane.showMessageDialog(null,
                            "Please fill in all fields with valid numbers.",
                            "Missing Input",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void calculateAndDisplay() {
        try {
            // The assignment specifies natural numbers (מספרים טבעיים), so we parse as integers
            int w = Integer.parseInt(widthField.getText().trim());
            int h = Integer.parseInt(heightField.getText().trim());
            int l = Integer.parseInt(lengthField.getText().trim());

            // Check if they are actually natural numbers (greater than 0)
            if (w <= 0 || h <= 0 || l <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Please enter positive natural numbers.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create box object and calculate
            Box box = new Box(l, w, h);

            // Update the UI labels
            volumeLabel.setText("Volume: " + box.getVolume());
            areaLabel.setText("Area: " + box.getSurfaceArea());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid integers in all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
