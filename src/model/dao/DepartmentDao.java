/**
 * 
 */
package model.dao;

import java.util.List;

import model.entities.Department;

/**
 * @author leo_dias
 *
 */
public interface DepartmentDao {
	
	void insert(Department obj);
	void upDate(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();

}
