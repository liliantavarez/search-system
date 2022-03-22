package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.utils.Load;

public class FichaUsuarioController {
	
	@FXML
	private Label lbNome;
	@FXML
	private Label lbUsuario;
	@FXML
	private Label lbEmail;

	
	Load lv = new Load();
	
	@FXML 
	public void onBtEditarAction(){
		
	}
	
	@FXML 
	public void onBtExcluirAction(){
		
	}
	
	@FXML 
	public void onBtVoltarAction(){
		lv.loadview("/views/Administrador.fxml");					
	}
	
	@FXML 
	public void onBtSairAction(){
		lv.loadview("/views/Login.fxml");					

	}

}
