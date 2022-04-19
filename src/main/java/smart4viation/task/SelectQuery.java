package smart4viation.task;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class SelectQuery {

    public Long getCargoWeightForRequestedFlight(int fNumber, String date, Transaction transaction, Session session) {

        Long cargoWeight = 0L;
        try {
            transaction = session.beginTransaction();

            String CARGO_QUERY = "select sum(case when c.weightUnit = 'kg' then c.weight else (c.weight * 0.454) end) as cargo_weight " +
                    "from flight f " +
                    "inner join f.flightCargo fc " +
                    "inner join fc.cargo c " +
                    "where f.flightNumber=:fNumber and cast(f.departureDate as string) like:date";

            Query cargo = session.createQuery(CARGO_QUERY);
            cargo.setParameter("fNumber", fNumber);
            cargo.setParameter("date", date + "%");

            if (cargo.getSingleResult() != null) {
                cargoWeight = (Long) cargo.getSingleResult();
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cargoWeight;
    }

    public Long getBaggageWeightForRequestedFlight(int fNumber, String date, Transaction transaction, Session session) {

        Long baggageWeight = 0L;
        try {
            transaction = session.beginTransaction();

            String BAGGAGE_QUERY = "select sum(case when b.weightUnit = 'kg' then b.weight else (b.weight * 0.454) end) as baggage_weight " +
                    "from flight f " +
                    "inner join f.flightCargo fc " +
                    "inner join fc.baggage b " +
                    "where f.flightNumber=:fNumber and cast(f.departureDate as string) like:date";

            Query baggage = session.createQuery(BAGGAGE_QUERY);
            baggage.setParameter("fNumber", fNumber);
            baggage.setParameter("date", date + "%");

            if(baggage.getSingleResult() != null)
            baggageWeight = (Long) baggage.getSingleResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return baggageWeight;
    }

    public Long getNumberOfDepartingFlightsForRequestedIATA(String iataCode, String date, Transaction transaction, Session session) {

        Long numberOfDepartingFlights = 0L;
        try {
            transaction = session.beginTransaction();

            String DEPARTURE_QUERY = "select count (*)" +
                    "from flight f " +
                    "where f.departureAirportIATACode =:iataCode and cast(f.departureDate as string) like:date";

            Query departure = session.createQuery(DEPARTURE_QUERY);
            departure.setParameter("iataCode", iataCode);
            departure.setParameter("date", date + "%");

            numberOfDepartingFlights = (Long) departure.getSingleResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return numberOfDepartingFlights;
    }

    public Long getNumberOfArrivingFlightsForRequestedIATA(String iataCode, String date, Transaction transaction, Session session) {

        Long numberOfDepartingFlights = 0L;
        try {
            transaction = session.beginTransaction();

            String ARRIVAL_QUERY = "select count (*)" +
                    "from flight f " +
                    "where f.arrivalAirportIATACode =:iataCode and cast(f.departureDate as string) like:date";

            Query departure = session.createQuery(ARRIVAL_QUERY);
            departure.setParameter("iataCode", iataCode);
            departure.setParameter("date", date + "%");

            numberOfDepartingFlights = (Long) departure.getSingleResult();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return numberOfDepartingFlights;
    }

    public Long getNumberOfArrivingBaggageForRequestedIATA(String iataCode, String date, Transaction transaction, Session session) {

        Long arrivingBaggageAmount = 0L;
        try {
            transaction = session.beginTransaction();

            String ARRIVAL_QUERY = "select sum(b.pieces)" +
                    "from flight f " +
                    "inner join f.flightCargo fc " +
                    "inner join fc.baggage b " +
                    "where f.arrivalAirportIATACode =:iataCode and cast(f.departureDate as string) like:date";

            Query arrival = session.createQuery(ARRIVAL_QUERY);
            arrival.setParameter("iataCode", iataCode);
            arrival.setParameter("date", date + "%");

            if (arrival.getSingleResult() != null) {
                arrivingBaggageAmount = (Long) arrival.getSingleResult();
            }


            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return arrivingBaggageAmount;
    }

    public Long getNumberOfDepartingBaggageForRequestedIATA(String iataCode, String date, Transaction transaction, Session session) {

        Long departingBaggageAmount = 0L;
        try {
            transaction = session.beginTransaction();

            String DEPARTURE_QUERY = "select sum(b.pieces)" +
                    "from flight f " +
                    "inner join f.flightCargo fc " +
                    "inner join fc.baggage b " +
                    "where f.departureAirportIATACode =:iataCode and cast(f.departureDate as string) like:date";

            Query departure = session.createQuery(DEPARTURE_QUERY);
            departure.setParameter("iataCode", iataCode);
            departure.setParameter("date", date + "%");

            if (departure.getSingleResult() != null) {
                departingBaggageAmount = (Long) departure.getSingleResult();
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return departingBaggageAmount;
    }

}

