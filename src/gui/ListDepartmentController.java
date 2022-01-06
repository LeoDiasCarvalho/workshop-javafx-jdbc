/**
 * 
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.NonReadableChannelException;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.java.swing.action.NewAction;
import com.sun.tools.javac.jvm.PoolConstant.LoadableConstant;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.internal.loader.Loader;
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
	public void onBtnNovoAction(ActionEvent evento) {

		Stage parentStage = Utils.palcoAtual(evento);
		Department obj = new Department();	
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
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

	private void createDialogForm(Department obj, String nomeCompleto, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeCompleto));
			Pane pane = loader.load();
			
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.upDateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com dados do Departamento");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);// Não consegue mecher em outras janelas sem fechar está
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.mostrarAlerta("IO Exception", "Erro ao carregar tela!", e.getMessage(), AlertType.ERROR);
		}
	}
}
