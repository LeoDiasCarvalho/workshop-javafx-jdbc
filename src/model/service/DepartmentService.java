/**
 * 
 */
package model.service;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 * @author leo_dias
 *
 */
public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();

	public List<Department> buscarTodos() {
		
		return dao.findAll();
	}
}
