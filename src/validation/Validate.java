package validation;

import javafx.scene.control.TextField;

public class Validate {
	public static boolean valida(TextField texto) {
		if(texto != null) {
			return true;
		}
		else {
			return false;
		}
	}


}
