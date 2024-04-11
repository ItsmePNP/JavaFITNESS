public enum WeightCategory {
    BANTAMWEIGHT("Bantamweight", 64.98),
    LIGHTWEIGHT("Lightweight", 69.97),
    MIDDLEWEIGHT("Middleweight", 79.95),
    LIGHT_HEAVYWEIGHT("Light-Heavyweight", 89.93),
    HEAVYWEIGHT("Heavyweight", 102.17);

    private final String description;
    private final double upperLimit;

    WeightCategory(String description, double upperLimit) {
        this.description = description;
        this.upperLimit = upperLimit;
    }

    public String getDescription() {
        return description;
    }

    public double getUpperLimit() {
        return upperLimit;
    }
}
