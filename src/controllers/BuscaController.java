package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.utils.Load;
import validation.Validate;

public class BuscaController {
	
	@FXML
	private TextField txtMelianteBusca;
	Load lv = new Load();
	
	@FXML
	public void onBtConfirmarAction() {
		String melianteBusca = txtMelianteBusca.getText();
		Validate.valida(txtMelianteBusca);
		
	}
	
	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}
	

}
