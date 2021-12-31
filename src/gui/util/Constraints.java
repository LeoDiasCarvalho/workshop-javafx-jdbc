/**
 * 
 */
package gui.util;

import javafx.scene.control.TextField;

/**
 * @author leo_dias
 *
 */
public class Constraints {

	public static void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs, valor, novoValor) -> {
			if (novoValor != null && !novoValor.matches("\\d*")) {
				txt.setText(valor);
			}
		});
	}

	public static void setTextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, valor, novoValor) -> {
			if (novoValor != null && !novoValor.matches("\\d*([\\d.]\\d*)?")) {
				txt.setText(valor);
			}
		});
	}

	public static void setTextFieldLength(TextField txt, Integer max) {
		txt.textProperty().addListener((obs, valor, novoValor) -> {
			if (novoValor != null && novoValor.length() > max) {
				txt.setText(valor);
			}
		});
	}

}
