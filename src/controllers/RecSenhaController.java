package controllers;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Load;

public class RecSenhaController {
	
	@FXML private TextField txtEmail;
	@FXML private Label lblStatus;
	Usuario u = LoginController.getU();
	
	Load lv = new Load();
	
	@FXML
	public void onBtConfirmarAction() {
		lblStatus.setText("");
		recSenha();
	}
	
	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");		
	}
	
	public void recSenha() {
		String email = txtEmail.getText();
		UsuariaDao dao = new UsuariaDao();
		if(dao.buscaUsuario(email) != null) {
			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("E-mail de recuperação de senha enviado para: "+email);
		}else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Usuario não cadastrado no sistema!");
		}
	}
	

}
