package pp.restaurante.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pp.restaurante.enums.StateEnum;

@Entity(name = "Mesa")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tableNum")
    private int tableNum;

    @Column(name = "ability")
    private Integer ability;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateEnum state;
}
