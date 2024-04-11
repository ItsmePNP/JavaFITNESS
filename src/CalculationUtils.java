public class CalculationUtils {
    public static double calculateBaseCost(int privateCoachingHours) {
        // Example calculation based on membership type (adjust as needed)
        if (privateCoachingHours > 0) {
            return 200; // Assuming higher cost for athletes with private coaching
        } else {
            return 150; // Assuming lower cost for standard membership
        }
    }

    public static double calculateCoachingCost(int privateCoachingHours) {
        return privateCoachingHours * 50; // Assuming a cost of 50 NPR per private coaching hour
    }

    public static double calculateSaunaCost(boolean sauna) {
        return sauna ? 20 : 0; // Assuming a fixed cost of 20 NPR for sauna if included
    }

    public static double calculateTotalCost(double baseCost, double coachingCost, double saunaCost) {
        return baseCost + coachingCost + saunaCost;
    }
}
