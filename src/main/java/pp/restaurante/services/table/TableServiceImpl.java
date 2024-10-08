package pp.restaurante.services.table;

import org.springframework.stereotype.Service;
import pp.restaurante.entities.Table;
import pp.restaurante.respositories.TableRepository;
import pp.restaurante.transformers.TableMapper;

import java.util.List;
import java.util.Optional;


@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;
    public TableServiceImpl(TableRepository tableRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.tableMapper = tableMapper;

    }
    @Override
    public Table add(Table entity) {
        return tableRepository.save(entity);
    }

    @Override
    public Optional<Table> getById(Long id) {
        Table table = tableRepository.findById(id).orElseThrow(() -> new IllegalStateException("Table not found"));
        return tableRepository.findById(id);
    }

    @Override
    public List<Table> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Table update(Table entity, Long id) {
        return tableRepository.findById(id).map( table -> {
            table.setId(table.getId());
            table.setTableNum(table.getTableNum() );
            table.setAbility(table.getAbility());
            table.setState(table.getState());
            return tableRepository.save(table);
        }).orElse(null);
    }
}
