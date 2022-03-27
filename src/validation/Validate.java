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
			nome = "Não cadastrado";
		}
		if (apelido.isEmpty()) {
			apelido = "Não cadastrado";
		}
		if (CPFMeliante.isEmpty()) {
			CPFMeliante = "xxx.xxx.xxx-xxx";
		}
		if (caracteristicasFisicas.isEmpty()) {
			caracteristicasFisicas = "Não cadastrado";
		}
		if (telefone.isEmpty()) {
			telefone = "(xx)xxxxx-xxxx";
		}
		if (delitos.isEmpty()) {
			delitos = "Não cadastrado";
		}
		if (faccao.isEmpty()) {
			faccao = "Não cadastrado";
		}
	}
	
	public static void validaEndereco(String cidade,String estado,String bairro,String rua,String numero) {
		if (cidade.isEmpty()) {
			cidade = "Não cadastrado";
		}
		if (estado.isEmpty()) {
			estado = "N/C";
		}
		if (bairro.isEmpty()) {
			bairro = "Não cadastrado";
		}
		if (rua.isEmpty()) {
			rua = "Não cadastrado";
		}
		if (numero.isEmpty()) {
			numero = "N/C";
		}


	}
	

}
