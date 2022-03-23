package controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.utils.Criptografar;
import model.utils.Load;
import model.utils.TextFieldFormatter;

public class LoginController implements Initializable {

	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtSenha;
	@FXML
	private Button btEntrar;
	@FXML
	private Label lblErrors;
	Load lv = new Load();
	
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	
    @FXML
	public void onBtEntrarAcction() throws NoSuchAlgorithmException {
		
		if (logIn().equals("Success")) {
            try {
            	if(resultSet.getString("fnivel").equals("Administrador")) {
            		lv.loadview("/views/Administrador.fxml");
            	}else {
            		lv.loadview("/views/Busca.fxml");            		
            	}

            }catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
		
	}

	private String logIn() {
		String status = "Success";
		String usuario = txtUsuario.getText();
		String senha = txtSenha.getText();
		if (usuario.isEmpty() || senha.isEmpty()) {
			setLblError(Color.TOMATO, "Credenciais vazias");
			status = "Error";
		} else {
			// query
			String sql = "SELECT * FROM usuario Where CPFUsuario = ? and senha = ?";
			try {
				String senhaEnc = Criptografar.cripografar(senha, "SHA1");
				
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, usuario);
				preparedStatement.setString(2, senhaEnc);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					setLblError(Color.TOMATO, "Entre com Usuario/Senha corretos!");
					status = "Error";
				} else {
					setLblError(Color.GREEN, "Login Successful..Redirecting..");
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}

		return status;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		if (con == null) {
			lblErrors.setTextFill(Color.TOMATO);
			lblErrors.setText("X Erro ao conecatar servidor X");
		} else {
			lblErrors.setTextFill(Color.GREEN);
			lblErrors.setText("Servidor conectado!");
		}
	}

	private void setLblError(Color color, String text) {
		lblErrors.setTextFill(color);
		lblErrors.setText(text);
	}
    public LoginController() {
    	con = DB.getConnection();
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
