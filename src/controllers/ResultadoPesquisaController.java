package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.MelianteDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.entites.Meliante;
import model.utils.Load;

public class ResultadoPesquisaController implements Initializable {


	@FXML
	private HBox hBoxResultado;

	@FXML
	private Label lbApelido;

	@FXML
	private Label lbCPF;

	@FXML
	private Label lbNome;

	@FXML
	private ImageView imageView;

	Load lv = new Load();

	@FXML
	void onHBoxResultadoClicked() {
		lv.loadview("/views/FichaMelianteUsuario.fxml");
	}

	public void exibir() {
		Meliante m = BuscaController.m;
		MelianteDao dao = new MelianteDao();
		dao.visualizar(m);

		lbNome.setText(m.getNome());
		lbApelido.setText(m.getApelido());
		lbCPF.setText(m.getCPFMeliante());
		imageView.setImage(dao.visualizar(m));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		exibir();
	}
}
