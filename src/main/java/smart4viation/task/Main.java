package smart4viation.task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        //read json data from files
        JsonToObjectParser json = new JsonToObjectParser();

        ArrayList<FlightCargo> flightCargoList = json.getFLightCargoListFromJsonToObject();
        ArrayList<Flight> flightList = json.getFLightListFromJsonToObject();

        //do references between flind and flightcargo
        flightCargoList.forEach(x -> x.setFlight(flightList.stream().filter(e -> e.getId() == x.getId()).findFirst().get()));
        //do references between flightcargo and baggage, cargo
        flightCargoList.forEach(x -> x.getBaggage().stream().forEach(e -> e.setFlightCargo(x)));
        flightCargoList.forEach(x -> x.getCargo().stream().forEach(e -> e.setFlightCargo(x)));


        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {

            transaction = session.beginTransaction();

            flightList.forEach(session::persist);
            flightCargoList.forEach(session::persist);
            flightCargoList.forEach(x -> x.getBaggage().stream().forEach(session::persist));
            flightCargoList.forEach(x -> x.getCargo().stream().forEach(session::persist));

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        SelectQuery flight = new SelectQuery();

        StringBuilder selectOptionMessage = new StringBuilder();
        selectOptionMessage.append("Select your option \n");
        selectOptionMessage.append("1 - Flight Number\n");
        selectOptionMessage.append("2 - IATA Code\n");

        System.out.println(selectOptionMessage);
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            option = scanner.nextInt();
        } while (option != 1 && option != 2);

        String date;
        switch (option) {
            case 1:
                System.out.print("Write the flight number: ");
                int fNumber = scanner.nextInt();
                System.out.print("Write the date of flight in format YYYY-MM-DD: ");
                date = scanner.next();
                getFlightInfo(fNumber, date, transaction, session, flight);
                break;
            case 2:
                System.out.print("Write the IATA code: ");
                String iataCode = scanner.next();
                System.out.print("Write the date of flight in format YYYY-MM-DD: ");
                date = scanner.next();
                getIataInfo(iataCode, date, transaction, session, flight);
                break;
            default:
                break;
        }

        session.getSessionFactory().close();
    }


    private static void getFlightInfo(final int fNumber, final String date, final Transaction transaction, final Session session, SelectQuery flight) {
        Long cargoWeight = flight.getCargoWeightForRequestedFlight(fNumber, date, transaction, session);
        Long baggageWeight = flight.getBaggageWeightForRequestedFlight(fNumber, date, transaction, session);
        StringBuilder flightNumberAnswer = new StringBuilder();

        flightNumberAnswer.append("Cargo Weight: " + cargoWeight + "kg\n");
        flightNumberAnswer.append("Baggage Weight: " + baggageWeight + "kg\n");
        flightNumberAnswer.append("Total Weight: " + (cargoWeight + baggageWeight) + "kg\n");

        System.out.println(flightNumberAnswer);
    }

    private static void getIataInfo(final String iataCode, final String date, final Transaction transaction, final Session session, SelectQuery flight) {
        Long departuresAmount = flight.getNumberOfDepartingFlightsForRequestedIATA(iataCode, date, transaction, session);
        Long arrivalsAmount = flight.getNumberOfArrivingFlightsForRequestedIATA(iataCode, date, transaction, session);
        Long arrivingBaggageAmount = flight.getNumberOfArrivingBaggageForRequestedIATA(iataCode, date, transaction, session);
        Long departingBaggageAmount = flight.getNumberOfDepartingBaggageForRequestedIATA(iataCode, date, transaction, session);

        StringBuilder iataCodeAnswer = new StringBuilder();
        iataCodeAnswer.append("Number of flights departing from the airport: " + departuresAmount + "\n");
        iataCodeAnswer.append("Number of flights arriving to the airport: " + arrivalsAmount + "\n");
        iataCodeAnswer.append("Total number of baggage departing from the airport: " + departingBaggageAmount + "\n");
        iataCodeAnswer.append("Total number of baggage arriving to the airport: " + arrivingBaggageAmount);

        System.out.println(iataCodeAnswer);
    }
}
