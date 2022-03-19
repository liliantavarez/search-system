package controllers;

import Validation.Validate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.utils.Load;

public class BuscaUsuarioController {
	
	@FXML
	private TextField txtEmailOuUsu;
	@FXML 
	private Button btBuscar;
	@FXML
	private Label lbUsuario;
	@FXML
	private Label lbEmail;
	@FXML
	private Button BtVoltar;
	@FXML
	private Button btSair;
	
	Load lv = new Load();
	
	
	@FXML
	public void onBtBuscarAcction() {
		String email = txtEmailOuUsu.getText();
		Validate.valida(txtEmailOuUsu);
		
	}
		
	@FXML
	public void onBtVoltarAcction() {
		lv.loadview("/views/Administrador.fxml");
	}
	@FXML
	public void onBtSairAcction() {
		lv.loadview("/views/Login.fxml");		
	}
	
	

}
