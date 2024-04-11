import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel implements ActionListener {
    private JTextField nameTextField;
    private JTextField weightTextField;
    private JCheckBox saunaCheckBox;
    private JTextField coachingHoursTextField;
    private JComboBox<WeightCategory> weightCategoryComboBox;
    private JButton submitButton;

    public FormPanel() {
        setBackground(new Color(0xF5, 0xFA, 0xB7));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

//        setLayout(new GridLayout(6, 2));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        nameTextField = createTextField(20);
        add(nameTextField, gbc);
//        add(new JLabel("Name:"));
//        nameTextField = new JTextField();
//        add(nameTextField);

        // Add Weight Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel("Weight (kg):"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        weightTextField = createTextField(20);
        add(weightTextField, gbc);

        // Add Sauna CheckBox
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel("Sauna:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        saunaCheckBox = new JCheckBox();
        saunaCheckBox.setBackground(new Color(0xF5, 0xFA, 0xB7));
        add(saunaCheckBox, gbc);

//        add(new JLabel("Weight (kg):"));
//        weightTextField = new JTextField();
//        add(weightTextField);

//        add(new JLabel("Sauna:"));
//        saunaCheckBox = new JCheckBox();
//        add(saunaCheckBox);

//        add(new JLabel("Coaching Hours:"));
//        coachingHoursTextField = new JTextField();
//        add(coachingHoursTextField);
//
//        add(new JLabel("Desired Weight Category:"));
//        weightCategoryComboBox = new JComboBox<>(WeightCategory.values());
//        add(weightCategoryComboBox);
        // Add Coaching Hours Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createLabel("Coaching Hours:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        coachingHoursTextField = createTextField(20);
        add(coachingHoursTextField, gbc);

        // Add Desired Weight Category Label and ComboBox
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createLabel("Desired Weight Category:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        weightCategoryComboBox = new JComboBox<>(WeightCategory.values());
        weightCategoryComboBox.setBackground(Color.WHITE);
        add(weightCategoryComboBox, gbc);

        // Add Submit Button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0x5B, 0xCC, 0xFF));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        add(submitButton, gbc);

        setPreferredSize(new Dimension(400, 300));


//        submitButton = new JButton("Submit");
//        submitButton.addActionListener(this);
//        add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameTextField.getText();
            String weightText = weightTextField.getText();
            String coachingHoursText = coachingHoursTextField.getText();

            // Check if weight and coaching hours are valid numbers
            if (!isNumeric(weightText) || !isNumeric(coachingHoursText)) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for weight and coaching hours.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit actionPerformed method if input is invalid
            }

            double weight = Double.parseDouble(weightText);
            int coachingHours = Integer.parseInt(coachingHoursText);
            boolean sauna = saunaCheckBox.isSelected();
            WeightCategory desiredWeightCategory = (WeightCategory) weightCategoryComboBox.getSelectedItem();
            Athlete athlete = new Athlete(1, name, weight, sauna, coachingHours, desiredWeightCategory);

            double baseCost = CalculationUtils.calculateBaseCost(coachingHours);
            double coachingCost = CalculationUtils.calculateCoachingCost(coachingHours);
            double saunaCost = CalculationUtils.calculateSaunaCost(sauna);
            double totalCost = CalculationUtils.calculateTotalCost(baseCost, coachingCost, saunaCost);

            ResultsWindow resultsWindow = new ResultsWindow();
            resultsWindow.updateResults(name, baseCost, coachingCost, saunaCost, totalCost);
            String comparisonResult = athlete.getComparisonToTargetWeight();
            resultsWindow.setComparisonResult(comparisonResult);
            resultsWindow.setVisible(true);
        }
    }

    // Helper method to check if a string is a valid number
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }
}
