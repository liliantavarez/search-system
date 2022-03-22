package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.utils.Load;
import validation.Validate;

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
	public void onBtConfirmarAction() {
		String melianteBusca = txtMelianteBusca.getText();
		
	}
	
	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");		
	}
	
	@FXML
	public void ontBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");				
	}

}
