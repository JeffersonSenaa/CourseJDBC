package model.dao;

import model.entities.*;
import java.util.List;

public interface SellerDao {
    void insert(Seller dep);
    void update(Seller dep);
    void delete(Seller dep);
    Seller findBy(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartament(Department department);
}
