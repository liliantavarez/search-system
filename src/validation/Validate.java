package validation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Validate {
	public static boolean valida(TextField texto) {
		if(texto != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean validaUsuario(TextField txtNome, TextField txtUsuario,TextField txtSenha, TextField txtConfsenha, TextField txtEmail) {
		if(txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty() || txtConfsenha.getText().isEmpty() || txtEmail.getText().isEmpty()) {
			return false;			
		}
		return true;
	}


}
