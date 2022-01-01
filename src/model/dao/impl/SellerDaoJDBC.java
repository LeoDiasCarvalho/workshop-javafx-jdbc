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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.BancoException;
import banco.Conexao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/**
 * @author leo_dias
 *
 */
public class SellerDaoJDBC implements SellerDao {

	// fazendo a dependencia com a conexao com o banco de dados
	private Connection conexao;

	public SellerDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
					"INSERT INTO SELLER "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?) ", 
					Statement.RETURN_GENERATED_KEYS);
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				Conexao.FechamentoResultSet(rs);
			}
			else {
				throw new BancoException("Erro inesperado, nenhuma linha afetada!");
			}
			
		}
		catch(SQLException e) {
			throw new BancoException(e.getMessage());
		}
		finally {
			Conexao.FechamentoStatement(st);
		}
	}

	@Override
	public void upDate(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ? ");
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
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
					"DELETE FROM seller "
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
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? " + "ORDER BY Name ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instanciarDepartment(rs);
				Seller seller = instanciarSeller(rs, dep);

				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new BancoException(e.getMessage());
		} finally {
			Conexao.FechamentoStatement(st);
			Conexao.FechamentoResultSet(rs);
		}
	}

	private Seller instanciarSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instanciarDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " 
					+ "ORDER BY Name ");

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instanciarDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = instanciarSeller(rs, dep);
				list.add(seller);
			}
			return list;
		} catch (SQLException e) {
			throw new BancoException(e.getMessage());
		} finally {
			Conexao.FechamentoStatement(st);
			Conexao.FechamentoResultSet(rs);
		}

	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name ");

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instanciarDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = instanciarSeller(rs, dep);
				list.add(seller);
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
