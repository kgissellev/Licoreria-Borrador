package Dao;

import model.Productos;
import org.springframework.data.repository.CrudRepository;

public interface ProductosRepository extends CrudRepository<Productos, Integer> {

    Productos findFirstByCodigo(String codigo);
}
