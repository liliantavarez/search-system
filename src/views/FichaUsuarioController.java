package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.utils.Load;

public class FichaUsuarioController {
	
	@FXML
	private Label lbNome;
	@FXML
	private Label lbUsuario;
	@FXML
	private Label lbEmail;
	@FXML
	private Button btEditar;
	@FXML
	private Button btExcluir;
	@FXML
	private Button btVoltar;
	@FXML
	private Button btSair;
	
	Load lv = new Load();
	
	@FXML 
	public void onBtEditarAcction(){
		
	}
	
	@FXML 
	public void onBtSalvarAcction(){
		
	}
	
	@FXML 
	public void onBtVoltarAcction(){
		lv.loadview("/views/Administrador.fxml");					
	}
	
	@FXML 
	public void onBtSairAcction(){
		lv.loadview("/views/Login.fxml");					

	}

}
