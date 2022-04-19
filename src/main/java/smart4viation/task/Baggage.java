package smart4viation.task;

import lombok.*;

import javax.persistence.*;

@Entity(name = "baggage")
@Table(name = "baggage")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Baggage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int weight;
    private String weightUnit;
    private int pieces;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flightcargo_id")
    FlightCargo flightCargo;

    Baggage(final int weight, final String weightUnit, final int pieces) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }
}
