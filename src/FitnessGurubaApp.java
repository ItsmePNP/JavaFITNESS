import javax.swing.*;

public class FitnessGurubaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fitness Guruba Training Cost Calculator");
            FormPanel formPanel = new FormPanel();
            frame.add(formPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}


