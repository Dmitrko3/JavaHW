import javax.swing.*;
import java.awt.*;

public class Targil1 extends JFrame {

    private JComboBox<String> phaseComboBox;
    private JLabel imageLabel;

    public Targil1() {
        // הגדרות בסיסיות של החלון
        setTitle("Phase Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. יצירת הפאנל העליון עם ה-JComboBox
        JPanel topPanel = new JPanel();

        // כאן מגדירים את השמות של המצבים (שמות הקבצים/האפשרויות)
        String[] phases = {"0","1", "2", "3","4","5","6","7"};
        phaseComboBox = new JComboBox<>(phases);

        topPanel.add(new JLabel("Select Phase:"));
        topPanel.add(phaseComboBox);
        add(topPanel, BorderLayout.NORTH);

        // 2. יצירת פאנל התצוגה (displayPanel) עם המסגרת שביקשת
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout()); // כדי שהתמונה תתפוס את כל המקום

        // קטע הקוד שצירפת עבור המסגרת עם הטקסט מעל החלק
        displayPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Display Phase"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // יצירת לייבל שיכיל את התמונה עצמה
        imageLabel = new JLabel("Please select a phase", SwingConstants.CENTER);
        displayPanel.add(imageLabel, BorderLayout.CENTER);

        add(displayPanel, BorderLayout.CENTER);

        // 3. הוספת מאזין (Listener) ל-JComboBox שיעדכן את התמונה בעת שינוי
        phaseComboBox.addActionListener(_ -> updateImage());

        // טעינת התמונה הראשונית בעת הפעלת התוכנה
        updateImage();

        // התאמת גודל החלון ומרכוזו
        setSize(500, 500);
        setLocationRelativeTo(null); // ממקם את החלון באמצע המסך
    }

    // מתודה לעדכון התמונה בהתאם לבחירה ב-JComboBox
    private void updateImage() {
        String selectedPhase = (String) phaseComboBox.getSelectedItem();

        // הוספת התיקייה img לתחילת הנתיב
        String fileName = "" + selectedPhase.replace(" ", "").toLowerCase() + ".png"; // הופך ל- "img/phase1.png"

        // ניסיון לטעון את התמונה מהנתיב היחסי
        ImageIcon icon = new ImageIcon(fileName);

        if (icon.getIconWidth() == -1) {
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found: " + fileName);
        } else {
            imageLabel.setText("");
            imageLabel.setIcon(icon);
        }
    }

    // הפעלת התוכנית
    public static void main(String[] args) {
        // שימוש ב-SwingUtilities כדי לוודא שה-GUI רץ ב-Thread הנכון
        SwingUtilities.invokeLater(() -> new Targil1().setVisible(true));
    }
}