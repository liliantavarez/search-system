package controllers;

import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.entites.Endereco;
import model.entites.Meliante;
import model.utils.Impressao;
import model.utils.Load;
import model.utils.TextFieldFormatter;

public class FichaMelianteController implements Initializable {

	@FXML private ImageView imageView;
	@FXML private Label lblStatus;
	@FXML private TextField txtApelido, txtBairro, txtCPF, txtCaracFisicas, txtCidade, 
	txtDelitos, txtFaccao, txtNome, txtNumero, txtRua,	txtTelefone, txtUF;

	Load lv = new Load();

	Endereco e = BuscaController.e;
	Meliante m = BuscaController.m;
	MelianteDao dao = new MelianteDao();
	Image img = dao.visualizar(m);
	Document doc = new Document(PageSize.A4);

	@FXML
	void onBtGerarPDFAction(ActionEvent event) {
		gerarPDF();
	}

	@FXML
	void onBtImprimirAction(ActionEvent event) {
		Impressao imp = new Impressao();
		imp.detectaImpressoras();
		gerarPDF();
		if (!imp.imprime(doc)) {
			lblStatus.setText(
					"Nennhuma impressora foi encontrada. Instale uma impressora padrão e reinicie o programa.");
		}
	}

	@FXML
	void onBtSairAction(ActionEvent event) {
		lv.loadview("/views/Login.fxml");
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
				doc.add(new Paragraph("Características Físiscas: " + mImp.getCaracteristicasFisicas()));
				doc.add(new Paragraph("Telefone: " + mImp.getTelefone()));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph(new Phrase("Endereço", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14F))));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph("Cidade: " + eImp.getCidade() + " Estado: " + eImp.getEstado()));
				doc.add(new Paragraph("Bairro: " + eImp.getBairro()));
				doc.add(new Paragraph("Rua: " + eImp.getRua() + " Nº: " + eImp.getNumero()));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph(
						new Phrase("Outras Informaçoes", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14F))));
				doc.add(new Paragraph("                            "));
				doc.add(new Paragraph("Delitos: " + mImp.getDelitos()));
				doc.add(new Paragraph("Facção: " + mImp.getFaccao()));

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

	public void exibir() {
		Endereco e = BuscaController.e;
		Meliante m = BuscaController.m;
		MelianteDao dao = new MelianteDao();
		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		exibir();
	}

}
