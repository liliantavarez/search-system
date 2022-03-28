package controllers;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DAO.UsuariaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.entites.Usuario;
import model.utils.Load;

public class RecSenhaController {

	@FXML
	private TextField txtEmail;
	@FXML
	private Label lblStatus;
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
		String emailBusca = txtEmail.getText();
		UsuariaDao dao = new UsuariaDao();
		Usuario user = dao.buscaUsuario(emailBusca);
		if (user != null) {

			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("Com a senha enviado para: " + emailBusca);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("kelvenunes123@gmail.com", "ljoeealjytmpgxus");
				}
			});
			session.setDebug(true);
			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("kelvenunes123@gmail.com"));

				Address[] toUser = InternetAddress.parse(user.getEmail());

				message.setRecipients(Message.RecipientType.TO, toUser);
				message.setSubject("Reculperação de Senha");
				message.setText(user.getSenha());

				Transport.send(message);

				System.out.println("Feito!!!");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Usuario não cadastrado no sistema!");

		}

	}

}
