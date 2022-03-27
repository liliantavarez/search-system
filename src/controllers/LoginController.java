package controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Criptografar;
import model.utils.Load;
import model.utils.TextFieldFormatter;

public class LoginController {

	@FXML private TextField txtUsuario, txtSenha;
	@FXML private Label lblErrors;
	
	Load lv = new Load();
	private static Usuario u = new Usuario();
	
    public static Usuario getU() {
		return u;
	}

	public static void setU(Usuario u) {
		LoginController.u = u;
	}

	@FXML
	public void onBtEntrarAcction() {
		lblErrors.setText("");

    	if (!txtSenha.getText().isEmpty() && !txtUsuario.getText().isEmpty()) {
    		logIn();    		
    	}else {
			lblErrors.setTextFill(Color.TOMATO);
			lblErrors.setText("Credenciais vazias!");
    	}
	}

	private void logIn() {
		UsuariaDao dao = new UsuariaDao();
		List <Usuario> usuarios = dao.getList();
		String senhaEnc = Criptografar.cripografar(txtSenha.getText(), "SHA1");
		
		for (int x = 0; x < usuarios.size(); x++) {
			if(txtUsuario.getText().equals(usuarios.get(x).getCPFUsuario()) && senhaEnc.equals(usuarios.get(x).getSenha())) {
				u = usuarios.get(x);
				try {
					if(usuarios.get(x).getfNivel().equals("Administrador")) {
						lv.loadview("/views/Administrador.fxml");
					}else {
						lv.loadview("/views/Busca.fxml");
					}
				}catch(Exception e) {
					Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE,null,e);
				}
			}else {
				if(x == usuarios.size()-1) {
					lblErrors.setTextFill(Color.TOMATO);
					lblErrors.setText("Credenciais inválidas!");
				}
			}
		}
	}

	@FXML
	private void txtUsuarioKeyReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtUsuario);
		tff.formatter();
	}

	@FXML
	public void onLabelRecSenhaMouseClicked() {
		lv.loadview("/views/RecSenha.fxml");
	}

}
