package controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.MelianteDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.entites.Meliante;
import model.utils.Load;

public class FichaMelianteUsuarioController implements Initializable {
	
	@FXML
	private Label lblNome;
	@FXML
	private Label lblApelido;
	@FXML
	private Label lblCPF;
	@FXML
	private Label lblCaracFisicas;
	@FXML
	private Label lblTelefone;
	@FXML
	private Label lblCidade;
	@FXML
	private Label lblUF;
	@FXML
	private Label lblBairro;
	@FXML
	private Label lblRua;
	@FXML
	private Label lblNumero;
	@FXML
	private Label lblDelitos;
	@FXML
	private Label lblFaccao;
	@FXML
	private ImageView imageView;
	@FXML
	private Button btGerarPDF;
	@FXML
	private Button btImprimir;
	@FXML
	private Button btVoltar;
	@FXML
	private Button btSair;
	
	Meliante m = BuscaController.m;
	
	Load lv = new Load();
	
	@FXML
	private void onBtGerarPDFAction() {
		
	}
	
	public void gerarPDF() {
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
	private void onBtImprimirAction() {
		
	}
	public void exibir() {
		MelianteDao dao = new MelianteDao();
		lblNome.setText(m.getNome());
		lblCPF.setText(m.getCPFMeliante());
		lblApelido.setText(m.getApelido());
		lblCaracFisicas.setText(m.getCaracteristicasFisicas());
		lblDelitos.setText(m.getDelitos());
		lblFaccao.setText(m.getFaccao());
		imageView.setImage(dao.visualizar(m));
		lblTelefone.setText(m.getTelefone());
	}
	
	@FXML
	private void onBtVoltarAction() {
		lv.loadview("/views/Busca.fxml");
	}
	
	@FXML
	private void onBtSairAction() {
		lv.loadview("/views/Login.fxml");		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		exibir();
	}

	
}
