package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.UsuariaDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Load;

public class BuscaUsuarioController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button BtVoltar;

	@FXML
	private HBox HBoxResultado;

	@FXML
	private Button btBuscar;

	@FXML
	private Button btSair;

	@FXML
	private Label lbResultado;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblStatus;

	@FXML
	private Label lblUsuario;

	@FXML
	private TextField txtEmailOuUsu;

	public static Usuario user;
	Load lv = new Load();

	@FXML
	void OnHBoxResultadoMouseClicked(MouseEvent event) {
		lv.loadview("/views/FichaUsuario.fxml");
	}

	@FXML
	void onBtBuscarAction(ActionEvent event) {

		lblStatus.setText("");

		try {
			if (buscarBD()) {
				exibir();
			}

		} catch (RuntimeException e) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Usuário não cadastrado!");
		}
	}

	@FXML
	void onBtSairAction(ActionEvent event) {
		lv.loadview("/views/Login.fxml");
	}

	@FXML
	void onBtVoltarAction(ActionEvent event) {
		lv.loadview("/views/Administrador.fxml");
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
			if (user.getId() > 0) {
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

}
