package controllers;

<<<<<<< Updated upstream
=======
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;
>>>>>>> Stashed changes
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.util.Callback;
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
	public void onBtSalvarAcction() {
		if(confereSenha(txtSenha, txtConfsenha)) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Insira senhas iguais");
		}else {
			salvarDados();
		}
	}

	private String salvarDados() {

		Connection conn = DB.getConnection();
		PreparedStatement pst = null;
		
<<<<<<< Updated upstream
		String nome = txtNome.getText();
		String usuario = txtUsuario.getText();
		String senha = txtSenha.getText();
		String cofSenha = txtConfsenha.getText();
		String email = txtEmail.getText();
=======
		try {
			pst = conn.prepareStatement("INSERT INTO usuarios ( nome, cpf, senha, email, nivel) VALUES (?,?,?,?,?)");
			
				String senhaEnc = Criptografar.cripografar(txtSenha.getText(),"SHA1");
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtUsuario.getText());
				pst.setString(3, senhaEnc);
				pst.setString(4, txtEmail.getText());
				pst.setInt(5, nivelDeAcesso(tipousuario));
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
>>>>>>> Stashed changes
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
