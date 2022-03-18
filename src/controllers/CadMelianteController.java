package controllers;

import Validation.Validate;
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
		if(Validate.valida(txtNome) != true) {
			nome = "Informação não inserida";
		}
		String apelido = txtApelido.getText();
		if(Validate.valida(txtApelido) != true) {
			apelido = "Informação não inserida";
		}
		String cpf = txtCPF.getText();
		if(Validate.valida(txtCPF) != true) {
			cpf = "Informação não inserida";
		}
		String caracFisicas = txtCaracFisicas.getText();
		if(Validate.valida(txtCaracFisicas) != true) {
			caracFisicas = "Informação não inserida";
		}
		String telefone = txtTelefone.getText();
		if(Validate.valida(txtTelefone) != true) {
			telefone = "Informação não inserida";
		}
		String cidade = txtCidade.getText();
		if(Validate.valida(txtCidade) != true) {
			cidade = "Informação não inserida";
		}
		String uf = txtUF.getText();
		if(Validate.valida(txtUF) != true) {
			uf = "Informação não inserida";
		}
		String bairro = txtBairro.getText();
		if(Validate.valida(txtBairro) != true) {
			bairro = "Informação não inserida";
		}
		String numero = txtNumero.getText();
		if(Validate.valida(txtNumero) != true) {
			numero = "Informação não inserida";
		}
		String delitos = txtDelitos.getText();
		if(Validate.valida(txtDelitos) != true) {
			delitos = "Informação não inserida";
		}
		String faccao = txtFaccao.getText();
		if(Validate.valida(txtFaccao) != true) {
			faccao = "Informação não inserida";
		}
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
