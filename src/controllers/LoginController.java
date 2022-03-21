package controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.utils.Load;
import model.utils.TextFieldFormatter;

public class LoginController {

	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtSenha;
	@FXML
	private Button btEntrar;
	@FXML
	private Label lblErrors;
	@FXML
	private Label lblStatus;

	Load lv = new Load();

	Connection conn = DB.getConnection();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@FXML
	public void onBtEntrarAcction() throws NoSuchAlgorithmException {

		logIn();

	}

	private String logIn() {
		String status = "Success";
		String email = txtUsuario.getText();
		String password = txtSenha.getText();
		if (email.isEmpty() || password.isEmpty()) {
			setLblError(Color.TOMATO, "Insira os dados");
			status = "Error";
		} else {
			// query
			String sql = "SELECT * FROM usuario Where CPFusuario = ? and senha = ?";
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					setLblError(Color.TOMATO, "Erro de Usuario/Senha");
					status = "Error";
				} else {
					setLblError(Color.GREEN, "Login realizado com sucesso");
					if (resultSet.getString("fnivel") == "Usuario") {
						lv.loadview("/views/Busca.fxml");
					} else {
						lv.loadview("/views/Administrador.fxml");
					}

				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}

		return status;
	}

	@FXML
	private void txtUsuarioKeyReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtUsuario);
		tff.formatter();
	}
	// @FXML
	// void

	@FXML
	public void onLabelRecSenhaMouseClicked() {
		lv.loadview("/views/RecSenha.fxml");
	}

	private void setLblError(Color color, String text) {
		lblErrors.setTextFill(color);
		lblErrors.setText(text);
		System.out.println(text);
	}

}
