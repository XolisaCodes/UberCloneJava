public abstract class Ride {

    // Fields:
    String startingPoint;
    String endingPoint;

    // Methods to Ovveride:
    public abstract double calculateDistance(String startingPoint, String endingPoint);
    public abstract Driver assignDriver();
    public abstract double calculateCost(String startingPoint, String endingPoint);
    public abstract void completePayment(Driver driver, Passenger passenger);
 }