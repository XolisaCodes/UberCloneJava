import java.io.*;

public class Database {

    // Fields:
    int numberOfXL;
    int numberOfX;
    Driver[] drivers;

    //Constructor
    Database() {}

    public int countLinesInCSV(String filename) {
        int i = 0;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filename));
            String row = null;
            while ((row = csvReader.readLine()) != null) {
                i++;
            }
            csvReader.close();
        }
        catch(Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return i;
    }

    // Methods:

    // Getters:
    public void getDriversArray() {

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("drivers.csv"));
            String row = null;
            int i = 0;
            this.drivers = new Driver[countLinesInCSV("drivers.csv")-1];
            while ((row = csvReader.readLine()) != null) {
                if(i > 0) {
                    String[] data = row.split(",");
                    Car car = new Car(data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim());
                    Driver driver = new Driver(car, data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), Integer.parseInt(data[4].trim()));

                    if(data[8].trim().equals("X")) {
                        this.numberOfX++;
                    }
                    if(data[8].trim().equals("XL")) {
                        this.numberOfXL++;
                    }

                    this.drivers[i-1] = driver;
                }
                i++;
                
            }
            csvReader.close();
        }
        catch(Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public Driver[] getArrayByVehicleType(String vehicleType) {

        Driver[] Xdrivers = new Driver[this.numberOfX];
        Driver[] XLdrivers = new Driver[this.numberOfXL];

        int xindex = 0;
        int xlindex = 0;
        for(int i=0; i<this.drivers.length; i++) {
            if(this.drivers[i].getCar().getVehicleType().equals("X")) {
                Xdrivers[xindex] = this.drivers[i];
                xindex++;
            }
            if(this.drivers[i].getCar().getVehicleType().equals("XL")) {
                XLdrivers[xlindex] = this.drivers[i];
                xlindex++;
            }
        }

        if(vehicleType.equals("X")) {
            return Xdrivers;
        }

        return XLdrivers;
    }
}