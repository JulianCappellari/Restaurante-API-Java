package pp.restaurante.services;

import java.util.List;
import java.util.Optional;

public interface Service<T, W> {
    T add(T entity);
    Optional<T> getById(W id);
    List<T> getAll();
    boolean delete(W id);
    T update(T entity, W id);
}
