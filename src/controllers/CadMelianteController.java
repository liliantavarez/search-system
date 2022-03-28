package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import DAO.EnderecoDao;
import DAO.MelianteDao;
import db.DbException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.entites.Endereco;
import model.entites.Meliante;
import model.utils.Load;
import model.utils.TextFieldFormatter;
import validation.Validate;

public class CadMelianteController {

	@FXML private TextField txtNome, txtApelido, txtCPF, txtCaracFisicas, txtTelefone, txtDelitos;
	@FXML private TextField txtFaccao, txtCidade, txtUF, txtBairro, txtRua, txtNumero;
	@FXML private Label lblStatus;
	@FXML private ImageView imgviewFoto;

	private FileChooser fileChooser;
	private File file;
	private Image image;
	private Image imagePadrao;
	private FileInputStream imagem;
	String caminhoFoto = null;

	Load lv = new Load();

	@FXML
	public void onBtSalvarAction() {
		lblStatus.setText("");
		try {
			if (txtCPF.getText().isEmpty()) {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Erro da realização do cadastro... Informe o CPF do meliante!");
			} else {
				salvarDados();
			}
		} catch (DbException e) {
			System.out.println(e.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(e.getMessage());
		}

	}

	private void salvarDados() {

		if (file == null) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Selecione uma foto");
		} else {
			try {

				String nome = txtNome.getText(), CPFMeliante = txtCPF.getText(), apelido = txtApelido.getText(),
						caracteristicasFisicas = txtCaracFisicas.getText(), delitos = txtDelitos.getText(),
						faccao = txtFaccao.getText(), telefone = txtTelefone.getText(), cidade = txtCidade.getText(),
						bairro = txtBairro.getText(), rua = txtRua.getText(), estado = txtUF.getText(),
						numero = txtNumero.getText();

				Validate.validarMeliante(nome, apelido, CPFMeliante, caracteristicasFisicas, telefone, delitos, faccao);
				Validate.validaEndereco(cidade, estado, bairro, rua, numero);
				
				imagem = new FileInputStream(file);

				Meliante m = new Meliante(nome, apelido, CPFMeliante, caracteristicasFisicas, telefone, imagem, delitos,
						faccao);
				Endereco en = new Endereco(m.getId(), cidade, estado, bairro, rua, numero);

				MelianteDao dao = new MelianteDao();
				EnderecoDao daoEn = new EnderecoDao();

				if (dao.add(m) && daoEn.add(en)) {
					lblStatus.setTextFill(Color.GREEN);
					lblStatus.setText("Cadastro realizado com sucesso!");
					limparCampos();

				} else {
					lblStatus.setTextFill(Color.TOMATO);
					lblStatus.setText("Erro da realização do cadastro...");
				}

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText(ex.getMessage());
			}
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
		txtTelefone.clear();
		txtDelitos.clear();
		txtFaccao.clear();
		imgviewFoto.setImage(imagePadrao);
	}

	@FXML
	private void onBtSelecionarFotoAction() {
		
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.png"));

		file = fileChooser.showOpenDialog(null);
		if (file != null) {
			System.out.println(file);
			
			caminhoFoto = file.getAbsolutePath();
			image = new Image(file.toURI().toString());
			imgviewFoto.setImage(image);

			try {
				imagem = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
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