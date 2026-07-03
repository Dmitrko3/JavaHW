import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 3. The Main App Entry Point
public class Main {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread (Best Practice)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BoxCalc().setVisible(true);
            }
        });
    }
}