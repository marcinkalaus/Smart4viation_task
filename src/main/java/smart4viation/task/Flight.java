package smart4viation.task;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private Date departureDate;

    @OneToOne
    private FlightCargo flightCargo;
}
