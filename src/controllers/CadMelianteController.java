package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.utils.Load;
import model.utils.TextFieldFormatter;

public class CadMelianteController {

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtApelido;
	@FXML
	private TextField txtCPF;
	@FXML
	private TextField txtCaracFisicas;
	@FXML
	private TextField txtCidade;
	@FXML
	private TextField txtUF;
	@FXML
	private TextField txtBairro;
	@FXML
	private TextField txtRua;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtDelitos;
	@FXML
	private TextField txtFaccao;
	@FXML
	private Button btVoltar;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btSair;
	@FXML
	private TextField txtTelefone;
	
	Load lv = new Load();
	
	@FXML
	public void onBtSalvarAcction() {
		
		String nome = txtNome.getText();
		String apelido = txtApelido.getText();
		String cpf = txtCPF.getText();
		String caracFisicas = txtCaracFisicas.getText();
		String telefone = txtTelefone.getText();
		String cidade = txtCidade.getText();
		String uf = txtUF.getText();
		String bairro = txtBairro.getText();
		String numero = txtNumero.getText();
		String delitos = txtDelitos.getText();
		String faccao = txtFaccao.getText();
	}
		
	@FXML
	public void onBtVoltarAcction() {
		lv.loadview("/views/Administrador.fxml");
	}
	@FXML
	public void onBtSairAcction() {
		lv.loadview("/views/Login.fxml");		
	}
	
	@FXML 
	private void txtCPFKeyReleased(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCPF);
		tff.formatter();
	}
	@FXML 
	private void txtTelefoneKeyReleased(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##) #####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtTelefone);
		tff.formatter();
	}

	@FXML 
	private void txtNumeroKeyReleased(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#######");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtNumero);
		tff.formatter();
		
	}
	


}
