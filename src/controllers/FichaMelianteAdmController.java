package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import DAO.EnderecoDao;
import DAO.MelianteDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.entites.Endereco;
import model.entites.Meliante;
import model.utils.Impressao;
import model.utils.Load;
import model.utils.TextFieldFormatter;

public class FichaMelianteAdmController{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btEditar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btGerarPDF;

	@FXML
	private Button btImprimir;

	@FXML
	private Button btSair;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btSelecionarFoto;

	@FXML
	private Button btVoltar;

	@FXML
	private ImageView imageView;

	@FXML
	private Label lblStatus;

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
    
	
	Load lv = new Load();
	
	private FileChooser fileChooser;
	private File file;
	private Image image;
	private Image imagePadrao;
	private FileInputStream imagem;
	
    Endereco e = BuscaAdmController.e;
    Meliante m = BuscaAdmController.m;
	MelianteDao dao = new MelianteDao();
	Document doc = new Document(PageSize.A4);

	@FXML
	void onBtEditarAction(ActionEvent event) {
		btSelecionarFoto.setVisible(true);
		btSalvar.setVisible(true);
		ativaTxt();
	}

	@FXML
	void onBtExcluirAction(ActionEvent event) {
		MelianteDao dao = new MelianteDao();
		EnderecoDao daoEnd = new EnderecoDao();
		if (dao.delete(m) && daoEnd.delete(e)) {
			limparCampos();
			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("Meliante removido do sistema!");
		} else {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Erro ao excluir meliante!");
		}
	}

	@FXML
	void onBtGerarPDFAction(ActionEvent event) {
		gerarPDF();
	}

	@FXML
	void onBtImprimirAction(ActionEvent event) {
    	Impressao imp = new Impressao();
		imp.detectaImpressoras();
		if(doc==null) {
			lblStatus.setText("Gere um PDF para impress�o");
		}else {			
		if (!imp.imprime(doc)) {
			lblStatus.setText(
					"Nennhuma impressora foi encontrada. Instale uma impressora padr�o e reinicie o programa.");
		}
		}
	}

	@FXML
	void onBtSairAction(ActionEvent event) {
		lv.loadview("/views/Login.fxml");
	}

	@FXML
	void onBtSalvarAction(ActionEvent event) {
		lblStatus.setText("");

		btSalvar.setVisible(false);
		atualiza();
		desativaTxt();
	}

	@FXML
	void onBtSelecionarFotoAction(ActionEvent event) {

		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.png"));

		file = fileChooser.showOpenDialog(null);
		if (file != null) {
			System.out.println(file);
			
			image = new Image(file.toURI().toString());
			imageView.setImage(image);

			try {
				imagem = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
		
	@FXML
	void onBtVoltarAction(ActionEvent event) {
		lv.loadview("/views/Busca.fxml");

	}

	@FXML
	void txtCPFKeyReleased(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCPF);
		tff.formatter();
	}

	@FXML
	void txtNumeroKeyReleased(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#######");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtNumero);
		tff.formatter();
	}

	@FXML
	void txtTelefoneKeyReleased(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtTelefone);
		tff.formatter();
	}
	
    public void exibir() {
    	Image img = dao.visualizar(m);

    	txtNome.setText(m.getNome());
    	txtCPF.setText(m.getCPFMeliante());
    	txtApelido.setText(m.getApelido());
    	txtCaracFisicas.setText(m.getCaracteristicasFisicas());
    	txtDelitos.setText(m.getDelitos());
    	txtFaccao.setText(m.getFaccao());
    	imageView.setImage(img);
    	txtTelefone.setText(m.getTelefone());

    	txtCidade.setText(e.getCidade());
    	txtUF.setText(e.getEstado());
    	txtBairro.setText(e.getBairro());
    	txtRua.setText(e.getRua());
    	txtNumero.setText(e.getNumero());
    }

	
	private void atualiza() {

		String nome = txtNome.getText(), CPFMeliante = txtCPF.getText(), apelido = txtApelido.getText(),
				caracteristicasFisicas = txtCaracFisicas.getText(), delitos = txtDelitos.getText(),
				faccao = txtFaccao.getText(), telefone = txtTelefone.getText(), cidade = txtCidade.getText(),
				bairro = txtBairro.getText(), rua = txtRua.getText(), estado = txtUF.getText(),
				numero = txtNumero.getText();

		if (file == null) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Selecione uma foto");
		} else {
			try {
				imagem = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int idM = m.getId();

			Meliante mUp = new Meliante(idM, nome, apelido, CPFMeliante, caracteristicasFisicas, telefone, imagem,
					delitos, faccao);
			Endereco enUp = new Endereco(idM, cidade, estado, bairro, rua, numero);

			MelianteDao dao = new MelianteDao();
			EnderecoDao daoEn = new EnderecoDao();

			if (dao.update(mUp) && daoEn.update(enUp)) {
				lblStatus.setTextFill(Color.GREEN);
				lblStatus.setText("Cadastro atualizado com sucesso!");
				limparCampos();

			} else {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Erro da atualiza��o do cadastro...");
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
		txtDelitos.clear();
		txtFaccao.clear();
		imageView.setImage(new Image("/views/SeekPng.com_personas-png_1305038.png"));
		txtTelefone.clear();
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

	public void gerarPDF() {
		MelianteDao dao = new MelianteDao();
		EnderecoDao daoEnd = new EnderecoDao();
		Meliante mImp = dao.buscaMeliante(m.getCPFMeliante());
		Endereco eImp = daoEnd.buscaEndereco(m.getId());

		doc.setMargins(40f, 40f, 40f, 40f);

		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));
		File file = fc.showSaveDialog(new Stage());
		if (file != null) {

			try {
		    	Image img = dao.visualizar(m);


				com.lowagie.text.Image imagem = com.lowagie.text.Image.getInstance(img.getUrl());
				imagem.setAlignment(com.lowagie.text.Image.ALIGN_CENTER);
				imagem.scaleAbsolute(150.0f, 150.0f);

				PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsolutePath()));
				doc.open();

				Paragraph titulo = new Paragraph(
						new Phrase(25F, "Ficha do Meliante", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18F)));
				titulo.setAlignment(Element.ALIGN_CENTER);

				doc.add(titulo);
				doc.add(new Paragraph("                            "));
				doc.add(imagem);
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph(
						new Phrase("Dados Pessoais", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14F))));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph("Nome: " + mImp.getNome()));
				doc.add(new Paragraph("Apelido: " + mImp.getNome()));
				doc.add(new Paragraph("CPF: " + mImp.getCPFMeliante()));
				doc.add(new Paragraph("Caracter�sticas F�siscas: " + mImp.getCaracteristicasFisicas()));
				doc.add(new Paragraph("Telefone: " + mImp.getTelefone()));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph(new Phrase("Endere�o", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14F))));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph("Cidade: " + eImp.getCidade() + " Estado: " + eImp.getEstado()));
				doc.add(new Paragraph("Bairro: " + eImp.getBairro()));
				doc.add(new Paragraph("Rua: " + eImp.getRua() + " N�: " + eImp.getNumero()));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph(
						new Phrase("Outras Informa�oes", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14F))));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph("Delitos: " + mImp.getDelitos()));
				doc.add(new Paragraph("Fac��o: " + mImp.getFaccao()));

				doc.close();

			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (DocumentException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
   
	@FXML
	void initialize() {
		exibir();
	}

}
