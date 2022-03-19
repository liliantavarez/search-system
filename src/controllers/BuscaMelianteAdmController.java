package controllers;

import Validation.Validate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.utils.Load;

public class BuscaMelianteAdmController {
	
	@FXML
	private TextField txtMelianteBusca;
	@FXML
	private Button btConfirmar;
	@FXML
	private Button btSair;
	@FXML
	private Button btVoltar;
	
	Load lv = new Load();
	
	@FXML
	public void onBtBuscarAcction() {
		String melianteBusca = txtMelianteBusca.getText();
		Validate.valida(txtMelianteBusca);
	}
	
	@FXML
	public void onBtSairAcction() {
		lv.loadview("/views/Login.fxml");		
	}
	
	@FXML
	public void ontBtVoltarAcction() {
		lv.loadview("/views/Administrador.fxml");				
	}

}