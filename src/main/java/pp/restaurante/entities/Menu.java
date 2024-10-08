package pp.restaurante.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameDish")
    private String nameDish;

    @Column(name = "price")
    private double price;

    @Column(name = "available")
    private boolean available;
}
