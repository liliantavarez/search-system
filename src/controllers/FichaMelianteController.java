package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.utils.Load;

public class FichaMelianteController {
	
	@FXML
	private Label lblNome;
	@FXML
	private Label lblApelido;
	@FXML
	private Label lblCPF;
	@FXML
	private Label lblCaracFisicas;
	@FXML
	private Label lblTelefone;
	@FXML
	private Label lblCidade;
	@FXML
	private Label lblUF;
	@FXML
	private Label lblBairro;
	@FXML
	private Label lblRua;
	@FXML
	private Label lblNumero;
	@FXML
	private Label lblDelitos;
	@FXML
	private Label lblFaccao;
	@FXML
	private Button btGerarPDF;
	@FXML
	private Button btEditar;
	@FXML
	private Button btExcluir;
	@FXML
	private Button btImprimir;
	@FXML
	private Button btVoltar;
	@FXML
	private Button btSair;
	
	Load lv = new Load();
	
	@FXML
	private void onBtGerarPDFAction() {
		
	}
	
	@FXML
	private void onBtEditarAction() {
		
	}
	
	@FXML
	private void onBtExcluirAction() {
		
	}
	
	@FXML
	private void onBtImprimirAction() {
		
	}
	
	@FXML
	private void onBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");
	}
	
	@FXML
	private void onBtSairAction() {
		lv.loadview("/views/Login.fxml");		
	}

	

}
