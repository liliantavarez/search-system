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

	public static void validarMeliante(String nome,String apelido,String CPFMeliante,
			String caracteristicasFisicas,String telefone,String delitos,String faccao){
		
		if (nome.isEmpty()) {
			nome = "N�o cadastrado";
		}
		if (apelido.isEmpty()) {
			apelido = "N�o cadastrado";
		}
		if (CPFMeliante.isEmpty()) {
			CPFMeliante = "xxx.xxx.xxx-xxx";
		}
		if (caracteristicasFisicas.isEmpty()) {
			caracteristicasFisicas = "N�o cadastrado";
		}
		if (telefone.isEmpty()) {
			telefone = "(xx)xxxxx-xxxx";
		}
		if (delitos.isEmpty()) {
			delitos = "N�o cadastrado";
		}
		if (faccao.isEmpty()) {
			faccao = "N�o cadastrado";
		}
	}
	
	public static void validaEndereco(String cidade,String estado,String bairro,String rua,String numero) {
		if (cidade.isEmpty()) {
			cidade = "N�o cadastrado";
		}
		if (estado.isEmpty()) {
			estado = "N/C";
		}
		if (bairro.isEmpty()) {
			bairro = "N�o cadastrado";
		}
		if (rua.isEmpty()) {
			rua = "N�o cadastrado";
		}
		if (numero.isEmpty()) {
			numero = "N/C";
		}


	}
	

}
