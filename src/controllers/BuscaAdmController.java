package controllers;

import DAO.EnderecoDao;
import DAO.MelianteDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.entites.Endereco;
import model.entites.Meliante;
import model.utils.Load;

public class BuscaAdmController {

	@FXML
	private Button btVoltar;
	@FXML
	private TextField txtMelianteBusca;
	@FXML
	private Label lblStatus;
	@FXML
	private HBox hBoxResultado;
	@FXML
	private ImageView imageView;

	@FXML
	private Label lbApelido;

	@FXML
	private Label lbCPF;

	@FXML
	private Label lbNome;

	public static Meliante m;
	public static Endereco e;

	Load lv = new Load();

	@FXML
	public void onBtConfirmarAction() {
		try {
			if (buscaBD()) {

				exibir();
			}
		} catch (IndexOutOfBoundsException e) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Erro ao realizar busca, tente novamente!");
		}

	}

	public boolean buscaBD() {

		String busca = txtMelianteBusca.getText();
		if (busca.isEmpty()) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Campo de pesquisa vazio!");
			return false;
		} else {
			MelianteDao dao = new MelianteDao();
			m = dao.buscaMeliante(busca);
			if (m == null) {
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Meliante não cadastrado do sistema!");
				return false;
			} else {
				System.out.println(m);
				EnderecoDao daoEnd = new EnderecoDao();
				e = daoEnd.buscaEndereco(m.getId());
			}
			return true;
		}

	}

	private void exibir() {
		MelianteDao dao = new MelianteDao();
		lblStatus.setText("");
		hBoxResultado.setStyle("visibility: true;");
		lbNome.setText(m.getNome());
		lbApelido.setText(m.getApelido());
		lbCPF.setText(m.getCPFMeliante());
		imageView.setImage(dao.visualizar(m));
	}

	@FXML
	public void onHBoxResultadoClicked() {
		lv.loadview("/views/FichaMelianteAdm.fxml");
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}

	@FXML
	public void ontBtVoltarAction() {
		lv.loadview("/views/Administrador.fxml");
	}
}
