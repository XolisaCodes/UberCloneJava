public class Driver extends Person {

    //Fields:
    Car car;
    String licenseID;
    
    //Constructor
    Driver(Car car, String licenseID, String name, String surname,String phone_number, int balance){
        super(name,surname,phone_number,balance);   
        this.car = car;
        this.licenseID = licenseID;    
    }

    //Methods:

    //Setters
    public void setCar(Car car){
        this.car = car;
    }

    public void setlicenseID(String licenseID){
        this.licenseID = licenseID;
    }

    // Getters:
    public Car getCar(){
        return car;
    }

    public String getlicenseID(){
        return  licenseID;
    }

    public String toString(){
        return super.toString() + " and I am a driver";
    }
}