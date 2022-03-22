package controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
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
			if (!confereSenha(txtSenha, txtConfsenha)) {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Insira senhas iguais");
			} else {
				salvarDados();
			}
		}else {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Insira todos os dados!");
		}
	}

	private String salvarDados() {

		Connection conn = DB.getConnection();
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(
					"INSERT INTO usuario ( CPFUsuario, usuario, senha, email, fnivel) VALUES (?,?,?,?,?)");
			RadioButton radio = (RadioButton) tipousuario.getSelectedToggle();
			String senhaEnc = Criptografar.cripografar(txtSenha.getText(), "SHA1");
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtNome.getText());
			pst.setString(3, senhaEnc);
			pst.setString(4, txtEmail.getText());
			pst.setString(5, radio.getText());
			pst.executeUpdate();

			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("Cadastro realizado com sucesso!");
			limparCampos();

			return "Success";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(ex.getMessage());
			return "Exception";
		} catch (NoSuchAlgorithmException e) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(e.getMessage());
			return "Exception";
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
