package bridgelabz;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL, distance, time);
        assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(RideType.NORMAL, distance, time);
        assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRide_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.NORMAL,2.0, 5),
                new Ride(RideType.NORMAL,0.1, 1),
                new Ride(RideType.NORMAL,4.0, 25),
                new Ride(RideType.NORMAL,3.0, 20)};
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 145.0);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "Invoice5678";
        Ride[] rides = {new Ride(RideType.NORMAL,2.0, 5),
                new Ride(RideType.NORMAL,0.1, 1),
                new Ride(RideType.NORMAL,4.0, 25),
                new Ride(RideType.NORMAL,3.0, 20)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 145.0);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumUserId_GenerateTotalFare_ShouldReturnInvoiceSummery() {
        Ride[] rides = {new Ride(RideType.PREMIUM,35.0, 45),
                new Ride(RideType.PREMIUM,10.55, 30),
                new Ride(RideType.NORMAL,20, 30)};
        invoiceGenerator.addRides("Invoice4321", rides);
        InvoiceSummary invoiceSummery = invoiceGenerator.getInvoiceSummary("Anubhav");
        InvoiceSummary expectedSummery = new InvoiceSummary(3, 1063.25);
        assertEquals(expectedSummery, invoiceSummery);
    }
    @Test
    public void givenPremiumLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(RideType.PREMIUM, distance, time);
        assertEquals(20, fare, 0.0);
    }

    @Test
    public void givenPremiumMultipleRide_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(RideType.PREMIUM,2.0, 5),
                new Ride(RideType.PREMIUM,0.1, 1),
                new Ride(RideType.PREMIUM,4.0, 25),
                new Ride(RideType.PREMIUM,3.0, 20)};
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 255.0);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenPremiumUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "Invoice1234";
        Ride[] rides = {new Ride(RideType.PREMIUM,2.0, 5),
                new Ride(RideType.PREMIUM,0.1, 1),
                new Ride(RideType.PREMIUM,4.0, 25),
                new Ride(RideType.PREMIUM,3.0, 20)};
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 255.0);
        assertEquals(expectedInvoiceSummary, summary);
    }
}
