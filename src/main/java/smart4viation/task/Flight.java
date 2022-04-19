package smart4viation.task;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "flight")
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {
    @JsonAlias({"flightId"})
    @Id
    private int id;

    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private Date departureDate;


    @OneToOne(mappedBy = "flight")
    private FlightCargo flightCargo;

    Flight(final int flightNumber, final String departureAirportIATACode, final String arrivalAirportIATACode, final Date departureDate) {
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }
}
