/**
 * 
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.service.DepartamentoServicos;

/**
 * @author leo_dias
 *
 */
public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemDepartamento;

	@FXML
	private MenuItem menuItemVendedor;

	@FXML
	private MenuItem menuItemSobre;

	@FXML
	public void onMenuItemDepartamentoAction() {
		carregarTela("/gui/ListaDepartamento.fxml", (ListaDepartamentoController controller) -> {
			controller.setDepartamentoServico(new DepartamentoServicos());
			controller.atualizarTabela();
		});
	}

	@FXML
	public void onMenuItemVendedorAction() {
		System.out.println("Cadastrou vendedor");
	}

	@FXML
	public void onMenuItemSobreAction() {
		carregarTela("/gui/Sobre.fxml", x -> {});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	public synchronized <T> void carregarTela(String nomeCompleto, Consumer<T> acaoDeInicializacao) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeCompleto));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();

			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			acaoDeInicializacao.accept(controller);

		} catch (IOException e) {
			Alerts.mostrarAlerta("IOException", "Erro ao carregar view", e.getMessage(), AlertType.ERROR);
		}

	}

}
