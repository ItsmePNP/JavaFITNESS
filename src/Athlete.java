public class Athlete {
    private final int id;
    private final String name;
    private final double weight;
    private final boolean sauna;
    private final int privateCoachingHours;
    private final WeightCategory desiredWeightCategory; // New field for desired weight category

    public Athlete(int id, String name, double weight, boolean sauna, int privateCoachingHours, WeightCategory desiredWeightCategory) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.sauna = sauna;
        this.privateCoachingHours = privateCoachingHours;
        this.desiredWeightCategory = desiredWeightCategory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public boolean hasSauna() {
        return sauna;
    }

    public int getPrivateCoachingHours() {
        return privateCoachingHours;
    }

    public WeightCategory getDesiredWeightCategory() {
        return desiredWeightCategory;
    }

//    public String getComparisonToTargetWeight() {
//        // Define target weights for each category
//        double[] targetWeights = {143.25, 154.25, 176.25, 198.25, 225.25};
//        String[] weightCategories = {"Bantamweight", "Lightweight", "Middleweight", "Light-Heavyweight", "Heavyweight"};
//
//        // Find the appropriate weight category based on the athlete's weight
//        int categoryIndex = -1;
//        for (int i = 0; i < targetWeights.length; i++) {
//            if (weight <= targetWeights[i]) {
//                categoryIndex = i;
//                break;
//            }
//        }
//
//        // If the athlete's weight is in the highest category
//        if (categoryIndex == -1) {
//            return "You are in the Super Heavyweight category.";
//        }
//
//        double targetWeight = targetWeights[categoryIndex];
//        double difference = targetWeight - weight;
//        String category = weightCategories[categoryIndex];
//
//        if (Math.abs(difference) < 0.01) { // Using a small tolerance for comparison
//            return "You have reached the " + category + " category.";
//        } else if (difference > 0) {
//            return "You need to gain " + String.format("%.2f", difference) + " kg to reach the " + category + " category.";
//        } else {
//            return "You need to lose " + String.format("%.2f", Math.abs(difference)) + " kg to reach the " + category + " category.";
//        }
//    }
public String getComparisonToTargetWeight() {
    // Find the appropriate weight category based on the desired weight category
    WeightCategory[] weightCategories = WeightCategory.values();
    WeightCategory targetCategory = null;

    for (WeightCategory category : weightCategories) {
        if (desiredWeightCategory == category) {
            targetCategory = category;
            break;
        }
    }

    if (targetCategory == null) {
        return "Your desired weight category is not found.";
    }

    // Get the index of the desired weight category
    int categoryIndex = targetCategory.ordinal();

    // If the desired weight category is in the highest category
    if (categoryIndex == weightCategories.length - 1) {
        return "Your desired weight category is in the " + weightCategories[categoryIndex].getDescription() + " category.";
    }

    double desiredWeight = targetCategory.getUpperLimit();
    String category = weightCategories[categoryIndex].getDescription();
    double difference = desiredWeight - weight;

    if (Math.abs(difference) < 0.01) { // Using a small tolerance for comparison
        return "You have reached the desired " + category + " category.";
    } else if (difference > 0) {
        return "You need to gain " + String.format("%.2f", difference) + " kg to reach the desired " + category + " category.";
    } else {
        return "You need to lose " + String.format("%.2f", Math.abs(difference)) + " kg to reach the desired " + category + " category.";
    }
}

}
