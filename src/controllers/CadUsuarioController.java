package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
	
	Load lv = new Load();

	
	@FXML
	public void onBtSalvarAcction() {
		
		String nome = txtNome.getText();
		String usuario = txtUsuario.getText();
		String senha = txtSenha.getText();
		String cofSenha = txtConfsenha.getText();
		String email = txtEmail.getText();
		RadioButton radio = (RadioButton) tipousuario.getSelectedToggle();
	
		
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
	private void txtUsuarioKeyReleased () {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtUsuario);
		tff.formatter();
	}

}
