package controllers;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Criptografar;
import model.utils.Load;
import model.utils.TextFieldFormatter;
import validation.Validate;

public class CadUsuarioController {

	@FXML private TextField txtNome, txtUsuario, txtSenha, txtConfsenha, txtEmail;
	@FXML private ToggleGroup tipousuario;
	@FXML private Label lblStatus;

	Load lv = new Load();

	@FXML
	public void onBtSalvarAction() {
		lblStatus.setText("");

		if (!buscaBD()) {
			if (Validate.validaUsuario(txtNome, txtUsuario, txtSenha, txtConfsenha, txtEmail)) {
				salvarDados();
			} else {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Insira todos os dados!");
			}
		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Usuario j? cadastrado no sistema!");
		}
	}

	private void salvarDados() {
		RadioButton radio = (RadioButton) tipousuario.getSelectedToggle();
		String senhaEnc = Criptografar.cripografar(txtSenha.getText(), "SHA1");

		String CPFUsuario = txtUsuario.getText(), nome = txtNome.getText(), senha = senhaEnc,
				email = txtEmail.getText(), fNivel = radio.getText();

		if (confereSenha(txtSenha, txtConfsenha)) {
			Usuario u = new Usuario(nome, CPFUsuario, senha, email, fNivel);
			UsuariaDao dao = new UsuariaDao();
			if (dao.add(u)) {
				limparCampos();
				lblStatus.setTextFill(Color.GREEN);
				lblStatus.setText("Usu?rio Cadastrado!");
			} else {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Erro ao cadastrar usuario...");
			}

		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Insira senhas iguais");
		}

	}

	private boolean confereSenha(TextField Senha, TextField ConfSenha) {
		if (txtSenha.getText().equals(txtConfsenha.getText())) {
			return true;
		} else {
			return false;
		}
	}

	private void limparCampos() {
		txtNome.clear();
		txtUsuario.clear();
		txtSenha.clear();
		txtConfsenha.clear();
		txtEmail.clear();
	}

	private boolean buscaBD() {
		UsuariaDao dao = new UsuariaDao();
		if (dao.buscaUsuario(txtUsuario.getText()) != null) {
			return true;
		}
		return false;
	}

	@FXML
	public void onBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}

	@FXML
	private void txtUsuarioKeyReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtUsuario);
		tff.formatter();
	}

}
