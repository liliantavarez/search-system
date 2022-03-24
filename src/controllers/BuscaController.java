package controllers;

import DAO.MelianteDao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.entites.Meliante;
import model.utils.Load;

public class BuscaController {

	@FXML
	private TextField txtMelianteBusca;
	@FXML
	private Label lblStatus;
	public static Meliante m;
	Load lv = new Load();

	@FXML
	public void onBtConfirmarAction() {
		try {
			buscaMeliante();
			if(m!=null) {
				lv.loadview("/views/ResultadoPesquisa.fxml");
			} 
		}catch (RuntimeException e) {
				//e.printStackTrace();
				lblStatus.setTextFill(Color.TOMATO);
				lblStatus.setText("Usuário não cadastrado!");
			}
	}

	public boolean buscaMeliante() {
		String busca = txtMelianteBusca.getText();

		if (busca.isEmpty()) {
			System.out.println("ssss");
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Insira um valor para realizar a busca!");
			return false;

		} else {
			MelianteDao dao = new MelianteDao();
			m = dao.buscaMeliante(busca);
			return true;
		}
	}

	@FXML
	public void onBtSairAction() {
		lv.loadview("/views/Login.fxml");
	}
	@FXML
	public void onBtVoltarAction() {
		lv.loadview("/views/Login.fxml");
	}

}
