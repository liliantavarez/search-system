package controllers;

import javafx.fxml.FXML;
import model.utils.Load;

public class AdministradorController {
		
	Load lv = new Load();
	
	@FXML
	public void onBtCadMelianteAction() {
		lv.loadview("/views/CadMeliante.fxml");
	}
	
	@FXML
	public void onBtCadUsuarioAction() {
		lv.loadview("/views/CadUsuario.fxml");		
	}
	
	@FXML
	public void onBtBuscMelianteAction() {
		lv.loadview("/views/BuscaAdm.fxml");
	}
	
	@FXML
	public void onBtBuscUsuarioAction() {
		lv.loadview("/views/BuscaUsuario.fxml");		
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");				
	}
	
}
