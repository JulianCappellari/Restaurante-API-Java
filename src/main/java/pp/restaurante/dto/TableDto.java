package pp.restaurante.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pp.restaurante.enums.StateEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDto {
    private Long id;
    private Integer tableNum;
    private Integer ability;
    private StateEnum state;
}
