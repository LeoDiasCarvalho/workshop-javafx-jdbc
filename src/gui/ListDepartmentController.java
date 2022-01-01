/**
 * 
 */
package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.service.DepartmentService;

/**
 * @author leo_dias
 *
 */
public class ListDepartmentController implements Initializable {

	private DepartmentService service;

	@FXML
	private Button btnNovo;

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColunaId;

	@FXML
	private TableColumn<Department, String> tableColunaNome;

	private ObservableList<Department> obsList;

	@FXML
	public void onBtnNovoAction() {
		System.out.println("novo departamento");
	}

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColunaId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tableColunaNome.setCellValueFactory(new PropertyValueFactory<>("Name"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		tableViewDepartment.prefWidthProperty().bind(stage.widthProperty());
	}

	public void atualizarTabela() {
		if (service == null) {
			throw new IllegalStateException("Servico está nulo");
		} else {
			List<Department> list = service.buscarTodos();
			obsList = FXCollections.observableArrayList(list);
			tableViewDepartment.setItems(obsList);
		}
	}

}
