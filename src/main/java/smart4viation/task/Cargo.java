package smart4viation.task;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int weight;
    private int weightUnit;
    private int pieces;
}
