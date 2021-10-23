package bridgelabz;

public class InvoiceGenerator {
    private static double MINIMUM_COST_PER_KILOMETER;
    private static int COST_PER_TIME;
    private static int MINIMUM_FARE;
    private RideRepository rideRepository;

    private void setValue(RideType rideType) {
        MINIMUM_COST_PER_KILOMETER=rideType.minimumCostPerKM;
        COST_PER_TIME=rideType.costPerTime;
        MINIMUM_FARE=rideType.minFare;
    }

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }
    public double calculateFare(RideType rideType, double distance, int time) {
        setValue(rideType);
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.rideType, ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }
    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
