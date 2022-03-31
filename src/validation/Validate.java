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
			txtNome.setText("Não cadastrado");
		}
		if (txtApelido.getText().isEmpty()) {
			txtApelido.setText("Não cadastrado");
		}
		if (txtCPFMeliante.getText().isEmpty()) {
			txtCPFMeliante.setText("xxx.xxx.xxx-xx");
		}
		if (txtCaracteristicasFisicas.getText().isEmpty()) {
			txtCaracteristicasFisicas.setText("Não cadastrado");
		}
		if (txtTelefone.getText().isEmpty()) {
			txtTelefone.setText("(xx)xxxxx-xxxx");
		}
		if (txtDelitos.getText().isEmpty()) {
			txtDelitos.setText("Não cadastrado");
		}
		if (txtFaccao.getText().isEmpty()) {
			txtFaccao.setText("Não cadastrado");
		}

	}
	
	public static void validaEndereco(TextField txtCidade,TextField txtEstado,TextField txtBairro,TextField txtRua,TextField txtNumero) {
		if (txtCidade.getText().isEmpty()) {
			txtCidade.setText("Não cadastrado");
		}
		if (txtEstado.getText().isEmpty()) {
			txtEstado.setText("N/C");
		}
		if (txtBairro.getText().isEmpty()) {
			txtBairro.setText("Não cadastrado");
		}
		if (txtRua.getText().isEmpty()) {
			txtRua.setText("Não cadastrado");
		}
		if (txtNumero.getText().isEmpty()) {
			txtNumero.setText("N/C");
		}

	}
	

}
