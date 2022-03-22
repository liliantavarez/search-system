package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.utils.Load;
import validation.Validate;

public class RecSenhaController {

	@FXML
	private TextField txtEmail;
	
	Load lv = new Load();

	@FXML
	public void onBtConfirmarAction() {
		String email = txtEmail.getText();
		Validate.valida(txtEmail);

	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");		
	}

}
