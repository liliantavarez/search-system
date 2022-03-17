package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.utils.Load;

public class AdministradorController {
	
	@FXML
	private Button btCadMeliante;
	@FXML
	private Button btCadUsuario;
	@FXML
	private Button btBuscMeliante;
	@FXML
	private Button btBuscUsuario;
	@FXML
	private Button btSair;
	
	Load lv = new Load();
	
	@FXML
	public void onBtCadMelianteAcction() {
		lv.loadview("/views/CadMeliante.fxml");
	}
	
	@FXML
	public void onBtCadUsuarioAcction() {
		lv.loadview("/views/CadUsuario.fxml");		
	}
	
	@FXML
	public void onBtBuscMelianteAcction() {
		lv.loadview("/views/BuscaMelianteAdm.fxml");
	}
	
	@FXML
	public void onBtBuscUsuarioAcction() {
		lv.loadview("/views/BuscaUsuario.fxml");		
	}

	@FXML
	public void onBtSairAcction() {
		lv.loadview("/views/Login.fxml");				
	}

}
