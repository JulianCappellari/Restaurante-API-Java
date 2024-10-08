package pp.restaurante.transformers;

import org.springframework.stereotype.Component;
import pp.restaurante.dto.TableDto;
import pp.restaurante.entities.Table;


@Component
public class TableMapper {

    public TableDto toTableDto(Table table) {
        return new TableDto(
                table.getId(),
                table.getTableNum(),
                table.getAbility(),
                table.getState()
        );
    }

    public Table toEntity(TableDto tableDto) {
        return new Table(
                tableDto.getId(),
                tableDto.getTableNum(),
                tableDto.getAbility(),
                tableDto.getState()
        );
    }
}
