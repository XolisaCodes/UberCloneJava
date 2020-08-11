import java.util.*;

public class UberApp {

    public static void main(String[] args) {
        System.out.println("  _    _ ____  ______ _____  \n" +
                " | |  | |  _ \\|  ____|  __ \\ \n" +
                " | |  | | |_) | |__  | |__) |\n" +
                " | |  | |  _ <|  __| |  _  / \n" +
                " | |__| | |_) | |____| | \\ \\ \n" +
                "  \\____/|____/|______|_|  \\_\\\n");

        // Example Passenger:
        // Passenger lonwabo = new Passenger("lonwabo@gmail.com", "Lonwabo", "Mvovo","0731223283", 12000);

        Passenger newPassenger;
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your name");
        String name = sc.nextLine();

        System.out.println("Please enter your surname");
        String surname = sc.nextLine();

        System.out.println("Please enter your email");
        String email = sc.nextLine();

        System.out.println("Please enter your phone number");
        String phoneNumber = sc.nextLine();

        System.out.println("Please enter your account balance");
        int accBalance = sc.nextInt();
        sc.nextLine();

        newPassenger = new Passenger(email, name, surname, phoneNumber, accBalance);

        System.out.println("Please enter your starting location");
        String startPoint = sc.nextLine();

        System.out.println("Please enter your final destination location");
        String endPoint = sc.nextLine();

        System.out.println("Please select your Uber type: XL or X");
        String vehicleType = sc.nextLine().toUpperCase();
        
        System.out.print("Starting Location: ");
        System.out.println(startPoint);
        System.out.print("End Location: ");
        System.out.println(endPoint);
        System.out.print("Uber Type: ");
        System.out.println(vehicleType.toUpperCase());

//        UberRide uberRide = new UberRide(startPoint, endPoint, lonwabo);
        UberRide uberRide = new UberRide(startPoint, endPoint, newPassenger);

        Driver assignedDriver = uberRide.assignDriver();

        System.out.println("\nCalculating cost...");
        uberRide.calculateCost(startPoint, endPoint);
        
        System.out.println("\nFinding you a driver...");
        System.out.println("Assigned driver: " + assignedDriver.getName().substring(0,1).toUpperCase() + assignedDriver.getName().substring(1) + " " + assignedDriver.getSurname().substring(0,1).toUpperCase() + assignedDriver.getSurname().substring(1));
        System.out.println("Assigned car: " + assignedDriver.getCar());
        
        System.out.println("\nYour ride is complete. Processing payment...");
//        uberRide.completePayment(assignedDriver, lonwabo);
        uberRide.completePayment(assignedDriver, newPassenger);
        

        
        

        // Person akhil = new Person("Akhil", "Boddu", "07838282", 123.12);
        // System.out.println(akhil);

        

        
    }

}