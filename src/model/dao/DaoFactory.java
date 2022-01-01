/**
 * 
 */
package model.dao;

import banco.Conexao;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

/**
 * @author leo_dias
 *
 */
public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(Conexao.Conectar());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(Conexao.Conectar());
	}
}
