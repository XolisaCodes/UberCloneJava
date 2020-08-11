public class Person {

    //Fields:
    private String name;
    private String surname;
    private String phone_number;
    private double balance;

    //Constructor
    Person(String name, String surname, String phone_number, double balance) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.balance = balance;
    }

    //Methods:
    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setCash(double balance) {
        this.balance = balance;
    }

    //Getters
    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
    
    public String getPhoneNumber(){
        return phone_number;
    }
    
    public double getCash(){
        return balance;
    }

    public String toString() {
        return name + " " + surname;
    }
}