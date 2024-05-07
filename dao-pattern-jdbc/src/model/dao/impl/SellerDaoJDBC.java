package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartamentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }


    @Override
    public void insert(Seller dep) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Seller dep) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Seller dep) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Seller findBy(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                                        + "FROM seller INNER JOIN department "
                                        + "ON seller.DepartmentId = department.Id "
                                        + "WHERE seller.Id = ?");      
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dep);
                return seller;
            }
            return null;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();
            seller.setName(rs.getString("Name"));
            seller.setId(rs.getInt("Id"));
            seller.setEmail(rs.getString("Email"));
            seller.setBaseSalary(rs.getDouble("BaseSalary"));
            seller.setBirthDate(rs.getDate("BirthDate"));
            seller.setDepartment(dep);  //Associação
            return seller;
    }


    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }


    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public List<Seller> findByDepartament(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
                                        + "FROM seller INNER JOIN department "
                                        + "ON seller.DepartmentId = department.Id "
                                        + "WHERE DepartmentId = ? ORDER BY Name");
            st.setInt(1, department.getId());

            rs = st.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> depMap = new HashMap<>();

            while(rs.next()){
                //Verifica se há departamento com o Id instanciado
                Department dep = depMap.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    dep = instantiateDepartment(rs);
                    depMap.put(rs.getInt("DepartmentId"), dep);
                }
                
                Seller seller = instantiateSeller(rs, dep);
                sellers.add(seller);
            }
            return sellers;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
} 