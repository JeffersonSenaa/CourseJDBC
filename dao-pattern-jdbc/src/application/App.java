package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
    
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: SellerDao ===");
        Seller seller = sellerDao.findBy(3);
        System.out.println(seller);

        System.out.println("\n\n=== Test 2: SellerDao: findByDepartment ===");
        Department dep = new Department(2, null);
        
        List<Seller> list = sellerDao.findByDepartament(dep);
        for (Seller obj : list) {
            System.out.println(obj);
        }
    }
}