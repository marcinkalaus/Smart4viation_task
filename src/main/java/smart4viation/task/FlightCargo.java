package smart4viation.task;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity(name = "flightcargo")
@Table(name = "flightcargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightCargo {

    @JsonAlias({"flightId"})
    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Flight flight;

    @JsonUnwrapped
    @OneToMany(mappedBy = "flightCargo")
    private List<Baggage> baggage;

    @JsonUnwrapped
    @OneToMany(mappedBy = "flightCargo")
    private List<Cargo> cargo;

}
