/**
 * 
 */
package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entidades.Departamento;

/**
 * @author leo_dias
 *
 */
public class DepartamentoServicos {

	public List<Departamento> buscarTodos() {
		List<Departamento> list = new ArrayList<>();

		list.add(new Departamento(1, "Books"));
		list.add(new Departamento(2, "Computers"));
		list.add(new Departamento(3, "Electronics"));

		return list;
	}
}
