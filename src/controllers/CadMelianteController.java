package controllers;

import validation.Validate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
	private TextField txtTelefone;
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
	private Label lblStatus;

	Load lv = new Load();

	@FXML
	public void onBtSalvarAction() {

		try {
			salvarDados();
		} catch (DbException e) {
			System.out.println(e.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(e.getMessage());
		}
	}

	private String salvarDados() {

		Connection conn = DB.getConnection();
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(
					"INSERT INTO meliantes ( nome, apelido, cpf, caracfisicas, cidade, uf, bairro, rua, numero, delitos,faccao) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, txtNome.getText());
			pst.setString(2, txtApelido.getText());
			pst.setString(3, txtCPF.getText());
			pst.setString(4, txtCaracFisicas.getText());
			pst.setString(5, txtCidade.getText());
			pst.setString(6, txtUF.getText());
			pst.setString(7, txtBairro.getText());
			pst.setString(8, txtRua.getText());
			pst.setString(9, txtNumero.getText());
			pst.setString(10, txtDelitos.getText());
			pst.setString(11, txtFaccao.getText());

			pst.executeUpdate();

			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("Cadastro realizado com sucesso!");
			limparCampos();

			return "Success";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(ex.getMessage());
			return "Exception";
		}
	}

	private void limparCampos() {
		txtNome.clear();
		txtApelido.clear();
		txtCPF.clear();
		txtCaracFisicas.clear();
		txtCidade.clear();
		txtUF.clear();
		txtBairro.clear();
		txtRua.clear();
		txtNumero.clear();
		txtDelitos.clear();
		txtFaccao.clear();
	}

	@FXML
	public void onBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}

	@FXML
	private void txtCPFKeyReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCPF);
		tff.formatter();
	}

	@FXML
	private void txtTelefoneKeyReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtTelefone);
		tff.formatter();
	}

	@FXML
	private void txtNumeroKeyReleased() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#######");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtNumero);
		tff.formatter();

	}

}
