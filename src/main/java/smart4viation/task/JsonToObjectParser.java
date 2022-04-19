package smart4viation.task;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class JsonToObjectParser {

    ArrayList<FlightCargo> getFLightCargoListFromJsonToObject(){

        String content;
        FlightCargo[] flightcargo = {};
        ArrayList<FlightCargo> flightCargoList = new ArrayList<>();
        try {
            String filePath = "src/main/resources/Flightcargo.json";
            content = new String(Files.readAllBytes(Paths.get(filePath)));

            ObjectMapper objectMapper = new ObjectMapper();

            flightcargo = objectMapper.readValue(content, FlightCargo[].class);

        } catch (IOException e){
            e.printStackTrace();
        }
        Collections.addAll(flightCargoList, flightcargo);
        return flightCargoList;
    }

    ArrayList<Flight> getFLightListFromJsonToObject(){

        String content;
        Flight[] flight = {};
        ArrayList<Flight> flightList = new ArrayList<>();
        try {
            String filePath = "src/main/resources/Flight.json";
            content = new String(Files.readAllBytes(Paths.get(filePath)));

            ObjectMapper objectMapper = new ObjectMapper();

            flight = objectMapper.readValue(content, Flight[].class);

        } catch (IOException e){
            e.printStackTrace();
        }
        Collections.addAll(flightList, flight);
        return flightList;
    }
}
