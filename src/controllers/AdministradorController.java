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
	public void onBtCadMelianteAction() {
		lv.loadview("/views/CadMeliante.fxml");
	}
	
	@FXML
	public void onBtCadUsuarioAction() {
		lv.loadview("/views/CadUsuario.fxml");		
	}
	
	@FXML
	public void onBtBuscMelianteAction() {
		lv.loadview("/views/Busca.fxml");
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
