package controllers;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtSenha;
	@FXML
	private TextField txtConfsenha;
	@FXML
	private TextField txtEmail;
	@FXML
	private ToggleGroup tipousuario;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btVoltar;
	@FXML
	private Button btSair;
	@FXML
	private Label lblStatus;

	Load lv = new Load();

	@FXML
	public void onBtSalvarAction() {
		if (Validate.validaUsuario(txtNome, txtUsuario, txtSenha, txtConfsenha, txtEmail)) {
			salvarDados();
		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Insira todos os dados!");
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
				lblStatus.setText("Usuário Cadastrado!");
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
