package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.EnderecoDao;
import DAO.MelianteDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import model.entites.Usuario;
import model.utils.Impressao;
import model.utils.Load;
import model.utils.TextFieldFormatter;
import validation.Validate;

public class FichaMelianteController implements Initializable {

	@FXML
	private Button btSalvar, btEditar, btExcluir, btGerarPDF, btImprimir, btSair, btSelecionarFoto, btVoltar;

	@FXML
	private ImageView imageView;

	@FXML Label lblStatus;

	@FXML
	private TextField txtApelido;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCaracFisicas;

	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtDelitos;

	@FXML
	private TextField txtFaccao;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtUF;

	private FileChooser fileChooser;
	private File file;
	private Image image;
	private FileInputStream imagem;

	Load lv = new Load();

	String caminhoFoto = null;

	Endereco e = BuscaController.e;
	Meliante m = BuscaController.m;

	public void exibir() {
		
		MelianteDao dao = new MelianteDao();

		txtNome.setText(m.getNome());
		txtCPF.setText(m.getCPFMeliante());
		txtApelido.setText(m.getApelido());
		txtCaracFisicas.setText(m.getCaracteristicasFisicas());
		txtDelitos.setText(m.getDelitos());
		txtFaccao.setText(m.getFaccao());
		imageView.setImage(dao.visualizar(m));
		txtTelefone.setText(m.getTelefone());

		txtCidade.setText(e.getCidade());
		txtUF.setText(e.getEstado());
		txtBairro.setText(e.getBairro());
		txtRua.setText(e.getRua());
		txtNumero.setText(e.getNumero());
	}

	public void limpar() {
		txtNome.setText("");
		txtCPF.setText("");
		txtApelido.setText("");
		txtCaracFisicas.setText("");
		txtDelitos.setText("");
		txtFaccao.setText("");
		imageView.setImage(new Image("/views/SeekPng.com_personas-png_1305038.png"));
		txtTelefone.setText("");

		txtCidade.setText("");
		txtUF.setText("");
		txtBairro.setText("");
		txtRua.setText("");
		txtNumero.setText("");
	}

	private void atualiza() {
		Validate.validarMeliante(txtNome, txtApelido, txtCPF, txtCaracFisicas, txtTelefone, txtDelitos, txtFaccao);
		Validate.validaEndereco(txtCidade, txtUF, txtBairro, txtRua, txtNumero);

		String nome = txtNome.getText(), CPFMeliante = txtCPF.getText(), apelido = txtApelido.getText(),
				caracteristicasFisicas = txtCaracFisicas.getText(), delitos = txtDelitos.getText(),
				faccao = txtFaccao.getText(), telefone = txtTelefone.getText(), cidade = txtCidade.getText(),
				bairro = txtBairro.getText(), rua = txtRua.getText(), estado = txtUF.getText(),
				numero = txtNumero.getText();

		try {

			imagem = new FileInputStream(file);

			Meliante m = new Meliante(nome, apelido, CPFMeliante, caracteristicasFisicas, telefone, imagem, delitos,
					faccao);
			Endereco en = new Endereco(cidade, estado, bairro, rua, numero);

			MelianteDao dao = new MelianteDao();
			EnderecoDao daoEn = new EnderecoDao();

			if (dao.add(m) && daoEn.add(en)) {
				lblStatus.setTextFill(Color.GREEN);
				lblStatus.setText("Cadastro atualizado com sucesso!");
				limpar();

			} else {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Erro da atualização do cadastro...");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(ex.getMessage());
		}
	}

	@FXML
	void onBtEditarAction() {
		btSelecionarFoto.setVisible(true);
		btSalvar.setVisible(true);
		ativaTxt();
	}

	@FXML
	void onBtVoltarAction() {
		lv.loadview("/views/Busca.fxml");
	}

	@FXML
	void onBtExcluirAction() {
		MelianteDao dao = new MelianteDao();
		if (dao.delete(m)) {
			limpar();
			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("Meliante removido do sistema!");
		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Erro ao excluir meliante!");
		}
	}

	@FXML
	public void onBtSalvarAction() {
		btSalvar.setVisible(false);
		atualiza();
		desativaTxt();
	}


	@FXML
	void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}

	@FXML
	void onBtSelecionarFotoAction() {

		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.png"));

		file = fileChooser.showOpenDialog(null);

		if (file != null) {
			caminhoFoto = file.getAbsolutePath();

			image = new Image(file.toURI().toString());

			imageView.setImage(image);
			try {
				imagem = new FileInputStream(file);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			imageView.setImage(new Image("/views/SeekPng.com_personas-png_1305038.png"));
		}
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

	public void ativaTxt() {
		txtNome.setEditable(true);
		txtCPF.setEditable(true);
		txtApelido.setEditable(true);
		txtCaracFisicas.setEditable(true);
		txtDelitos.setEditable(true);
		txtFaccao.setEditable(true);

		txtTelefone.setEditable(true);

		txtCidade.setEditable(true);
		txtUF.setEditable(true);
		txtBairro.setEditable(true);
		txtRua.setEditable(true);
		txtNumero.setEditable(true);
	}

	public void desativaTxt() {
		txtNome.setEditable(false);
		txtCPF.setEditable(false);
		txtApelido.setEditable(false);
		txtCaracFisicas.setEditable(false);
		txtDelitos.setEditable(false);
		txtFaccao.setEditable(false);

		txtTelefone.setEditable(false);

		txtCidade.setEditable(false);
		txtUF.setEditable(false);
		txtBairro.setEditable(false);
		txtRua.setEditable(false);
		txtNumero.setEditable(false);
	}

	public void mostraBtsAdm() {
		btEditar.setVisible(true);
		btExcluir.setVisible(true);
	}

/*	
	@FXML
	void onBtGerarPDFAction() {
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(""));
			doc.open();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@FXML
	void onBtImprimirAction() {
		Impressao imp = new Impressao();
		imp.detectaImpressoras();
		imp.imprime("ssssssssssss");

	}
*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		exibir();
	}

}
