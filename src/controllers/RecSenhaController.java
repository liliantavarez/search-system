package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.utils.Load;

public class RecSenhaController {
	
	@FXML 
	private TextField txtEmail;
	@FXML
	private Button btConfirmar;
	@FXML
	private Button btVoltar;
	
	Load lv = new Load();
	
	@FXML
	public void onBtConfirmarAcction() {
		String email = txtEmail.getText();
		
	}
	
	@FXML
	public void onBtSairAcction() {
		lv.loadview("/views/Login.fxml");		
	}
	

}
