/**
 * 
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("Cadastrou departamento");
	}

	@FXML
	public void onMenuItemVendedorAction() {
		System.out.println("Cadastrou vendedor");
	}

	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("Ajuda sobre");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

}
