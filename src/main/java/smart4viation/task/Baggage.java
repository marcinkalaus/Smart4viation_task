package smart4viation.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "baggage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Baggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int weight;
    private int weightUnit;
    private int pieces;
}
