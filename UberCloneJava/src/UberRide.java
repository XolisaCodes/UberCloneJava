import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;
import java.util.*;

public class UberRide extends Ride {

    // Fields:
    String startPoint;
    String endpoint;
    double price;
    double rate;
    Driver driver;
    Passenger passenger;
    double distance;

    // Constructors:
    UberRide() {}

    UberRide(String startPoint, String endpoint, Passenger passenger){
        this.startPoint = startPoint;
        this.endpoint = endpoint;
        this.price = 0.00;
        this.rate = 0.00;
        this.driver = null;
        this.passenger = passenger;
    }

   
    @ Override
    public Driver assignDriver() {

        Database DB = new Database();
        DB.getDriversArray();
        Driver[] drivers = DB.getArrayByVehicleType("XL");

        Random ran = new Random();
        int randomIndex = ran.nextInt(drivers.length-1);

        this.driver = drivers[randomIndex];
        
        return drivers[randomIndex];
    }

    @ Override
    public void completePayment(Driver driver, Passenger passenger) {

        double driverBalance = driver.getCash();
        driver.setCash(driverBalance + this.price);
        System.out.println("Adding R" + (int)this.price + " to driver account. \nDriver Account balance: R" + Math.round(driver.getCash() * 100.0) / 100.0);

        double passengerBalance = passenger.getCash();
        passenger.setCash(passengerBalance - this.price);
        System.out.println("Deducting R" + (int)this.price + " from passenger account. \nPassenger Account balance: R" + Math.round(passenger.getCash() * 100.0) / 100.0);
    }

    @ Override
    public double calculateDistance(String startingPoint, String endingPoint) {
        double distance = 0.0;
        try {
            UberRide uberRide = new UberRide();
            distance = uberRide.MyGETRequest(startingPoint, endingPoint);
        }
        catch(IOException ex){
            System.out.println ("Error: \n" + ex.toString());
        }

        this.distance = distance / 1000;
        System.out.println("Trip distance: " + this.distance + " km");
        return (distance/1000);
    }

    @ Override
    public double calculateCost(String startingPoint, String endingPoint) {

        double cost = 0.0;
        UberRide uberRide = new UberRide();
        double distance = uberRide.calculateDistance(startingPoint, endingPoint);
        cost = distance * this.driver.getCar().getBaseRate();
        price = cost;
        System.out.println("Trip cost: R" + (Math.round(price * 100.0) / 100.0));
        return cost;
    }

    public double MyGETRequest(String startingPoint, String endingPoint) throws IOException {
        URL urlForGetRequest = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + startingPoint +",SA&destinations=" + endingPoint + ",SA&departure_time=now&key=AIzaSyCs2UIPeA_ygj6aDL45ta9ZdJu3Mo1PIOs");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();


        UberRide uberRide = new UberRide();
        double distance = 0.0;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            } in .close();
            String distanceAsString = uberRide.retrieveDistanceAsString(response.toString());
            distance = Double.parseDouble(distanceAsString);
        } else {
            System.out.println("GET NOT WORKED");
        }

        return distance;
    }

    public String retrieveDistanceAsString(String jsonString) {
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
        Gson gson = builder.create(); 
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class); 
        JsonArray jsonArray = jsonObject.getAsJsonArray("rows");
           
        JsonElement je = null;
        Iterator<JsonElement> iterator = jsonArray.iterator();
         while(iterator.hasNext()) {
             je = iterator.next();
             jsonArray = je.getAsJsonObject().getAsJsonArray("elements");
         }

         iterator = jsonArray.iterator();
         while(iterator.hasNext()) {
             je = iterator.next();
             jsonObject = je.getAsJsonObject().get("distance").getAsJsonObject();
         }

         return jsonObject.get("value") + "";
    }
}