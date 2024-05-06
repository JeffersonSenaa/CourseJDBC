package model.dao;

import model.entities.*;
import java.util.List;

public interface DepartamentDao {
    void insert(Department dep);
    void update(Department dep);
    void delete(Department dep);
    Department findBy(Integer id);
    List<Department> findAll();
}
