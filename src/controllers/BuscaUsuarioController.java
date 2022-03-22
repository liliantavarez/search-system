package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.utils.Load;

public class BuscaUsuarioController {
	
	@FXML
	private TextField txtEmailOuUsu;
	@FXML 
	private Button btBuscar;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblEmail;
	@FXML
	private Button BtVoltar;
	@FXML
	private Button btSair;
	@FXML
	private Label lblStatus;
	Load lv = new Load();
	@FXML
	private Node HBoxResultado;
	
	@FXML
	public void onBtBuscarAction() {
		
		if(buscarBD()=="Success") {
			buscarBD();
		}

	}
	
	public String buscarBD() {
		String status = "Success";
		String busca = txtEmailOuUsu.getText();
		
		if (busca.isEmpty()) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Informe usuario ou e-mail para realizar a busca!");
			status = "Exception";
		
		} else {
			// query
			String sql = "SELECT * FROM usuario Where CPFUsuario = ? or email = ?";
			Connection conn = DB.getConnection();
			PreparedStatement pst = null;
			ResultSet resultSet = null;
			
			try {
				
				pst = conn.prepareStatement(sql);
				pst.setString(1, txtEmailOuUsu.getText());
				pst.setString(2, txtEmailOuUsu.getText());

				resultSet = pst.executeQuery();
				
				if (!resultSet.next()) {
					lblStatus.setTextFill(Color.TOMATO);
					lblStatus.setText("Usuario n�o cadastrado!");
					status = "Exception";
				} else {
					lblStatus.setText("");
					HBoxResultado.setStyle("visibility: true;");
					lblUsuario.setText(resultSet.getString("CPFUsuario"));
					lblEmail.setText(resultSet.getString("email"));
					status = "Success";
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}
		return status;
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
