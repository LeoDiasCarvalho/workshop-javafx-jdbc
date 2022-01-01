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
import model.entidades.Departamento;
import model.service.DepartamentoServicos;

/**
 * @author leo_dias
 *
 */
public class ListaDepartamentoController implements Initializable {

	private DepartamentoServicos servico;

	@FXML
	private Button btnNovo;

	@FXML
	private TableView<Departamento> tableViewDepartamento;

	@FXML
	private TableColumn<Departamento, Integer> tableColunaId;

	@FXML
	private TableColumn<Departamento, String> tableColunaNome;
	
	private ObservableList<Departamento> obsList;

	@FXML
	public void onBtnNovoAction() {
		System.out.println("novo departamento");
	}

	public void setDepartamentoServico(DepartamentoServicos servico) {
		this.servico = servico;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
		tableViewDepartamento.prefWidthProperty().bind(stage.widthProperty());
	}
	
	public void atualizarTabela() {
		if(servico == null) {
			throw new IllegalStateException("Servico está nulo");
		}else {
			List<Departamento> list = servico.buscarTodos();
			obsList = FXCollections.observableArrayList(list);
			tableViewDepartamento.setItems(obsList);
		}
	}

}
