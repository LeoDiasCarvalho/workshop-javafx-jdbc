/**
 * 
 */
package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @author leo_dias
 *
 */
public class Utils {

	public static Stage palcoAtual(ActionEvent evento) {
		return (Stage)((Node)evento.getSource()).getScene().getWindow();
	}
}
