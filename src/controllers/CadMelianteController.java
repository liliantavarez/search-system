package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import DAO.EnderecoDao;
import DAO.MelianteDao;
import db.DbException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	@FXML
	private ImageView imgviewFoto;

	private FileChooser fileChooser;
	private File file;
	private Image image;
	private Image imagePadrao;
	private FileInputStream fis;
	String caminhoFoto = null;

	Load lv = new Load();

	@FXML
	public void onBtSalvarAction() {

		if (!txtCPF.getText().isEmpty()) {
			try {
				salvarDados();
			} catch (DbException e) {
				System.out.println(e.getMessage());
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText(e.getMessage());
			}
		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Erro da realização do cadastro... Informe o CPF do meliante!");
		}
	}

	private void salvarDados() {

		String nome = txtNome.getText(), CPFMeliante = txtCPF.getText(), apelido = txtApelido.getText(),
				caracteristicasFisicas = txtCaracFisicas.getText(), delitos = txtDelitos.getText(),
				faccao = txtFaccao.getText(), telefone = txtTelefone.getText(), cidade = txtCidade.getText(),
				bairro = txtBairro.getText(), rua = txtRua.getText(), estado = txtUF.getText(),
				numero = txtNumero.getText();

		try {

			fis = new FileInputStream(file);

			Meliante m = new Meliante(nome, CPFMeliante, apelido, caracteristicasFisicas, delitos, faccao, fis, telefone);
			Endereco en = new Endereco(CPFMeliante, cidade, bairro, rua, estado, numero);

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
		imgviewFoto.setImage(imagePadrao);
	}

	@FXML
	private void onBtSelecionarFotoAction() {
		imagePadrao = imgviewFoto.getImage();

		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("imagens", "*.jpg", "*.png"));

		file = fileChooser.showOpenDialog(null);

		if (file != null) {
			caminhoFoto = file.getAbsolutePath();
			image = new Image(file.toURI().toString());
			System.out.println(image.getUrl());

			imgviewFoto.setImage(image);
			try {
				fis = new FileInputStream(file);

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
