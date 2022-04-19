package smart4viation.task;

import lombok.*;

import javax.persistence.*;

@Entity(name = "cargo")
@Table(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int weight;
    private String weightUnit;
    private int pieces;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flightcargo_id")
    FlightCargo flightCargo;

    Cargo(final int weight, final String weightUnit, final int pieces) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }
}
