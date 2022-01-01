/**
 * 
 */
package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.BancoException;
import banco.Conexao;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 * @author leo_dias
 *
 */
public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conexao;

	public DepartmentDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
					"INSERT INTO Department " 
					+ "(Name) "
					+ "VALUES (?) ", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				Conexao.FechamentoResultSet(rs);
			} else {
				throw new BancoException("Erro inesperado, nenhuma linha afetada!");
			}

		} catch (SQLException e) {
			throw new BancoException(e.getMessage());
		} finally {
			Conexao.FechamentoStatement(st);
		}

	}

	@Override
	public void upDate(Department obj) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE Id = ? ");
			
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new BancoException(e.getMessage());
		}
		finally {
			Conexao.FechamentoStatement(st);
		}

	}

	@Override  
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
					"DELETE FROM department "
					+ "WHERE Id = ? ");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new BancoException(e.getMessage());
		}
		finally {
			Conexao.FechamentoStatement(st);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement("SELECT * FROM department WHERE Id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instanciarDepartment(rs);
				return dep;
			}
			return null;
			
		} catch (SQLException e) {
			throw new BancoException(e.getMessage());
			
		} finally {
			Conexao.FechamentoStatement(st);
			Conexao.FechamentoResultSet(rs);
		}
	}
	
	private Department instanciarDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conexao.prepareStatement("SELECT * FROM department ORDER BY Name");

			rs = st.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {

				Department dep = instanciarDepartment(rs);
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			return list;
			
		} catch (SQLException e) {
			throw new BancoException(e.getMessage());
		} finally {
			Conexao.FechamentoStatement(st);
			Conexao.FechamentoResultSet(rs);
		}
	}

}
