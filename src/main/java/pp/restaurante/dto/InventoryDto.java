package pp.restaurante.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
    private Long id;
    private String name;
    private Integer amount;
    private Integer minimumThreshold;
}
