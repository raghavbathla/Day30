package bridgelabz;

public enum RideType {
    NORMAL(10,1,5), PREMIUM(15,2,20);

    public final double minimumCostPerKM;
    public final int costPerTime;
    public final int minFare;
    RideType(double minimumCostPerKM, int costPerTime, int minFare) {
        this.minimumCostPerKM = minimumCostPerKM;
        this.costPerTime = costPerTime;
        this.minFare = minFare;
    }
}
