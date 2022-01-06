/**
 * 
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

/**
 * @author leo_dias
 *
 */
public class DepartmentFormController implements Initializable {

	private Department entity;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label lblErroName;

	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	@FXML
	public void onBtnSalvarAction() {

		System.out.println("Salvo");
	}

	@FXML
	public void onBtnCancelarAction() {
		System.out.println("Cancelado");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldLength(txtName, 30);
	}

	public void upDateFormData() {

		if (entity == null) {
			throw new IllegalStateException("Entidade está nula!");
		} else {
			txtId.setText(String.valueOf(entity.getId()));
			txtName.setText(String.valueOf(entity.getName()));
		}
	}

}
