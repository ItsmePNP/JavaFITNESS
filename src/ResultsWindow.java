import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultsWindow extends JFrame {
    private JLabel nameLabel;
    private JLabel baseCostLabel;
    private JLabel coachingCostLabel;
    private JLabel saunaCostLabel;
    private JLabel totalCostLabel;
    private JLabel comparisonLabel; // New label for weight comparison
    private JButton registerButton; // New register button
    private List<AthleteData> athleteDataList; // List to store athlete data

    public ResultsWindow() {
        super("Results");
        setLayout(new GridLayout(7, 1));

        nameLabel = new JLabel("Athlete Name: ");
        baseCostLabel = new JLabel("Base Cost: ");
        coachingCostLabel = new JLabel("Coaching Cost: ");
        saunaCostLabel = new JLabel("Sauna Cost: ");
        totalCostLabel = new JLabel("Total Cost: ");
        comparisonLabel = new JLabel(); // Initialize comparison label
        registerButton = new JButton("Register"); // Initialize register button

        // Initialize athlete data list
        athleteDataList = new ArrayList<>();

        // Register button action listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the data from the labels
                String name = nameLabel.getText().substring(13); // Remove "Athlete Name: " prefix
                double baseCost = Double.parseDouble(baseCostLabel.getText().substring(11, baseCostLabel.getText().indexOf(" NPR")));
                double coachingCost = Double.parseDouble(coachingCostLabel.getText().substring(15, coachingCostLabel.getText().indexOf(" NPR")));
                double saunaCost = Double.parseDouble(saunaCostLabel.getText().substring(11, saunaCostLabel.getText().indexOf(" NPR")));
                double totalCost = Double.parseDouble(totalCostLabel.getText().substring(12, totalCostLabel.getText().indexOf(" NPR")));

                // Create AthleteData object
                AthleteData athleteData = new AthleteData(name, baseCost, coachingCost, saunaCost, totalCost);

                // Add athlete data to the list
                athleteDataList.add(athleteData);

                // Save data to JSON file
                saveDataToJsonFile();
            }
        });

        add(nameLabel);
        add(baseCostLabel);
        add(coachingCostLabel);
        add(saunaCostLabel);
        add(totalCostLabel);
        add(comparisonLabel); // Add comparison label
        add(registerButton); // Add register button

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to update result labels
    public void updateResults(String name, double baseCost, double coachingCost, double saunaCost, double totalCost) {
        nameLabel.setText("Athlete Name: " + name);
        baseCostLabel.setText("Base Cost: " + baseCost + " NPR");
        coachingCostLabel.setText("Coaching Cost: " + coachingCost + " NPR");
        saunaCostLabel.setText("Sauna Cost: " + saunaCost + " NPR");
        totalCostLabel.setText("Total Cost: " + totalCost + " NPR");
    }

    // Method to set comparison result
    public void setComparisonResult(String comparisonResult) {
        comparisonLabel.setText(comparisonResult);
    }

    // Method to save data to JSON file
    private void saveDataToJsonFile() {
        try (FileWriter fileWriter = new FileWriter("athlete_data.json")) {
            // Convert athlete data list to JSON format and write to file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ((Gson) gson).toJson(athleteDataList, fileWriter);

            // Show registration successful message
            JOptionPane.showMessageDialog(ResultsWindow.this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Class to represent athlete data
    private static class AthleteData {
        private String name;
        private double baseCost;
        private double coachingCost;
        private double saunaCost;
        private double totalCost;

        public AthleteData(String name, double baseCost, double coachingCost, double saunaCost, double totalCost) {
            this.name = name;
            this.baseCost = baseCost;
            this.coachingCost = coachingCost;
            this.saunaCost = saunaCost;
            this.totalCost = totalCost;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBaseCost() {
            return baseCost;
        }

        public void setBaseCost(double baseCost) {
            this.baseCost = baseCost;
        }

        public double getCoachingCost() {
            return coachingCost;
        }

        public void setCoachingCost(double coachingCost) {
            this.coachingCost = coachingCost;
        }

        public double getSaunaCost() {
            return saunaCost;
        }

        public void setSaunaCost(double saunaCost) {
            this.saunaCost = saunaCost;
        }

        public double getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(double totalCost) {
            this.totalCost = totalCost;
        }
    }
}
