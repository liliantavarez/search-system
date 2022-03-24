package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.utils.Load;

public class RecSenhaController {
	
	@FXML 
	private TextField txtEmail;
	
	Load lv = new Load();
	
	@FXML
	public void onBtConfirmarAction() {

	}
	
	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");		
	}
	

}
