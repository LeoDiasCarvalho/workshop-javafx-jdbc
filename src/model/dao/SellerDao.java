/**
 * 
 */
package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

/**
 * @author leo_dias
 *
 */
public interface SellerDao {
	
	void insert(Seller obj);
	void upDate(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department department);

}
