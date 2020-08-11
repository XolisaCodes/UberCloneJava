public class Car implements Vehicle {

    // Fields:
    String numberPlate;
    String colour;
    String model;
    String type;
    double base_rate;

    //Constructor
    Car(String numberPlate, String colour, String model, String type) {
        this.numberPlate = numberPlate;
        this.colour = colour;
        this.model = model;
        this.type = type;
        if(type.equals("XL")) {
            this.base_rate = 15.00;
        } else {
            this.base_rate = 10.00;
        }
    }

    //Methods:

    // Getters:
    public String getNumberPlate() {
        return this.numberPlate;
    }

    public double getBaseRate() {
        return this.base_rate;
    }

    public String getColor() {
        return this.colour;
    }
    public String getModel() {
        return this.model;
    }

    public String getVehicleType() {
        return this.type;
    }

    public String toString() {
        return this.model + " car of " + this.colour + " colour, with number plate: " + this.numberPlate;
    }
}