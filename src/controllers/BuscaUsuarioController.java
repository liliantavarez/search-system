package controllers;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Load;

public class BuscaUsuarioController {

	@FXML
	private TextField txtEmailOuUsu;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lbResultado;
	@FXML
	private Label lblStatus;
	@FXML
	private Node HBoxResultado;

	public static Usuario user;
	Load lv = new Load();

	@FXML
	public void onBtBuscarAction() {
		try {
			if (buscarBD()) {
				exibir();
			}

		} catch (RuntimeException e) {
			// e.printStackTrace();
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Usuário não cadastrado!");
		}

	}

	public boolean buscarBD() {
	
		String busca = txtEmailOuUsu.getText();
		if (busca.isEmpty()) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Informe usuario ou e-mail para realizar a busca!");
			return false;
		} else {
			UsuariaDao dao = new UsuariaDao();
			user = dao.buscaUsuario(busca);
			if(user.getId()>0) {
				return true;
			}
			return false;
		}
		
	}

	private void exibir() {
		lblStatus.setText("");
		HBoxResultado.setStyle("visibility: true;");
		lbResultado.setStyle("visibility: true;");
		lblUsuario.setText(user.getCPFUsuario());
		lblEmail.setText(user.getEmail());
		
	}

	@FXML
	private void OnHBoxResultadoMouseClicked() {
		lv.loadview("/views/FichaUsuario.fxml");
	}

	@FXML
	public void onBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}

}
