package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Load;

public class FichaUsuarioController implements Initializable {

	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtNome;
	@FXML
	private Button btSalvar;
	@FXML
	private Label lbStatus;
	Load lv = new Load();

	Usuario u = BuscaUsuarioController.enviar();

	@FXML
	public void onBtEditarAction() {
		btSalvar.setVisible(true);
		ativaTxt();
	}

	@FXML
	public void onBtSalvarAction() {
		atualiza();
	}
	
	public void atualiza() {
		UsuariaDao dao = new UsuariaDao();
		int id = u.getIdUsuario();
		String CPFUsuario = txtUsuario.getText(), nome = txtNome.getText(), email = txtEmail.getText();
		Usuario upUsuario = new Usuario(id,CPFUsuario,nome,email);
		if(dao.update(upUsuario)) {
			lbStatus.setTextFill(Color.GREEN);
			lbStatus.setText("Usuário atualizado com sucesso!");
		}else {
			lbStatus.setTextFill(Color.TOMATO);
			lbStatus.setText("Erro na atualização de usuário!");
		}
	
	}
	public void ativaTxt() {
		txtNome.setEditable(true);
		txtUsuario.setEditable(true);
		txtEmail.setEditable(false);
	}
	
	public void desativaTxt() {
		txtNome.setEditable(false);		
	}

	@FXML
	public void onBtExcluirAction() {
		UsuariaDao dao = new UsuariaDao();
		if (dao.delete(u)) {
			limpar();
			lbStatus.setTextFill(Color.GREEN);
			lbStatus.setText("Usuário removido do sistema!");
		} else {
			lbStatus.setTextFill(Color.TOMATO);
			lbStatus.setText("Erro ao excluir usuário!");
		}
	}

	public void limpar() {
		txtNome.setText("");
		txtUsuario.setText("");
		txtEmail.setText("");
	}

	public void exibir() {
		txtNome.setText(u.getNome());
		txtUsuario.setText(u.getCPFUsuario());
		txtEmail.setText(u.getEmail());
	}

	@FXML
	public void onBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		exibir();
	}

}
