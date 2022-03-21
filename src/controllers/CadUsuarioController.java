package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import model.utils.Load;
import model.utils.TextFieldFormatter;

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
	public void onBtSalvarAcction() {

		try {
			if (salvarDados() == "Success") {
				salvarDados();
			}
		} catch (DbException e) {
			System.out.println(e.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(e.getMessage());
		}
	}

	private String salvarDados() {

		Connection conn = DB.getConnection();
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(
					"INSERT INTO usuario ( CPFUsuario, usuario, senha, email, fnivel) VALUES (?,?,?,?,?)");
			RadioButton radio = (RadioButton) tipousuario.getSelectedToggle();
			// String senhaEnc = Criptografar.cripografar(txtSenha.getText(), "SHA1");
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, txtNome.getText());
			pst.setString(3, txtSenha.getText());
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
		}
	}

	private boolean confereSenha(TextField Senha, TextField ConfSenha) {
		if (txtSenha.getText() == txtConfsenha.getText()) {
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

	private int nivelDeAcesso(ToggleGroup tipousuario) {
		RadioButton radio = (RadioButton) tipousuario.getSelectedToggle();
		if (radio.getText().equals("Usuario")) {
			return 1;
		}
		return 2;
	}

	@FXML
	public void onBtVoltarAcction() {
		lv.loadview("/views/Administrador.fxml");
	}

	@FXML
	public void onBtSairAcction() {
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
