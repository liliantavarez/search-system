package validation;

import javafx.scene.control.TextField;

public class Validate {

	public static boolean validaUsuario(TextField txtNome, TextField txtUsuario, TextField txtSenha,
			TextField txtConfsenha, TextField txtEmail) {
		if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()
				|| txtConfsenha.getText().isEmpty() || txtEmail.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	public static void validarMeliante(TextField txtNome,TextField txtApelido,TextField txtCPFMeliante,
			TextField txtCaracteristicasFisicas,TextField txtTelefone,TextField txtDelitos,TextField txtFaccao){
		
		if (txtNome.getText().isEmpty()) {
			txtNome.setText("N�o cadastrado");
		}
		if (txtApelido.getText().isEmpty()) {
			txtApelido.setText("N�o cadastrado");
		}
		if (txtCPFMeliante.getText().isEmpty()) {
			txtCPFMeliante.setText("xxx.xxx.xxx-xx");
		}
		if (txtCaracteristicasFisicas.getText().isEmpty()) {
			txtCaracteristicasFisicas.setText("N�o cadastrado");
		}
		if (txtTelefone.getText().isEmpty()) {
			txtTelefone.setText("(xx)xxxxx-xxxx");
		}
		if (txtDelitos.getText().isEmpty()) {
			txtDelitos.setText("N�o cadastrado");
		}
		if (txtFaccao.getText().isEmpty()) {
			txtFaccao.setText("N�o cadastrado");
		}

	}
	
	public static void validaEndereco(TextField txtCidade,TextField txtEstado,TextField txtBairro,TextField txtRua,TextField txtNumero) {
		if (txtCidade.getText().isEmpty()) {
			txtCidade.setText("N�o cadastrado");
		}
		if (txtEstado.getText().isEmpty()) {
			txtEstado.setText("N/C");
		}
		if (txtBairro.getText().isEmpty()) {
			txtBairro.setText("N�o cadastrado");
		}
		if (txtRua.getText().isEmpty()) {
			txtRua.setText("N�o cadastrado");
		}
		if (txtNumero.getText().isEmpty()) {
			txtNumero.setText("N/C");
		}

	}
	

}
