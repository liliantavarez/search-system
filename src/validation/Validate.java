package validation;

import javafx.scene.control.TextField;

public class Validate {
	public static boolean valida(TextField texto) {
		if (texto != null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validaUsuario(TextField txtNome, TextField txtUsuario, TextField txtSenha,
			TextField txtConfsenha, TextField txtEmail) {
		if (txtNome.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()
				|| txtConfsenha.getText().isEmpty() || txtEmail.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	public static void validarMeliante(TextField txtNome, TextField txtApelido, TextField txtCPF, TextField txtCaracFisicas, TextField txtTelefone, TextField txtDelitos, TextField txtFaccao){
		if (txtNome.getText().isEmpty()) {
			txtNome.setText("Não cadastrado");
		}
		if (txtApelido.getText().isEmpty()) {
			txtApelido.setText("Não cadastrado");
		}
		if (txtCPF.getText().isEmpty()) {
			txtCPF.setText("xxx.xxx.xxx-xx");
		}
		if (txtCaracFisicas.getText().isEmpty()) {
			txtCaracFisicas.setText("Não cadastrado");
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
	
	public static void validaEndereco( TextField txtCidade, TextField txtUF, TextField txtBairro, TextField txtRua,TextField txtNumero) {
		if (txtCidade.getText().isEmpty()) {
			txtCidade.setText("Não cadastrado");
		}
		if (txtUF.getText().isEmpty()) {
			txtUF.setText("N/C");
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
