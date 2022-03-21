package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.utils.Load;
import validation.Validate;

public class BuscaController {
	
	@FXML
	private TextField txtMelianteBusca;
	@FXML
	private Button btConfirmar;
	@FXML
	private Button btSair;
	
	Load lv = new Load();
	
	@FXML
	public void onBtConfirmarAcction() {
		String melianteBusca = txtMelianteBusca.getText();
<<<<<<< Updated upstream
=======
		Validate.valida(txtMelianteBusca);
>>>>>>> Stashed changes
		
	}
	
	@FXML
	public void onBtSairAcction() {
		lv.loadview("/views/Login.fxml");		
	}
	

}
